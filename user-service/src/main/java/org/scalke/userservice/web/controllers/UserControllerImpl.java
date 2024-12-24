package org.scalke.userservice.web.controllers;

import lombok.AllArgsConstructor;
import org.scalke.userservice.dtos.UserDTO;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.UserNotFoundException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.scalke.userservice.services.UserService;
import org.scalke.userservice.web.requests.AddNewUserRequest;
import org.scalke.userservice.web.requests.UpdateUserNameRequest;
import org.scalke.userservice.web.requests.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
public class UserControllerImpl implements UserController {
    private UserService userService;

    @Override
    public ResponseEntity<UserDTO> createMewUser(AddNewUserRequest request) throws UserServiceLogicException {
        UserDTO user = userService.addNewUser(request);
        URI location = MvcUriComponentsBuilder.fromController(getClass()).path("/id/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @Override
    public ResponseEntity<AppUser> getUserById(Long id) throws UserServiceLogicException, UserNotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUserById(id));
    }

    @Override
    public ResponseEntity<UserDTO> getUserByEmail(String email) throws UserServiceLogicException {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUserByEmail(email));
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUsername(String username) throws UserServiceLogicException {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.findUserByUsername(username));
    }

    @Override
    public ResponseEntity<Page<UserDTO>> getUsers(int page, int size, String sortBy, boolean ascending, long creatorId) throws UserServiceLogicException {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy);
        return ResponseEntity.ok(userService.findUsers(PageRequest.of(page, size, sort), creatorId));
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UpdateUserRequest request) throws UserServiceLogicException {
        UserDTO updatedUser = userService.updateUser(request);
        URI location = MvcUriComponentsBuilder.fromController(getClass()).path("/id/{id}").buildAndExpand(updatedUser.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<UserDTO>(updatedUser, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UpdateUserNameRequest request) throws UserServiceLogicException {
        UserDTO updatedUser = userService.updateUserFullName(request);
        URI location = MvcUriComponentsBuilder.fromController(getClass()).path("/id/{id}").buildAndExpand(updatedUser.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<UserDTO>(updatedUser, headers, HttpStatus.OK);
    }


}
