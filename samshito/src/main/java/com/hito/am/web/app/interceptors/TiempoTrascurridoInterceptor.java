package com.hito.am.web.app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hito.am.web.app.controllers.IndexController;

@Component
public class TiempoTrascurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("TiempoTrascurridoInterceptor:preHandle() entrando...");
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("carga", tiempoInicio);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("TiempoTrascurridoInterceptor:preHandle() entrando...");
	}

}
