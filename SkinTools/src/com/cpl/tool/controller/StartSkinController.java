package com.cpl.tool.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cpl.constants.NetworkConstants;
import com.cpl.tool.service.StartSkinService;

@Controller
public class StartSkinController {
	@Resource
	HttpServletRequest mRequest;
	
	@Resource
	HttpServletResponse mResponse;
	
	@Resource
	StartSkinService mService;
	
	@RequestMapping("start")
	public ModelAndView indexGuide() {
		HttpSession session = mRequest.getSession();
		String curSkinName = (String)session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		return new ModelAndView("guide");

//		if (curSkinName == null || "".equals(curSkinName)) {
//			return new ModelAndView("guide");
//		} else {
//			return new ModelAndView("mainpage");
//		}
	}

	@RequestMapping("startskin")
	public ModelAndView startBuildSkin() {
		HttpSession session = mRequest.getSession();
		Object nameObj = session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		if (nameObj != null && !nameObj.equals("")) {
			return new ModelAndView("mainpage", NetworkConstants.PARAMS_KEY_NOTI_CLOSE_LAST_SKIN, true);
		}
		
		String skinName = mRequest.getParameter("skin_name");
		if (skinName == null || "".equals(skinName.trim())) {
			return new ModelAndView("guide", "error", "invalidate skin name");
		}
		session.setAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN, skinName);
		mService.startSkin(skinName);
		ModelAndView res = new ModelAndView();
		res.setViewName("mainpage");
		return res;
	}
	
	@RequestMapping("finishEdit")
	public ModelAndView finisEditCurSkin() {
		HttpSession session = mRequest.getSession();
		session.setAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN, null);
		return new ModelAndView("guide"); 
	}
}
