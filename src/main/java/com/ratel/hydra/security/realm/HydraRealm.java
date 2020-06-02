package com.ratel.hydra.security.realm;

import com.ratel.hydra.system.po.User;
import com.ratel.hydra.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static com.ratel.hydra.common.constant.ExceptionEnum.AUTH1005;

/**
 * @author ratel
 * @date 2020-05-22
 */
@Slf4j
public class HydraRealm extends AuthorizingRealm  {

    @Autowired
    private UserService service;

    /**
     * @Description 授权
     * @Author      ratel
     * @Date        2020-05-22
     * @param       principalCollection
     * @return      org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权中、、、、");
        return null;
    }

    /**
     * @Description 认证
     * @Author      ratel
     * @Date        2020-05-22
     * @param       authenticationToken
     * @return      org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String(((char[]) authenticationToken.getCredentials()));
        User user = service.getByUsername(username);
        Assert.notNull(user,AUTH1005.getMsg());
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
