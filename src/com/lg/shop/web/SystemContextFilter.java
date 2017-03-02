package com.lg.shop.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.lg.shop.model.SystemContext;


public class SystemContextFilter implements Filter {
    int pageSize;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		try{
		int pageOffset=0;
		String sort=req.getParameter("sort");
		String order=req.getParameter("order");
		 try{
			 pageOffset=Integer.parseInt(req.getParameter("pager.offset"));
			 


		 }catch(NumberFormatException e){
			 pageOffset=0;
		 }
		 SystemContext.setSort(sort);
		 SystemContext.setOrder(order);
		 SystemContext.setPageSize(pageSize);
		 SystemContext.setPageOffset(pageOffset);
		 chain.doFilter(req, resp);
		
		}finally {
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
		}
		
		 
			

	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
        try{
		pageSize=Integer.parseInt(cfg.getInitParameter("pageSize"));
        }catch(NumberFormatException e){
        	pageSize=15;
        }
				
	}

}
