package kr.demonic.util;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 에러페이지 핸들러 클래스
 */
@Controller
public class ErrorPageHandler implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "comm/error-404";
            }
            if(statusCode == HttpStatus.FORBIDDEN.value()){
                return "comm/error-403";
            }
        }
        return "comm/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
