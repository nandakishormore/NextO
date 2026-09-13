package com.nextbigo.Nexto.service;

import java.util.List;

import com.nextbigo.Nexto.dto.member.InviteMemberRequest;
import com.nextbigo.Nexto.dto.member.MemberResponse;
import com.nextbigo.Nexto.dto.member.UpdateMemberRoleRequest;

public interface ProjectMemberService {

	List<MemberResponse> getProjectMembers(Long projectId);

	MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

	MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

	void removeProjectMember(Long projectId, Long memberId);

}
