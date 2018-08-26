package com.cpl.tool.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkinFontController {
	@Resource
	HttpServletRequest mRequest;
	
	@RequestMapping("/upload_iconfont")
	public ModelAndView uploadIconFont() {
		System.out.println("miaomiaomiao??");
		String curSkinName = mRequest.getParameter("skin_name");
		System.out.println(curSkinName + " now upload iconfont");
		return new ModelAndView("fonts", "skin_name", curSkinName);
	}
}
