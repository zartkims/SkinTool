package com.cpl.tool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadKeyController {
	
	@RequestMapping("/uploadfiles")
	public String recieveUploadFile(@RequestParam("1080") MultipartFile[] imgs1080,
			@RequestParam("720") MultipartFile[] imgs720,
			@RequestParam("480") MultipartFile[] imgs480) {
		for (MultipartFile img : imgs1080) {
			System.out.println("1080 :    " + img.getName() + "   " + img.getOriginalFilename() + "    " + img.getSize());
		}
		for (MultipartFile img : imgs720) {
			System.out.println("720 :     " + img.getName() + "   " + img.getOriginalFilename() + "    " + img.getSize());
		}
		for (MultipartFile img : imgs480) {
			System.out.println("480 :     " + img.getName() + "   " + img.getOriginalFilename() + "    " + img.getSize());
		}
		System.out.println("here !!!!! " + imgs1080.length);
		return "mainpage";
	}
}
