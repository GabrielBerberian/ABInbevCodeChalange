package org.abinbev.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OAuth2Filter implements Filter {

    private final ObjectMapper objectMapper;

    public OAuth2Filter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<Cookie> cookie = extractCookie(request);
        if (cookie.isPresent()) {
            writeCookieResponse(response, cookie);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // This filter doesn't have any initialization details.
    }

    @Override
    public void destroy() {
        // This filter doesn't have any destruction details.
    }

    private Optional<Cookie> extractCookie(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        return cookies == null ? Optional.empty() : Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> cookie.getName().equals("JSESSIONID"))
                .findAny();
    }

    private void writeCookieResponse(HttpServletResponse request, Optional<Cookie> cookie) throws IOException {
        Map<String, String> cookieMap = new HashMap<>();
        cookieMap.put("Cookie", cookie.get().getName() + "=" + cookie.get().getValue());
        String body = objectMapper.writeValueAsString(cookieMap);
        request.setContentLength(body.length());
        request.getWriter().write(body);
    }

}
