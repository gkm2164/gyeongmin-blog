package kr.pe.imarch.controller;

import kr.pe.imarch.controller.form.PostForm;
import kr.pe.imarch.model.entity.Category;
import kr.pe.imarch.model.entity.Post;
import kr.pe.imarch.model.entity.PostDetail;
import kr.pe.imarch.model.entity.User;
import kr.pe.imarch.model.repository.CategoryRepository;
import kr.pe.imarch.model.repository.PostDetailRepository;
import kr.pe.imarch.model.repository.PostRepository;
import kr.pe.imarch.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by USER on 2016-01-27.
 */
@Controller
@RequestMapping("/post")
public class PostController extends LayoutController {

    @Autowired private UserRepository ur;
    @Autowired private PostRepository pr;
    @Autowired private CategoryRepository cr;
    @Autowired private PostDetailRepository pdr;

    @ModelAttribute("header")
    public String header() {
        return "post/header";
    }

    @RequestMapping({"/", "/list"})
    public String postList(Model model) {
        model.addAttribute("posts", pr.findAll());
        return layoutCall("post/list", model);
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        PostForm postForm = new PostForm();
        model.addAttribute("postForm", new PostForm());
        return layoutCall("post/new", model);
    }

    @Transactional
    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String newPost(@ModelAttribute PostForm postForm) {
        Post post = new Post();
        PostDetail postDetail = new PostDetail();

        User user = ur.findOne(postForm.getOwnerId());
        Category category = cr.findOne(postForm.getCategoryId());

        post.setOwner(user);
        post.setCategory(category);
        post.setTitle(postForm.getTitle());
        post.setCreatedTime(Timestamp.from(Instant.now()));
        post.setModifiedTime(Timestamp.from(Instant.now()));
        post.setDeleted(false);
        pr.save(post);

        postDetail.setText(postForm.getText());
        postDetail.setPost(post);
        pdr.save(postDetail);
        return "redirect:/post/list";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editPost(@RequestParam int id, Model model) {
        Post post = pr.findOne(id);
        PostForm postForm = new PostForm();

        postForm.setId(post.getId());
        postForm.setOwnerId(post.getOwner().getId());
        postForm.setCategoryId(post.getCategory().getId());
        postForm.setTitle(post.getTitle());
        postForm.setText(post.getPostDetail().getText());

        model.addAttribute("postForm", postForm);

        return layoutCall("post/edit", model);
    }

    @Transactional
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String editPost(@ModelAttribute PostForm postForm) {
        Post post = pr.findOne(postForm.getId());
        PostDetail postDetail = post.getPostDetail();
        Category category = cr.findOne(postForm.getCategoryId());

        post.setCategory(category);
        post.setTitle(postForm.getTitle());
        post.setModifiedTime(Timestamp.from(Instant.now()));
        post.setDeleted(false);
        postDetail.setText(postForm.getText());

        pr.save(post);

        return "redirect:/post/list";
    }

    @RequestMapping("/show")
    public String showPost(@RequestParam int id,
                           Model model) {
        Post post = pr.findOne(id);

        model.addAttribute("post", post);

        return layoutCall("post/show", model);
    }
}
