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
import com.cpl.tool.service.ColorsService;
import com.cpl.tool.service.FontService;
import com.cpl.tool.service.KbBgService;
import com.cpl.tool.service.KeyDetailUploaderService;
import com.cpl.utils.FileUtils;

@Controller
public class UploadController {
	@Resource
	HttpServletRequest mRequest;
	
	@Resource
	KeyDetailUploaderService mKeyService;
	
	@Resource
	FontService mFontService;
	
	@Resource
	KbBgService mKbService;
	
	@Resource
	ColorsService mColorService;
	
	@RequestMapping("/uploadkeybgs")
	public ModelAndView recieveKeyBgFile(@RequestParam("1080") MultipartFile[] imgs1080,
			@RequestParam("720") MultipartFile[] imgs720,
			@RequestParam("480") MultipartFile[] imgs480) {
		//TODO cpl! move this to service 
		System.out.println("go save keys");
		mKeyService.save(imgs1080, imgs720, imgs480, mRequest);
		Object ini = mRequest.getSession().getAttribute(NetworkConstants.SESSION_1080_IMAGE);
		System.out.println("finish save and the ini 1080 is : " + ini);
		return new ModelAndView("editAllKeys"); 
	}
	
	@RequestMapping("/uploadFonts")
	public ModelAndView recieveUploadFont(@RequestParam("normalFont") MultipartFile normalFont) {
		//TODO cpl! move this to service 
		System.out.println("go save font");
		mFontService.save(normalFont, mRequest);
		System.out.println("finish save normalFont  ");
		return new ModelAndView("mainpage"); 
	}
	
	
	@RequestMapping("/uploadKbBg")
	public ModelAndView recieveUploadBg(@RequestParam("keyboardBg") MultipartFile kbBG, 
			@RequestParam("candBg") MultipartFile candsBG,
			@RequestParam("previewBg") MultipartFile previewPng) {
		//TODO cpl! move this to service 
		System.out.println("go save bg");
		mKbService.save(kbBG, candsBG, previewPng, mRequest);
		System.out.println("finish save bg  ");
		return new ModelAndView("mainpage"); 
	}
	
	@RequestMapping("/colors")
	public ModelAndView recieveColors() {
		//TODO cpl! move this to service 
		System.out.println("go save bg");
		mColorService.save(mRequest);
		System.out.println("finish save colors  ");
		return new ModelAndView("mainpage"); 
	}
	
	
}
