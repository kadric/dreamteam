package edu.cth.dzenkyair.frontend.auth;

import edu.cth.dzenkyair.backend.core.Groups;
import edu.cth.dzenkyair.frontend.session.FlightSession;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dženan
 */
@WebFilter("/faces/private/admin/*")
public class AdminFilter implements Filter {

    @Inject
    private FlightSession flightSession;
    
    @Override
    public void init(FilterConfig config) throws ServletException {
        ;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (flightSession.getUser().getGroups().get(0) == Groups.USER) {
            response.sendRedirect(request.getContextPath() + "/faces/pages/login.xhtml"); // No logged-in user found, so redirect to login page.
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        ;
    }
}
