package com.example.freelance_user_management_server.translator;

import com.example.freelance_user_management_server.dto.CreateUserResponse;
import com.example.freelance_user_management_server.entity.UserEntity;

public class UserTranslator {
	public static UserEntity toEntity(CreateUserResponse dto) {
		return UserEntity.builder()
				.email(dto.getEmail())
				.role(dto.getRole())
				.status(dto.getStatus())
				.name(dto.getName())
				.gender(dto.getGender())
				.description(dto.getDescription())
				.birthday(dto.getBirthday())
				.profilePicture(dto.getProfilePicture() == null? "favicon.ico": dto.getProfilePicture())
				.build();
	}

	public static CreateUserResponse toDto(UserEntity entity) {
		return CreateUserResponse.builder()
				.userGUID(entity.getUserGUID())
				.email(entity.getEmail())
				.status(entity.getStatus())
				.name(entity.getName())
				.gender(entity.getGender())
				.description(entity.getDescription())
				.birthday(entity.getBirthday())
				.role(entity.getRole())
				.build();
	}
}
