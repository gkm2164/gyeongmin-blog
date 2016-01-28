package kr.pe.imarch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by USER on 2016-01-28.
 */
@Controller
public class AuthController {

    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }
}
