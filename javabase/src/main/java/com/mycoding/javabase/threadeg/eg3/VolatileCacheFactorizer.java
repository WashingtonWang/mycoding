package com.mycoding.javabase.threadeg.eg3;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class VolatileCacheFactorizer implements Servlet{
    private volatile OneValueCache cache = new OneValueCache(null,null);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null){
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
    }

    private BigInteger[] factor(BigInteger bi){
        BigInteger[] bis = new BigInteger[]{};
        bis[0] = new BigInteger("1");
        return bis;
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest){
        BigInteger bi = new BigInteger("3");
        return bi;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
