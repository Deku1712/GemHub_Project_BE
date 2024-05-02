package exeGemHub.gemhub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exeGemHub.gemhub.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findRoleByRoleName(String roleName);
}
