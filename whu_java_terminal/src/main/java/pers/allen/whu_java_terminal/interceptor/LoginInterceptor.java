package pers.allen.whu_java_terminal.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.allen.whu_java_terminal.utils.JWTUtils;
import pers.allen.whu_java_terminal.utils.JsonData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入到controller之前的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
//            // 如果是OPTIONS则结束请求
//            if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//                response.setStatus(HttpStatus.NO_CONTENT.value());
//                response.setHeader("Access-Control-Allow-Origin", "*");
//                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
//                response.setHeader("Access-Control-Max-Age", "86400");
//                response.setHeader("Access-Control-Allow-Headers", "*");
//                return false;
//            }
            String accessToken = request.getHeader("token"); //从header中得到token
            if (accessToken == null) {
                accessToken = request.getParameter("token");
            }

            if (StringUtils.isNotBlank(accessToken)) {
                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims == null) {
                    //告诉登录过期，重新登录
                    sendJsonMessage(response, JsonData.buildError("登录过期，重新登录"));
                    return false;
                }

                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id", id);  //把取出来的参数放进request里，controller才可以获取参数
                request.setAttribute("name", name);

                return true;//返回true 拦截器放行 继续往下走

            }

        }catch (Exception e){}
        sendJsonMessage(response, JsonData.buildError("登录过期，重新登录")); //否则拦截器不放行，通知前端（返回一个response，类型jsondata
        return false;
    }




    /**
     * 自定义方法，响应json数据给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            //writer:把object写成json
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));

            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }


    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
