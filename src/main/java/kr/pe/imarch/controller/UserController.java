package kr.pe.imarch.controller;

import kr.pe.imarch.model.entity.User;
import kr.pe.imarch.model.entity.user.UserType;
import kr.pe.imarch.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user")
public class UserController extends LayoutController {
    @Autowired private UserRepository ur;

    @ModelAttribute("header")
    public String header() {
        return "user/header";
    }

    @RequestMapping("/list")
    public String findAll(Model model) {
        model.addAttribute("users", ur.findAll());
        return layoutCall("user/list", model);
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();

        user.setDeleted(false);
        user.setUserType(UserType.REAL);

        model.addAttribute("user", user);
        return layoutCall("user/new", model);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String newUser(@ModelAttribute User user,
                          Model model) {
        user.setCreatedTime(Timestamp.from(Instant.now()));
        ur.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam int id,
                           Model model) {
        model.addAttribute("user", ur.findOne(id));
        return layoutCall("user/new", model);
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute User user,
                           Model model) {

        ur.save(user);
        return "redirect:/user/list";
    }
}
