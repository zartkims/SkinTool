package com.cpl.tool.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cpl.constants.NetworkConstants;

@Controller
public class SkinFontController {
	@Resource
	HttpServletRequest mRequest;
	
	@RequestMapping("/upload_iconfont")
	public ModelAndView uploadIconFont() {
		System.out.println("miaomiaomiao??");
		Object nameObj = mRequest.getSession().getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
  		String curSkinName = nameObj != null ? (String) nameObj : "";
		System.out.println(curSkinName + " now upload iconfont");
		return new ModelAndView("fonts");
	}
}
