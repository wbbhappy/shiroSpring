package credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 1小时内密码最多试5次，如果超过5次锁定1小时，之后可再重试，如果还是重试失败，可以锁定如1天，防止密码被暴力破解。
 * 我们通过继承HashedCredentialsMatcher，且使用Ehcache记录重试次数和超时时间。
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
	//AtomicInteger和Integer的区别是前者提供了线程安全方式进行加减，适用于高并发，而后者不是
	private Cache<String,AtomicInteger> passwordRetryCache;
	//在spring-shiro.xml中注入EhCacheManager(classpath:ehcache.xml)
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	public boolean doCredentialsMatch(AuthenticationToken token,AuthenticationInfo info) {
		String username = (String) token.getPrincipal();				//得到用户名
		//String password = new String((char[])token.getCredentials());	//得到密码
		//访问次数+1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if(retryCount==null){
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if(retryCount.incrementAndGet()>5){
			//如果访问次数>5
			throw new ExcessiveAttemptsException();
		}
		//匹配用户输入的token的凭证（未加密）与系统提供的凭证（已加密）
		boolean mathches = super.doCredentialsMatch(token, info);
		//如果登录成功
		if(mathches){
			//清除访问次数
			passwordRetryCache.remove(username);
		}
		return mathches;
	}
}
