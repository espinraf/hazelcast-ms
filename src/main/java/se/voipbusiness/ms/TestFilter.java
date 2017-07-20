package se.voipbusiness.ms;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FILTER CONFIGURATION 1..................");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FILTER DO 1..................");
        //HttpServletResponse httpResponse = (HttpServletResponse) response;
        //httpResponse.sendRedirect("/static/index.html");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("FILTER DESTROY 1..................");
    }
    // implements Filter's methods here...
}