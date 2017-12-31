package demo.service_gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.filters.StaticResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class PreRequestAuthFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(PreRequestAuthFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // RequestContext is a shared among threads.
        // used for get all information about request and response.
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String password = request.getParameter("password");
        if (password != null && password.equals("123456")) {
//            ctx.setSendZuulResponse(true);
//            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            log.info("Authentication successed.");
        }
        else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
            ctx.set("isSuccess", false);
            log.info(String.format("Authentication failed: password not correct"));
        }
        return null;
    }
}
