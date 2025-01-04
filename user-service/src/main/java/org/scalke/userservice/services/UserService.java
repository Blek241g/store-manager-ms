package org.scalke.userservice.services;


import org.scalke.userservice.dtos.UserDTO;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.UserNotFoundException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.scalke.userservice.web.requests.AddNewUserRequest;
import org.scalke.userservice.web.requests.UpdateUserNameRequest;
import org.scalke.userservice.web.requests.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO addNewUser(AppUser appUser) throws UserServiceLogicException;
    UserDTO addNewUser(AddNewUserRequest request) throws UserServiceLogicException;
    UserDTO findUserByUsername(String username) throws UserServiceLogicException;
    Page<UserDTO> findUsers(Pageable pageable) throws UserServiceLogicException;
    Page<UserDTO> findUsers(Pageable pageable, long creatorId) throws UserServiceLogicException;
    UserDTO findUserByEmail(String email) throws UserServiceLogicException;
    AppUser findUserById(Long id) throws UserServiceLogicException, UserNotFoundException;
    UserDTO updateUser(AppUser user) throws UserServiceLogicException;
    UserDTO updateUser(UpdateUserRequest request) throws UserServiceLogicException;
    void deleteUser(Long id) throws UserServiceLogicException;
    void deleteUser(AppUser appUser) throws UserServiceLogicException;
    UserDTO updateUserFullName(UpdateUserNameRequest request) throws UserServiceLogicException, UserNotFoundException;
}
