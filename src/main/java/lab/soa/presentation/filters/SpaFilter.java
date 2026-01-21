package lab.soa.presentation.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class SpaFilter implements Filter {
    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (path.startsWith("/api/v1/")) {
            chain.doFilter(request, response);
            return;
        }
        if (path.contains(".") && !path.endsWith(".html")) {
            chain.doFilter(request, response);
            return;
        }
        request.getRequestDispatcher("/index.html").forward(request, response);
    }
}
