package org.codingdojo.potterkata.contollers;

import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.requests.UserCreateRequest;
import org.codingdojo.potterkata.services.DefaultUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class UserController {

    private final DefaultUserService userService;

    public UserController(DefaultUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    User Create(@RequestBody UserCreateRequest request) throws HttpClientErrorException {
        try {
            return this.userService.create(request.name());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}