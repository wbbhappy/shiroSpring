package dao;

import entity.Permission;

public interface PermissionDao {
	public Permission createPermission(Permission permission);
	public void deletePermission(Long permissionId);
}
