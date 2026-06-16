package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;
import java.io.IOException;

/**
 * 令牌校验过滤器
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    private org.example.util.JwtUtils JwtUtils;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 使用ServletPath精准获取接口路径
        String path = request.getServletPath();

        // 白名单：首页、静态资源、登录接口全部放行，不走token校验
        boolean pass = "/".equals(path)
                || "/index.html".equals(path)
                || path.startsWith("/assets/")
                || "/favicon.ico".equals(path)
                || path.equals("/api/login"); // 精准匹配登录接口

        if (pass) {
            log.info("白名单路径，直接放行：{}", path);
            chain.doFilter(request, response);
            return;
        }

        // 获取token
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(request, response);
    }

}