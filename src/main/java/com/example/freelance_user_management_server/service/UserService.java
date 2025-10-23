package com.example.freelance_user_management_server.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.freelance_user_management_server.dto.CreateUserRequest;
import com.example.freelance_user_management_server.dto.CreateUserResponse;
import com.example.freelance_user_management_server.dto.GetUserResponse;
import com.example.freelance_user_management_server.entity.UserEntity;
import com.example.freelance_user_management_server.enums.UserRole;
import com.example.freelance_user_management_server.enums.UserStatus;
import com.example.freelance_user_management_server.exception.ResourceNotFoundException;
import com.example.freelance_user_management_server.repository.UserRepository;
import com.example.freelance_user_management_server.translator.UserTranslator;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public GetUserResponse getUserByUserGUID(String userGUID) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByUserGUID(userGUID);
		if (optionalUserEntity.isPresent()) {
			UserEntity userEntity = optionalUserEntity.get();
			return GetUserResponse.builder()
						.userGUID(userEntity.getUserGUID())
						.email(userEntity.getEmail())
						.role(userEntity.getRole())
						.name(userEntity.getName())
						.gender(userEntity.getGender())
						.description(userEntity.getDescription())
						.birthday(userEntity.getBirthday())
						.profilePicture(userEntity.getProfilePicture())
						.build();
		} else {
			throw new ResourceNotFoundException("User with GUID " + userGUID + " not found.");
		}

	}

	public GetUserResponse getUserByEmailAndRole(String email, UserRole role) throws ResourceNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.getUserByEmailAndRole(email, role);
		if (optionalUserEntity.isPresent()) {
			UserEntity userEntity = optionalUserEntity.get();
			return GetUserResponse.builder()
						.userGUID(userEntity.getUserGUID())
						.email(userEntity.getEmail())
						.role(userEntity.getRole())
						.name(userEntity.getName())
						.birthday(userEntity.getBirthday())
						.description(userEntity.getDescription())
						.gender(userEntity.getGender())
						.profilePicture(userEntity.getProfilePicture())
						.build();
		} else {
			throw new ResourceNotFoundException("User with email " + email + " and role " + role.getValue() + " not found.");
		}
	}

	public CreateUserResponse createUser(CreateUserRequest request) {
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		UserEntity userEntity = UserEntity.builder()
				.userGUID(UUID.randomUUID().toString())
				.email(request.getEmail())
				.password(encodedPassword)
				.role(request.getRole())
				.status(UserStatus.CREATED)
				.name(request.getName())
				.gender(request.getGender())
				.description(request.getDescription())
				.birthday(request.getBirthday())
				.profilePicture(request.getProfilePicture())
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build();
		userRepository.insertUser(userEntity);
		return UserTranslator.toDto(userEntity);

	}

	public void deleteUser(String userGUID) {
		userRepository.deleteUserByUserGUID(userGUID);
	}
}
