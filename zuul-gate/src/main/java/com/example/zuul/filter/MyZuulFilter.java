package com.example.zuul.filter;

import com.example.zuul.jwt.JwtTokenUtil;
import com.example.zuul.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyZuulFilter extends ZuulFilter{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Value("${auth.token-header}")
    private String tokenHeader;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response=ctx.getResponse();


        String uri=request.getRequestURI();

        System.out.println(uri);
        //设置不拦截的地址
        if(uri.contains("/auth")|| uri.contains("/login")
                || uri.contains("/ui")
                || uri.contains("/css")
                || uri.contains("/js")
                || uri.endsWith(".css")
                || uri.endsWith(".js")){

            return null;
        }

        String token = request.getHeader(tokenHeader);

        System.out.println("from header--"+token);


        //校验token
        if (StringUtils.isBlank(token) || token.equals("null") || jwtTokenUtil.isTokenExpired(token)) {

            System.out.println(uri);

            try {
                response.setStatus(401);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //设置为false的话return之后就不往下走了，如果设置成true，则请求还会继续前进
            ctx.setSendZuulResponse(false);

            ctx.setResponse(response);

            return null;
        } else {
            //TODO 根据token获取相应的登录信息，进行校验（略）
            ctx.setSendZuulResponse(true);
            ctx.addZuulRequestHeader("Authorization",token);
            System.out.println("验证通过");

        }


        System.out.println("我在zuul filter里面");
        return null;
    }


}
