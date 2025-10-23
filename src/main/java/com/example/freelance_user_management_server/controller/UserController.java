package com.example.freelance_user_management_server.controller;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;

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
//	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<GetUserResponse> getUserByUserGUID(@PathVariable String userGUID) throws ResourceNotFoundException {
		if (StringUtils.isBlank(userGUID)) {
			throw new IllegalArgumentException("userGUID must not be blank");
		}
		return ResponseEntity.ok(userService.getUserByUserGUID(userGUID));
	}

	@GetMapping
//	@PreAuthorize(INSTRUCTOR_OR_STUDENT)
	public ResponseEntity<GetUserResponse> getUserByUserEmailAndRole(
			@RequestParam String email, @RequestParam UserRole role
	) throws ResourceNotFoundException {
		if (StringUtils.isBlank(email)) {
			throw new IllegalArgumentException("Email must not be blank");
		}

		return ResponseEntity.ok(userService.getUserByEmailAndRole(email, role));
	}

}
