package kr.pe.imarch.controller;

import kr.pe.imarch.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by USER on 2016-01-27.
 */
@Controller
@RequestMapping("/post")
public class PostController extends LayoutController {

    @Autowired PostRepository pr;

    @ModelAttribute("header")
    public String header() {
        return "post/header";
    }

    @RequestMapping({"/", "/list"})
    public String postList(Model model) {
        model.addAttribute("posts", pr.findAll());
        return layoutCall("post/list", model);
    }


}
