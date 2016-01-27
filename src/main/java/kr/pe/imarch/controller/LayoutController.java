package kr.pe.imarch.controller;

import org.springframework.ui.Model;

/**
 * Created by USER on 2016-01-27.
 */
public abstract class LayoutController {
    public String layoutCall(String view, Model model) {
        model.addAttribute("title", "Gyeongmin's Blog");
        model.addAttribute("view", view);
        return "layout/main";
    }
}
