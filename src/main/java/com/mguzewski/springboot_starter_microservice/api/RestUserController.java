package com.mguzewski.springboot_starter_microservice.api;

import com.mguzewski.springboot_starter_microservice.service.UserService;
import com.mguzewski.springboot_starter_microservice.service.dto.UserCreateRequest;
import com.mguzewski.springboot_starter_microservice.service.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class RestUserController implements UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody final UserCreateRequest request) {
        final UserResponse created = userService.create(request);
        final URI location = URI.create("/api/users/" + created.getId());
        return created(location).body(created);
    }

    @GetMapping
    public Page<UserResponse> list(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable
    ) {
        return userService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable final UUID id) {
        return ok(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        userService.delete(id);
        return noContent().build();
    }
}
