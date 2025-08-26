package com.mguzewski.springboot_starter_microservice.service;

import com.mguzewski.springboot_starter_microservice.domain.User;
import com.mguzewski.springboot_starter_microservice.exception.AlreadyExistsApiException;
import com.mguzewski.springboot_starter_microservice.exception.BadRequestApiException;
import com.mguzewski.springboot_starter_microservice.exception.NotFoundApiException;
import com.mguzewski.springboot_starter_microservice.repository.UserRepository;
import com.mguzewski.springboot_starter_microservice.service.dto.UserCreateRequest;
import com.mguzewski.springboot_starter_microservice.service.dto.UserResponse;
import com.mguzewski.springboot_starter_microservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(final UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsApiException("User with this email already exists");
        }

        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            throw new BadRequestApiException("Given password and password confirmation are not the same.");
        }

        final User user = userMapper.toEntity(request, passwordEncoder);
        final User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(final UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new NotFoundApiException(format("No user with %s ID", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getPage(final Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public void delete(final UUID id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundApiException(format("No user with %s ID", id));
        }
        userRepository.deleteById(id);
    }
}
