package pers.ycy.itoken.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyZuulFilter extends ZuulFilter {


    @Override
    public String filterType() {
        /* 过滤器类型, 在请求之前执行. */
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        /* 开启拦截器.  也可以指定在什么情况下 开启拦截器. */
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /* 如果条件合适 则放行, 否则不放行. */

        RequestContext requestContext = RequestContext.getCurrentContext();

        System.out.println("不放");

        System.out.println(requestContext.getRequest());
        System.out.println(requestContext.getRequest().getRemoteAddr());
        System.out.println(requestContext.get("coyoteRequest"));


        requestContext.setSendZuulResponse(true);


        return null;
    }
}
