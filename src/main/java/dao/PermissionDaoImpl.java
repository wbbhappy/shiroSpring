package dao;

import entity.Permission;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PermissionDaoImpl extends JdbcDaoSupport implements PermissionDao{
	/*
	 * 添加权限
	 */
	public Permission createPermission(final Permission permission) {
		final String sql = "insert into sys_permissions(permission,description,available) values (?,?,?)";
		//Spring提供了一个可以返回新增记录对应主键值的方法
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection)throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql,new String[]{"id"});
				psst.setString(1, permission.getPermission());
				psst.setString(2, permission.getDescription());
				psst.setBoolean(3, permission.getAvailable());
				return psst;
			}
		},keyHolder);
		permission.setId(keyHolder.getKey().longValue());
		return permission;
	}
	/*
	 * 删除权限
	 */
	public void deletePermission(Long permissionId) {
		//首先把与permission关联的相关表的数据删掉
		String sql = "delete from sys_roles_permissions where permission_id=?";
		getJdbcTemplate().update(sql, permissionId);
		sql = "delete from sys_permissions where id=?";
		getJdbcTemplate().update(sql, permissionId);
	}
}
