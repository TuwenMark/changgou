package com.changgou.goods.filter;

import com.changgou.entity.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @Program: changgou
 * @Description: 边界日志过滤器
 * @Author: Mr.Ye
 * @Date: 2021-12-27 00:17
 **/
// @Slf4j
@WebFilter(filterName = "boundary", urlPatterns = "/*")
public class BoundaryLogFilter implements Filter {
    // 引入lombok的注解即可不用此句
    private static final Logger LOGGER = LoggerFactory.getLogger(BoundaryLogFilter.class);

    private static final String TRANCE_ID = "tranceId";
    private static final String URI = "uri";
    private static final String BOUNDARY = "boundary";
    private static final String BOUNDARY_IN = "I";
    private static final String BOUNDARY_OUT = "O";
    private static final String STATUS_REGEX = "2\\d{2}";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 父类转换为子类，强制转换为HttpServletRequest
        Long startTime = System.currentTimeMillis();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        MDC.put(TRANCE_ID, getTranceId());
        MDC.put(URI, httpServletRequest.getRequestURI());
        MDC.put(BOUNDARY, BOUNDARY_IN);
        String method = httpServletRequest.getMethod();
        LOGGER.info("{}", method);

        chain.doFilter(httpServletRequest, httpServletResponse);

        MDC.put(BOUNDARY, BOUNDARY_OUT);
        // 获取响应码
        int statusCode = httpServletResponse.getStatus();
        String status = Constants.FAILED;
        if (contains(STATUS_REGEX, Integer.toString(statusCode))) {
            status = Constants.OK;
        }
        LOGGER.info("{}ms|{}|{}", System.currentTimeMillis() - startTime, statusCode, status);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 匹配是否包含
     *
     * @param regex 正则表达式
     * @param source 数据源
     * @return 是否包含
     */
    private static Boolean contains(String regex, String source) {
        return Pattern.compile(regex).matcher(source).matches();
    }

    /**
     * 获取tranceId
     *
     * @return tranceId
     */
    private static String getTranceId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
