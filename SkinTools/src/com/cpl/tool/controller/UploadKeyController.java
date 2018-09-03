package com.cpl.tool.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cpl.constants.INICode;
import com.cpl.constants.NetworkConstants;
import com.cpl.tool.manager.SkinFolders;
import com.cpl.tool.service.KeyDetailUploaderService;
import com.cpl.utils.FileUtils;

@Controller
public class UploadKeyController {
	@Resource
	HttpServletRequest mRequest;
	
	@Resource
	KeyDetailUploaderService mService;
	
	@RequestMapping("/uploadfiles")
	public ModelAndView recieveUploadFile(@RequestParam("1080") MultipartFile[] imgs1080,
			@RequestParam("720") MultipartFile[] imgs720,
			@RequestParam("480") MultipartFile[] imgs480) {
		//TODO cpl! move this to service 
		System.out.println("go save");
		mService.save(imgs1080, imgs720, imgs480, mRequest);
		Object ini = mRequest.getSession().getAttribute(NetworkConstants.SESSION_1080_IMAGE);
		System.out.println("finish save and the ini 1080 is : " + ini);
		return new ModelAndView("editAllKeys"); 
	}
	
	
}
