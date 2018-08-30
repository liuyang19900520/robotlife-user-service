package com.liuyang19900520.robotlife.user.exception;


import com.liuyang19900520.robotlife.user.common.pojo.CommonRes;
import com.liuyang19900520.robotlife.user.common.util.LJsonUtils;
import com.liuyang19900520.robotlife.user.common.util.LLoggerUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author liuya
 */
@Slf4j
@Component
public class SysExceptionHandler implements HandlerExceptionResolver {

    private Logger logBusiness = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        SysException sysException = null;
        if (ex instanceof SysException) {
            sysException = (SysException) ex;
            response.setStatus(HttpStatus.OK.value());
            logBusiness.info("--------------Response----------------");
            logBusiness.info("异常:" + ((SysException) ex).getErrorMessage());
            logBusiness.info("--------------Response----------------");
        } else if (ex instanceof DuplicateKeyException) {
            sysException = new SysException("", "");
            sysException.setErrorCode("09");
            sysException.setErrorMessage("DB インサート失敗");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            log.error("异常:" + ex.getMessage(), ex);
        } else {
            // 如果抛出的不是系统自定义异常则重新构造一个系统错误异常。
            sysException = new SysException("", "exception");
            // httpStatus
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error("异常:" + ex.getMessage(), ex);
        }
        // ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        /* response */
        String jsonstr = LJsonUtils
                .objectToJson(CommonRes.buildError(sysException.getErrorCode(), sysException.getErrorMessage()));
        try {
            response.getWriter().write(jsonstr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView();
    }

}
