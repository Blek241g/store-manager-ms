package org.scalke.userservice.mappers;

import com.github.javafaker.App;
import lombok.AllArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.scalke.userservice.dtos.UserDTO;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.UserNotFoundException;
import org.scalke.userservice.repositories.UserRepository;
import org.scalke.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    public UserRepository userRepository;

    public abstract UserDTO entityToDto(AppUser user);
    public abstract List<UserDTO> mapToDTO(List<AppUser> users);
    public abstract List<AppUser> mapToEntity(List<UserDTO> userDTOS);

    public AppUser dtoToEntity(UserDTO userDTO){
        if (userDTO == null) {
            return null;
        }
        return userRepository.findById(userDTO.getId()).orElse(null);
    }
}
