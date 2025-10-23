package com.example.freelance_user_management_server.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import com.example.freelance_user_management_server.enums.Gender;
import com.example.freelance_user_management_server.enums.UserRole;
import com.example.freelance_user_management_server.enums.UserStatus;

@Table(name = "users")
@Data
@Builder
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String userGUID;
	private String email;
	private String password;
	private UserRole role;
	private UserStatus status;
	private String name;
	private Gender gender;
	private String description;
	private LocalDate birthday;
	private String profilePicture;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
