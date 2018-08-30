package com.liuyang19900520.robotlife.user.shiro.filter;


import com.liuyang19900520.robotlife.user.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

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

        HttpServletResponse resp = (HttpServletResponse) response;
        if (isJwtSubmission(request)) {
            AuthenticationToken token = createJwtToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                if (subject.isAuthenticated()) {
                    return true;
                }
            } catch (AuthenticationException e) {
//                e.printStackTrace();
//                log.info("认证失败");
//                resp.setContentType("application/json;charset=UTF-8");
//                resp.setHeader("Access-Control-Allow-Origin", "*");
//                resp.getOutputStream().write(LJsonUtils.objectToJson(ResultVo.error(Messages.SIGN_IN_FAILED, e.getMessage())).getBytes());
                throw new SysException("10001", e.getMessage());
            }
        }
        return false;
    }

}
