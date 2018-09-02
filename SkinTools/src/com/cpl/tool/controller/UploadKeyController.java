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

import com.cpl.constants.INICode;
import com.cpl.constants.INIFile;
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
	public String recieveUploadFile(@RequestParam("1080") MultipartFile[] imgs1080,
			@RequestParam("720") MultipartFile[] imgs720,
			@RequestParam("480") MultipartFile[] imgs480) {
		//TODO cpl! move this to service 
		mService.save(imgs1080, imgs720, imgs480, mRequest);
		return "mainpage";
	}
	
	
}
