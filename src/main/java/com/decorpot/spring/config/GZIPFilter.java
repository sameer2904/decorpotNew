package com.decorpot.spring.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
public class GZIPFilter implements Filter
{
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String acceptEncoding = httpRequest.getHeader(HttpHeaders.ACCEPT_ENCODING);
        if (acceptEncoding != null)
        {
            System.out.println("Processing GZIPFilter");
            //searching for 'gzip' in ACCEPT_ENCODING header
            if (acceptEncoding.indexOf("gzip") >= 0)
            {
                               GZIPResponseWrapper gzipResponse = new GZIPResponseWrapper(httpResponse);
                 chain.doFilter(request, gzipResponse);
                 gzipResponse.finish();
                 return;
            }
            }
            chain.doFilter(request, response);
        }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}