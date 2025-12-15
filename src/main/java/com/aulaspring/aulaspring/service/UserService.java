package com.aulaspring.aulaspring.service;

import com.aulaspring.aulaspring.controller.CreateUserDTO;
import com.aulaspring.aulaspring.controller.UpdateUserDTO;
import com.aulaspring.aulaspring.entity.User;
import com.aulaspring.aulaspring.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Builder
public class    UserService {

    private final UserRepository userRepository;

    public UUID createUser(CreateUserDTO createUserDTO) {

        // CONVERTER DE DTO PARA ENTITY
        var entity = new User();
        entity.setUsername(createUserDTO.username());
        entity.setEmail(createUserDTO.email());
        entity.setPassword(createUserDTO.password());
        entity.setCreationTimestamp(Instant.now());
        entity.setUpdateTimestamp(null);

        var userSaved = userRepository.saveAndFlush(entity);

        return userSaved.getUserId();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public UUID deleteUser(UUID userId) {
        User deletedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        userRepository.delete(deletedUser);
        return deletedUser.getUserId();
    }

    public User findById(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
    }

    public User updateUserById(UUID userId, UpdateUserDTO updateUserDTO){
        User userEntity = findById(userId);
        User updatedUser = User.builder().userId(userEntity.getUserId()) // copia o ID pro updatedUser pra sobrescrever
                ///////////////////////////////
                .username(updateUserDTO.username() != null ? updateUserDTO.username() : userEntity.getUsername())
                ///////////////////////////////
                .email(updateUserDTO.email() != null ? updateUserDTO.email() : userEntity.getEmail())
                ///////////////////////////////
                .password(updateUserDTO.password() != null ? updateUserDTO.password() : userEntity.getPassword())
                ///////////////////////////////
                .creationTimestamp(userEntity.getCreationTimestamp())
                ///////////////////////////////
                .updateTimestamp(Instant.now())
                .build();

        return userRepository.saveAndFlush(updatedUser);
    }
}
