package com.ideashin.attendance.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter{
	private  String encoding = "UTF-8";


	@Override
	public void destroy() {
		this.encoding = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getInitParameter("ENCODING");
	}

}
