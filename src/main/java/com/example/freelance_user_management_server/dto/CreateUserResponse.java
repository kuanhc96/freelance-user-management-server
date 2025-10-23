package com.example.freelance_user_management_server.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

import com.example.freelance_user_management_server.enums.Gender;
import com.example.freelance_user_management_server.enums.UserRole;
import com.example.freelance_user_management_server.enums.UserStatus;

@Data
@Builder
public class CreateUserResponse {
	private String userGUID;
	private String name;
	private String email;
	private LocalDate birthday;
	private Gender gender;
	private String description;
	private UserStatus status;
	private UserRole role;
	private String profilePicture;
}
