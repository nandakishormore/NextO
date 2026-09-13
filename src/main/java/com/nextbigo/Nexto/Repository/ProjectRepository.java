package com.nextbigo.Nexto.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nextbigo.Nexto.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	@Query("""
			SELECT p FROM Project p
			WHERE p.deletedAt IS NULL
			AND EXISTS (
				SELECT 1 FROM ProjectMembers pm
				where pm.id.userId = :userId
				And pm.id.projectId = p.id
			)
			ORDER BY p.updatedAt DESC
			"""
	)
	List<Project> findAllAccesibleByUser(@Param("userId") Long userId);

	@Query(""" 
			SELECT p FROM Project p
			WHERE p.id = :projectId
			AND p.deletedAt IS NULL					
			"""
	)
	Optional<Project> findAccesibleProjectById(@Param("projectId") Long projectId, @Param("userId") Long userId);
}
