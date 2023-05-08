package com.gym1.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        } else {
            String token = request.getHeader("token");
            Map<String, Object> map = new HashMap<>();
            try {
                boolean verify = JwtUtil.checkToken(token);
                if (verify) {
                    map.put("state", true);
                    //convert map to jason
                    String json = new ObjectMapper().writeValueAsString(map);
                    response.setContentType("application/json;charset=UTF-8");
                    return true;
                } else {
                    map.put("state", false);
                    String json = new ObjectMapper().writeValueAsString(map);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getOutputStream().println(json);
                    return false;
                }
            } catch (SignatureException e) {
                e.printStackTrace();
                map.put("message", "Invalid token!");
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
                map.put("message", "The token format is not supported!");
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
                map.put("message", "The token is overdue!");
            } catch (MalformedJwtException e) { // IllegalArgumentException
                e.printStackTrace();
                map.put("message", "The token format is not supported!");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("message", "The token is invalid!");
            }
            map.put("state", false);

            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getOutputStream().println(json);
            return false;
        }
    }
}
