package jcano.bediscussion2.controller;

import jcano.bediscussion2.dto.UserDTO;
import jcano.bediscussion2.model.UserRequest;
import jcano.bediscussion2.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PutMapping("/signup")
    public UserDTO registerUser(@RequestBody UserRequest userRequest) {
        return usersService.registerUser(userRequest);
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return usersService.getAllUsers();

    }

    @GetMapping("/{email}")
    public UserDTO getUser(@PathVariable String email) {return usersService.getUser(email);}
}
