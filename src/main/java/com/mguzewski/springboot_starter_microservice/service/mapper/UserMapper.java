package com.mguzewski.springboot_starter_microservice.service.mapper;

import com.mguzewski.springboot_starter_microservice.domain.User;
import com.mguzewski.springboot_starter_microservice.service.dto.UserCreateRequest;
import com.mguzewski.springboot_starter_microservice.service.dto.UserResponse;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", expression = "java( passwordEncoder.encode(request.password()) )")
    User toEntity(UserCreateRequest request, @Context PasswordEncoder passwordEncoder);

    UserResponse toResponse(User user);
}
