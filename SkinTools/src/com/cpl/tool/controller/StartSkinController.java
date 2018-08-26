package com.cpl.tool.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cpl.tool.service.StartSkinService;

@Controller
public class StartSkinController {
	@Resource
	HttpServletRequest mRequest;
	
	@Resource
	StartSkinService mService;
	
	@RequestMapping("startskin")
	public ModelAndView startBuildSkin() {
		String skinName = mRequest.getParameter("skin_name");
		if (skinName == null || "".equals(skinName.trim())) {
			return new ModelAndView("guide", "error", "invalidate skin name");
		}
		mService.startSkin(skinName);
		ModelAndView res = new ModelAndView();
		res.addObject("cur_skin_name", skinName);
		res.setViewName("mainpage");
		return res;
	}
}
