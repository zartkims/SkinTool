package com.cpl.tool.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cpl.tool.service.LoginService;

@Controller
public class LoginController {
	
	@Resource
	LoginService mService;
//	@Resource
//	CplService mService;
	
	@Resource
	HttpServletRequest mRequest;
	
	@Resource
	HttpServletResponse mResponse;
	
//	@RequestMapping("guide")
//	public ModelAndView toLoginPage() {
//		System.out.println("in here !!!!");
//		ModelAndView modelAndView = new ModelAndView("guide");
//		return modelAndView;
//	}
//	
	@RequestMapping("login")
	public ModelAndView doLogin() {
		System.out.println("what the fuxk4");
		String userName = mRequest.getParameter("username");
		String password = mRequest.getParameter("password");
		boolean res = mService.doLogin(userName, password);
		System.out.println("res : " + res);
		if (res) {
			return new ModelAndView("redirect:/page/success");
		} else {
			Map<String, String> errorInfo = new HashMap<String, String>();
			errorInfo.put("error", "username or password error");
			mRequest.setAttribute("username", userName);
			return new ModelAndView("login", errorInfo);
		}
	}
	
}
