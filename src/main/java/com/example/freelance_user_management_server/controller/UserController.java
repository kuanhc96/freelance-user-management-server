package com.example.freelance_user_management_server.controller;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;

import com.example.freelance_user_management_server.dto.CreateUserRequest;
import com.example.freelance_user_management_server.dto.CreateUserResponse;
import com.example.freelance_user_management_server.dto.GetUserResponse;
import com.example.freelance_user_management_server.entity.UserEntity;
import com.example.freelance_user_management_server.enums.UserRole;
import com.example.freelance_user_management_server.exception.ResourceNotFoundException;
import com.example.freelance_user_management_server.repository.UserRepository;
import com.example.freelance_user_management_server.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{userGUID}")
	@PreAuthorize("hasAuthority('USER_MANAGEMENT_SERVER')")
	public ResponseEntity<GetUserResponse> getUserByUserGUID(@PathVariable String userGUID) throws ResourceNotFoundException {
		if (StringUtils.isBlank(userGUID)) {
			throw new IllegalArgumentException("userGUID must not be blank");
		}
		return ResponseEntity.ok(userService.getUserByUserGUID(userGUID));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('USER_MANAGEMENT_SERVER')")
	public ResponseEntity<GetUserResponse> getUserByUserEmailAndRole(
			@RequestParam String email, @RequestParam UserRole role
	) throws ResourceNotFoundException {
		if (StringUtils.isBlank(email)) {
			throw new IllegalArgumentException("Email must not be blank");
		}

		return ResponseEntity.ok(userService.getUserByEmailAndRole(email, role));
	}

	@PostMapping("/user/create")
	@PreAuthorize("hasAuthority('USER_MANAGEMENT_SERVER')")
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
		if (!request.getEmail().contains("@")) {
			throw new IllegalArgumentException("Invalid email format");
		}
		if (io.micrometer.common.util.StringUtils.isBlank(request.getPassword())) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		if (io.micrometer.common.util.StringUtils.isBlank(request.getName())) {
			throw new IllegalArgumentException("Name cannot be empty");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
	}

	@DeleteMapping("/delete/{userGUID}")
	@PreAuthorize("hasAuthority('USER_MANAGEMENT_SERVER')")
	public ResponseEntity<Void> deleteUser(@PathVariable String userGUID) {
		if (io.micrometer.common.util.StringUtils.isBlank(userGUID)) {
			throw new IllegalArgumentException("User GUID cannot be null or empty");
		}
		userService.deleteUser(userGUID);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/authenticate")
	@PreAuthorize("hasAuthority('USER_MANAGEMENT_SERVER')")
	public ResponseEntity<String> authenticate(@RequestParam String email, @RequestParam UserRole role, @RequestParam String password) {
		return ResponseEntity.ok(userService.authenticate(email, role, password));
	}
}
