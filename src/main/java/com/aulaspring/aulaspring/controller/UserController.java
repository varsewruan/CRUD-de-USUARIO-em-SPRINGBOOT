package com.aulaspring.aulaspring.controller;

import com.aulaspring.aulaspring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
        public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO){
            return ResponseEntity.ok(userService.createUser(createUserDTO));
        }

    @GetMapping
        public ResponseEntity<?> findAllUsers(){
            return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable UUID userId){
        try{
            return ResponseEntity.ok(userService.findById(userId));
        } catch (RuntimeException userNotFoundException){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable UUID userId, @RequestBody UpdateUserDTO user){
        userService.updateUserById(userId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
        public ResponseEntity<?> deleteUser(@PathVariable UUID userId){
        try{
            return ResponseEntity.ok(userService.deleteUser(userId));
        } catch (RuntimeException userNotFoundException){
            return ResponseEntity.badRequest().build();
        }
    }
}

