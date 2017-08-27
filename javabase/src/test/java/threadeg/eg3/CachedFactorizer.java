package threadeg.eg3;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class CachedFactorizer implements Servlet{

    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits(){
        return hits;
    }

    public synchronized double getCacheHitRatio(){
        return (double) cacheHits / (double)hits;
    }


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
        BigInteger[] factors = null;
        synchronized (this){
            ++hits;
            if (i.equals(lastNumber)){
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null){
            factors = factor(i);
            synchronized (this){
                lastNumber = i;
                lastFactors = factors.clone();
            }
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
