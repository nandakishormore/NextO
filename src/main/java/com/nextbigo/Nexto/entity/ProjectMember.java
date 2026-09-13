package com.nextbigo.Nexto.entity;

import java.time.Instant;

import com.nextbigo.Nexto.enums.ProjectRole;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {
	
	@EmbeddedId
	ProjectMemberId id;
	
	@ManyToOne
	@MapsId("projectId")
	Project project;
	
	@ManyToOne
	@MapsId("userId")
	User user;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	ProjectRole projectRole;
	
	Instant invitedAt;
	Instant acceptedAt;
	
}
