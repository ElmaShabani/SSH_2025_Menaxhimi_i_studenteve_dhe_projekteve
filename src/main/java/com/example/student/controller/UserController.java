package com.example.student.controller;

import com.example.student.domain.User;
import com.example.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUser(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<User> users = userService.getAllUsers(page, size);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable String id){
        boolean deleted=userService.deleteUser(id);
        if(deleted){
            return ResponseEntity.ok("User with id"+id+"is deleted successfully");
        }
        else{
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser( String id, @RequestBody User updateuser ){
        User user= userService.updateUser(id,updateuser);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<User>> filterUser(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "email", required = false) String email) {

        List<User> user = userService.filterUser(id, email);

        if (!user.isEmpty()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
