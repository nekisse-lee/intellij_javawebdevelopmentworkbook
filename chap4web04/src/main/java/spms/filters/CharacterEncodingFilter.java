package spms.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter{
    FilterConfig config;

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
            throws IOException, ServletException {
            // 서블릿을 실행하기 전에 수행할 작업
        request.setCharacterEncoding("UTF-8");

        nextFilter.doFilter(request, response);

        //서블릿을 실행한 후 수행할 작업


    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
}
