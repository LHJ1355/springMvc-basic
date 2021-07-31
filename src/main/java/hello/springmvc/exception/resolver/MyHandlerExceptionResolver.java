package hello.springmvc.exception.resolver;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        exception 발생 시에 서블릿 컨테이너는 status code = 500인 internal server error로 판단하여
//        HttpStatus.INTERNAL_SERVER_ERROR error page를 호출함
//        요청 파라미터에 문제가 있는 경우 서블릿 컨테이너가 status code = 400인 error page를 호출해 주도록 exception resolver에서 바꿀 수 있음
        if(ex instanceof IllegalArgumentException) {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
