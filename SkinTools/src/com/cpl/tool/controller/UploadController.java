package com.cpl.tool.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping("/downloadSkinInner")
	public ResponseEntity<byte[]> downloadSkinInner() {
		//TODO cpl! move this to service 
		System.out.println("go download");
		
		HttpSession session = mRequest.getSession();
		String skinName = (String) session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		
		return downLoadSkinMSP(skinName); 
	}

	@RequestMapping("/downloadSkinOutside")
	public ResponseEntity<byte[]> downloadSkinOutside() {
		String skinName = mRequest.getParameter("skinName");
		System.out.println("go here down load : " + skinName);
		return downLoadSkinMSP(skinName); 
	}
	
	private ResponseEntity<byte[]> downLoadSkinMSP(String skinName) {
		String curSkinRoot = SkinFolders.get_SKIN_ROOT_DIR(skinName);
		File file = new File(curSkinRoot);
		if (file.exists()) {
			//to zip file 
			File targetZipFile = new File(SkinFolders.ALL_SKIN_ROOT,  file.getName() + INICode.MSP_SUFFIX);
			FileUtils.deleteFile(targetZipFile);
			FileUtils.zipFile(file.getPath(), targetZipFile.getPath());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
	        headers.setContentDispositionFormData("attachment", file.getName() + INICode.MSP_SUFFIX);
	        
			try {
				ResponseEntity<byte[]> rsp = new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(targetZipFile),
						headers, HttpStatus.CREATED);
				return rsp;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
