package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @PostMapping
    public User create(User user){
        return new User();
    }
}
