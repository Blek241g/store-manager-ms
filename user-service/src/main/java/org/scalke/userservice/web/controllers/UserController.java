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
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController{
    @PostMapping("")
    ResponseEntity<UserDTO> createMewUser(@Valid @RequestBody AddNewUserRequest request) throws UserServiceLogicException;

    @GetMapping("/id/{id}")
    ResponseEntity<AppUser> getUserById(@PathVariable Long id) throws UserServiceLogicException, UserNotFoundException;

    @GetMapping("/search/email/{email}")
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws UserServiceLogicException;

    @GetMapping("/search/username/{username}")
    ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) throws UserServiceLogicException;

    @GetMapping("/search/created-by/{creatorId}")
    ResponseEntity<Page<UserDTO>> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascending,
                                           @PathVariable long creatorId) throws UserServiceLogicException, UserNotFoundException;

    @PutMapping("")
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserRequest request) throws UserServiceLogicException;

    @PatchMapping("/name")
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UpdateUserNameRequest request) throws UserServiceLogicException, UserNotFoundException;

}
