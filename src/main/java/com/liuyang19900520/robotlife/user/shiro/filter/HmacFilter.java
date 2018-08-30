package com.liuyang19900520.robotlife.user.shiro.filter;

import com.liuyang19900520.robotlife.user.common.pojo.Messages;
import com.liuyang19900520.robotlife.user.common.pojo.ResultVo;
import com.liuyang19900520.robotlife.user.common.util.LJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuyang on 2018/3/16
 *
 * @author liuya
 */
@Slf4j
public class HmacFilter extends StatelessFilter {

    /**
     * 是否放行
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            //已经认证过直接放行
            return true;
        }
        //转到拒绝访问处理逻辑
        return false;
    }

    /**
     * 拒绝处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        //如果是Hmac鉴权的请求
        if (isHmacSubmission(request)) {
            //创建令牌
            AuthenticationToken token = createHmacToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                //认证
                subject.login(token);
                if (subject.isAuthenticated()) {
                    return true;
                }
            } catch (UnknownAccountException exception) {
                exception.printStackTrace();
                log.info("账号不存在");
                resp.setContentType("application/json;charset=UTF-8");
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.getOutputStream().write(LJsonUtils.objectToJson(ResultVo.error(Messages.SIGN_IN_FAILED, exception.getMessage())).getBytes());
            } catch (IncorrectCredentialsException exception) {
                exception.printStackTrace();
                log.info("错误的凭证，用户名或密码不正确");
                resp.setContentType("application/json;charset=UTF-8");
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.getOutputStream().write(LJsonUtils.objectToJson(ResultVo.error(Messages.SIGN_IN_AUTH_FAILED, exception.getMessage())).getBytes());
            } catch (LockedAccountException exception) {
                exception.printStackTrace();
                log.info("账户已锁定");
                resp.setContentType("application/json;charset=UTF-8");
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.getOutputStream().write(LJsonUtils.objectToJson(ResultVo.error(Messages.SIGN_IN_STATUS_FAILED, exception.getMessage())).getBytes());
            } catch (ExcessiveAttemptsException exception) {
                exception.printStackTrace();
                log.info("错误次数过多");
                resp.setContentType("application/json;charset=UTF-8");
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.getOutputStream().write(LJsonUtils.objectToJson(ResultVo.error(Messages.SIGN_IN_FAILED, exception.getMessage())).getBytes());
            } catch (AuthenticationException exception) {
                exception.printStackTrace();
                log.info("认证失败");
                resp.setContentType("application/json;charset=UTF-8");
                resp.setHeader("Access-Control-Allow-Origin", "*");
                resp.getOutputStream().write(LJsonUtils.objectToJson(ResultVo.error(Messages.SIGN_IN_FAILED, exception.getMessage())).getBytes());
            }

        }
        //打住，访问到此为止
        return false;
    }


}
