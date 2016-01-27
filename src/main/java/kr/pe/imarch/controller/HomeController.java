package kr.pe.imarch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by USER on 2016-01-27.
 */
@Controller
@RequestMapping("/")
public class HomeController extends LayoutController {

    @ModelAttribute("header")
    public String header() {
        return "home/header";
    }

    @RequestMapping
    public String home(Model model) {
        return layoutCall("home/index", model);
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return layoutCall("home/about", model);
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return layoutCall("home/contact", model);
    }
}
