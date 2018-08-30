package com.liuyang19900520.robotlife.user.shiro.filter;


import com.liuyang19900520.robotlife.auth.commons.pojo.Messages;
import com.liuyang19900520.robotlife.auth.commons.pojo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by liuyang on 2018/3/18
 *
 * @author liuya
 */
@Slf4j
public class JwtAuthFilter extends StatelessFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isJwtSubmission(request)) {
            AuthenticationToken token = createJwtToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                if (subject.isAuthenticated()) {

                    return true;
                }
            } catch (AuthenticationException e) {
                log.error(e.getMessage(), e);
                ResultVo.error(Messages.UNAUTHORIZATION
                        , e.getMessage());
            }
        }
        return false;
    }

}
