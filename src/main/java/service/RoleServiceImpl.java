package service;

import dao.RoleDao;
import entity.Role;

public class RoleServiceImpl implements RoleService{
	private RoleDao roleDao;
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}
}
