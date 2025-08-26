package com.mguzewski.springboot_starter_microservice.service;

import com.mguzewski.springboot_starter_microservice.service.dto.UserCreateRequest;
import com.mguzewski.springboot_starter_microservice.service.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse getById(UUID id);

    Page<UserResponse> getPage(Pageable pageable);

    void delete(UUID id);
}
