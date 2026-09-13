package com.nextbigo.Nexto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nextbigo.Nexto.dto.member.MemberResponse;
import com.nextbigo.Nexto.entity.ProjectMember;
import com.nextbigo.Nexto.entity.User;
import com.nextbigo.Nexto.enums.ProjectRole;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
	
	@Mapping(target = "projectRole", expression = "java(ProjectRole.OWNER)")
	MemberResponse toProjectMemberResponceFromOwner(User owner);
	
	@Mapping(source = "id.userId", target = "id")
	@Mapping(source = "user.username", target = "username")
	@Mapping(source = "user.name", target = "name")
	MemberResponse toMemberResponsefromProjectMember(ProjectMember projectMember);

}
