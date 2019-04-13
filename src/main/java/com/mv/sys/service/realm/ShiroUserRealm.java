package com.mv.sys.service.realm;


import com.mv.sys.dao.SysUserDao;
import com.mv.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro权限认证，使用加盐加密算法。
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	SysUserDao sysUserDao;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	/**
	 * 使用后MD5加密
	 * @param credentialsMatcher
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher("MD5");
		super.setCredentialsMatcher(cMatcher);
	};

	/**
	 * 授权认证
	 * @param principals
	 * @return
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		SysUser user = (SysUser)principals.getPrimaryPrincipal();
		Integer conservator = user.getConservator();
		Integer vip =user.getVip();

		Set<String> set = new HashSet<>();
		if(conservator==1){
			set.add("ht:tj");
			set.add("ht:sc");
			set.add("ht:xg");
		}if (vip>0){
			set.add("ht:vip");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		return info;
	}

	/**
	 * 重写该方法，进行登陆认证
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		//获取数据库中数据，用于与token对比
		SysUser user = sysUserDao.findUserByName(username);
		//获取不到，抛出异常，说明用户不存在。
		if(user==null)
			throw new UnknownAccountException();
		//密码盐值，加密算法中关键字段。
		ByteSource salt = ByteSource.Util.bytes(user.getSalt());
		//shiro内置对比函数
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
		System.out.println("re:"+info);
		return info
                ;
	}

}
