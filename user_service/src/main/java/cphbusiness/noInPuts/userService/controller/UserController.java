package cphbusiness.noInPuts.userService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(String id) {
        return "User with id: " + id;
    }
}
