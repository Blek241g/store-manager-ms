package org.scalke.userservice.web.controllers;

import jakarta.validation.Valid;
import org.scalke.userservice.dtos.UserDTO;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.UserNotFoundException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.scalke.userservice.web.requests.AddNewUserRequest;
import org.scalke.userservice.web.requests.UpdateUserNameRequest;
import org.scalke.userservice.web.requests.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
public interface UserController {
    @PostMapping("")
    ResponseEntity<UserDTO> createMewUser(@Valid @RequestBody AddNewUserRequest request) throws UserServiceLogicException;

    @GetMapping("/id/{id}")
    ResponseEntity<AppUser> getUserById(@PathVariable Long id) throws UserServiceLogicException, UserNotFoundException;

    @GetMapping("/email/{email}")
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws UserServiceLogicException;

    @GetMapping("/username/{username}")
    ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws UserServiceLogicException;

    @GetMapping("/{creatorId}")
    ResponseEntity<Page<UserDTO>> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascending,
                                           @PathVariable long creatorId) throws UserServiceLogicException;

    @PutMapping("")
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserRequest request) throws UserServiceLogicException;

    @PatchMapping("/name")
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserNameRequest request) throws UserServiceLogicException;

}
