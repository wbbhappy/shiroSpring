package dao;

import entity.Role;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDaoImpl extends JdbcDaoSupport implements RoleDao{
	/*
	 * 添加角色
	 */
	public Role createRole(final Role role) {
		final String sql = "insert into sys_roles(role, description, available) values(?,?,?)";
		//Spring提供了一个可以返回新增记录对应主键值的方法
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql,new String[]{"id"});
				psst.setString(1, role.getRole());
				psst.setString(2, role.getDescription());
				psst.setBoolean(3, role.getAvailable());
				return psst;
			}
		},keyHolder);
		role.setId(keyHolder.getKey().longValue());
		return role;
	}
	/*
	 * 删除角色
	 */
	public void deleteRole(Long roleId) {
		//首先把和role关联的相关表数据删掉
		String sql = "delete from sys_users_roles where role_id=?";
		getJdbcTemplate().update(sql, roleId);
		sql = "delete from sys_roles where id=?";
		getJdbcTemplate().update(sql, roleId);
	}
	/*
	 * 关联角色和权限
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if(permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
		for(Long permissionId : permissionIds) {
			if(!exists(roleId, permissionId)) {
				getJdbcTemplate().update(sql, roleId, permissionId);
			}
		}
	}
	/*
	 * 解除关联角色和权限
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if(permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
		for(Long permissionId : permissionIds) {
			if(exists(roleId, permissionId)) {
				getJdbcTemplate().update(sql, roleId, permissionId);
			}
		}
	}
	/*
	 * 判断角色:权限映射在表中是否已存在
	 */
	private boolean exists(Long roleId, Long permissionId) {
		String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, roleId, permissionId) != 0;
	}
}
