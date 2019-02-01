package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import service.UserService;
import entity.User;

public class UserRealm extends AuthorizingRealm{
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/*
	 * 表示获取用户身份验证信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//PrincipalCollection是一个身份集合，因为我们现在就一个Realm，所以直接调用getPrimaryPrincipal
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(username));
		authorizationInfo.setStringPermissions(userService.findPermissions(username));

		return authorizationInfo;
	}
	/*
	 * 表示根据用户身份获取授权信息
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.findByUsername(username);
		if(user==null){
			throw new UnknownAccountException();	//没找到账号
		}
		if(Boolean.TRUE.equals(user.getLocked())){
			throw new LockedAccountException();		//账号锁定
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(),	//用户名
				user.getPassword(),	//密码
				ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
				getName()			//realm name
		);
		return authenticationInfo;
	}
	/*
	 * 清空之前缓存的AuthorizationInfo
	 */
	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	/*
	 * 清空之前缓存的AuthenticationInfo
	 */
	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}
	/*
	 * 其同时调用clearCachedAuthorizationInfo和clearCachedAuthenticationInfo
	 */
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCacheAuthorizationInfo(){
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo(){
		getAuthenticationCache().clear();
	}

	public void clearAllCache(){
		clearAllCacheAuthorizationInfo();
		clearAllCachedAuthenticationInfo();
	}
}
