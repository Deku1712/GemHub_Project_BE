package exeGemHub.gemhub.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import exeGemHub.gemhub.Entity.Role;
import exeGemHub.gemhub.Repository.RoleRepo;
import exeGemHub.gemhub.Service.RoleService;

public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepo roleRepo;
	
	public RoleServiceImpl() {
	}
	
	public RoleServiceImpl(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		return roleRepo.findRoleByRoleName(roleName);
	}

	@Override
	public Iterable<Role> findAll() {
		return roleRepo.findAll();
	}

	@Override
	public void save(Role role) {
		roleRepo.save(role);
	}

}
