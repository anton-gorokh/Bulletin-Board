package org.bulletin_board.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("hello")
    public String hello() {
        return "Hi buddy :)";
    }
}
