package exeGemHub.gemhub.Service;

import exeGemHub.gemhub.Entity.Role;

public interface RoleService {
	Role findRoleByRoleName(String roleName);
	
	Iterable<Role> findAll();
	
	void save(Role role);
}
