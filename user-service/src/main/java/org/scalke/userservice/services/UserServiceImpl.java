package org.scalke.userservice.services;

import com.github.javafaker.App;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.AppMessage;
import org.scalke.userservice.constants.UserType;
import org.scalke.userservice.dtos.UserDTO;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.UserNotFoundException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.scalke.userservice.mappers.UserMapper;
import org.scalke.userservice.repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.scalke.userservice.web.requests.AddNewUserRequest;
import org.scalke.userservice.web.requests.UpdateUserNameRequest;
import org.scalke.userservice.web.requests.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

//    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
//    @Transactional(noRollbackFor = Exception.class)
    public UserDTO addNewUser(AppUser appUser) throws UserServiceLogicException {
       try {

           if(appUser.getUserType() == UserType.REGULAR ){
               AppUser creator = findUserById(appUser.getCreatedBy());
           }

           log.info("Creator : {}, userType : {}, username: {}", appUser.getCreatedBy(), appUser.getUserType(), appUser.getUsername());
           return userMapper.entityToDto(userRepository.save(AppUser.builder()
                           .email(appUser.getEmail())
                           .password(appUser.getPassword())
                           .userType(appUser.getUserType())
                           .username(appUser.getUsername())
                           .firstname(appUser.getFirstname())
                           .lastname(appUser.getLastname())
                           .phoneNumber(appUser.getPhoneNumber())
                           .isActive(appUser.getIsActive())
                           .avatar(appUser.getAvatar())
                           .deleted(appUser.getDeleted())
                           .createdBy(appUser.getCreatedBy())
                           .roles(appUser.getRoles())
                           .build()));

       }catch (UserServiceLogicException | UsernameNotFoundException e){
           throw e;
       } catch (Exception e){
           log.error("Error while adding new user", e);
           throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public UserDTO addNewUser(AddNewUserRequest request) throws UserServiceLogicException {
        try {
            AppUser creator = findUserById(request.getCreatedBy());
            return userMapper.entityToDto(userRepository.save(
                            AppUser.builder()
                            .email(request.getEmail())
                            .password("password")
                            .userType(request.getUserType())
                            .username(request.getUsername())
                            .firstname(request.getFirstname())
                            .lastname(request.getLastname())
                            .phoneNumber(request.getPhoneNumber())
                                    .deleted(false)
                            .isActive(false)
                                    .deleted(false)
                            .avatar(request.getAvatar())
                            .createdBy(creator.getId())
                            .build()
            ));

        }
        catch (Exception e){
            log.error("Error while adding new user "+ e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserDTO findUserByUsername(String username) throws UserServiceLogicException {
       try {
           return userMapper.entityToDto(userRepository.findFirstByUsername(username));
       }catch (Exception e) {
           log.error("Error finding user by Username " + username, e);
           throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public Page<UserDTO> findUsers(Pageable pageable) throws UserServiceLogicException {
        try {
            return userRepository.findAll(pageable).map(userMapper::entityToDto);
        }catch (Exception e) {
            log.error("Error finding users", e);
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Page<UserDTO> findUsers(Pageable pageable, long creatorId) throws UserServiceLogicException {
        try {
           AppUser creator = findUserById(creatorId);
           if(creator.getUserType() == UserType.REGULAR){
               throw new UserServiceLogicException("Not authorrized", HttpStatus.UNAUTHORIZED);
           }else {
               return userRepository.findAppUserByCreatedBy(creator.getId(), pageable).map(userMapper::entityToDto);
           }
        }catch (UserServiceLogicException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to fetch users with creator id: {} and message: {}",creatorId, e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserDTO findUserByEmail(String email) throws UserServiceLogicException {
        try {
            return userMapper.entityToDto(userRepository.findFirstByEmail(email));
        }catch (Exception e) {
            log.error("Failed to find user by email: {}", e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteUser(Long id) throws UserServiceLogicException {
        try {
            userRepository.deleteUserById(id);
        }catch (Exception e) {
            log.error("Error while deleting user with id: {} message {}", id, e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteUser(AppUser appUser) throws UserServiceLogicException {
        try {
            userRepository.deleteUser(appUser);
        }catch (Exception e) {
            log.error("Error while deleting user: {}", e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserDTO updateUserFullName(UpdateUserNameRequest request) throws UserServiceLogicException, UserNotFoundException {
        try {
           AppUser user = findUserById(request.getUserId());
           user.setFirstname(request.getFirstname());
           user.setLastname(request.getLastname());
           return userMapper.entityToDto( userRepository.save(user));
        }catch (UserNotFoundException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Error while updating user full name: {}", e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppUser findUserById(Long id) throws UserNotFoundException, UserServiceLogicException {
        try {
            return userRepository.findById(id).orElseThrow(
                    () -> new UserNotFoundException("User with id: "+id+" not found!", HttpStatus.NOT_FOUND));
        }catch (UserNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to find user by id: " + id);
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserDTO updateUser(AppUser user) throws UserServiceLogicException {
        try {
            findUserById(user.getId());
            return userMapper.entityToDto(userRepository.save(user));
        }catch (Exception e){
            log.error("Failed to update user: " + user.getId());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserDTO updateUser(UpdateUserRequest request) throws UserServiceLogicException {
        try {
            AppUser user = findUserById(request.getUserId());
            if(request.getEmail() != user.getEmail()){
               // updateUserWithNewEmail()
            }
            else if(request.getPhoneNumber() != user.getPhoneNumber()){
                //updateUserWithNewPhoneNumber()
            }else{
                user.setFirstname(request.getFirstname());
                user.setLastname(request.getLastname());
                user.setPhoneNumber(request.getPhoneNumber());
                user.setEmail(request.getEmail());
                user.setUsername(request.getUsername());
                user.setAvatar(request.getAvatar());
                user.setUserType(request.getUserType());
            }
            return userMapper.entityToDto(userRepository.save(user));
        }catch (Exception e){
            log.error("Failed to update user in request: " + e.getMessage());
            throw new UserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
