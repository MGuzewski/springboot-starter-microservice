package com.mguzewski.springboot_starter_microservice.api;

import com.mguzewski.springboot_starter_microservice.service.dto.UserCreateRequest;
import com.mguzewski.springboot_starter_microservice.service.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Users", description = "Users API")
public interface UserController {

    @Operation(summary = "Create user")
    @ApiResponse(responseCode = "201", description = "Created")
    ResponseEntity<UserResponse> create(UserCreateRequest request);

    @Operation(summary = "Paginated user list")
    Page<UserResponse> list(Pageable pageable);

    @Operation(summary = "Get user by ID")
    ResponseEntity<UserResponse> getById(UUID id);

    @Operation(summary = "Delete user")
    ResponseEntity<Void> delete(UUID id);
}
