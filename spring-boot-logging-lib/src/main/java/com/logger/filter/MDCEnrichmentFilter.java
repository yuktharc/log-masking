import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import java.io.IOException;

public class MDCEnrichmentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // Enrich MDC with request information
            MDC.put("requestId", httpRequest.getHeader("X-Request-ID"));
            MDC.put("userId", httpRequest.getHeader("X-User-ID"));
            MDC.put("clientIp", request.getRemoteAddr());
            // Add more context as needed

            try {
                chain.doFilter(request, response);
            } finally {
                // Clear MDC after request processing
                MDC.clear();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}