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

@Controller
public class UploadKeyController {
	@Resource
	HttpServletRequest mRequest;
	
	@RequestMapping("/uploadfiles")
	public String recieveUploadFile(@RequestParam("1080") MultipartFile[] imgs1080,
			@RequestParam("720") MultipartFile[] imgs720,
			@RequestParam("480") MultipartFile[] imgs480) {
		//TODO cpl! move this to service 
		String keySectionName = (String) mRequest.getAttribute(NetworkConstants.PARAMS_KEY_SECTION_NAME);
		String imageSession = "IMG_" + keySectionName;
		HttpSession session = mRequest.getSession();
		String skinName = (String) session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		INIFile img1080INI = getImgeINI(session, NetworkConstants.SESSION_1080_IMAGE, SkinFolders.get_SKIN_1080_IMAGE(skinName));
		INIFile img720INI = getImgeINI(session, NetworkConstants.SESSION_720_IMAGE, SkinFolders.get_SKIN_720_IMAGE(skinName));
		INIFile img480INI = getImgeINI(session, NetworkConstants.SESSION_480_IMAGE, SkinFolders.get_SKIN_480_IMAGE(skinName));
		
		String res1080Dir = SkinFolders.get_SKIN_1080_RES_DIR(skinName);
		String res720Dir = SkinFolders.get_SKIN_1080_RES_DIR(skinName);
		String res480Dir = SkinFolders.get_SKIN_1080_RES_DIR(skinName);
		
		
		saveFiles(imgs1080, res1080Dir, imageSession, img1080INI);
		saveFiles(imgs720, res720Dir, keySectionName, img720INI);
		saveFiles(imgs480, res480Dir, keySectionName, img480INI);
		
		System.out.println("here !!!!! " + imgs1080.length);
		return "mainpage";
	}
	
	public void saveFiles(MultipartFile[] images, String folder, String section, INIFile iniFile) {
		try {
			boolean isChange = false;
			//TODO cpl!之后还要清空之前的文件
			if (isValidateImage(images[0].getOriginalFilename())) {
				isChange |= saveFile(images[0], folder, section, INICode.N, iniFile);
			}
			if (isValidateImage(images[1].getOriginalFilename())) {
				isChange |= saveFile(images[1], folder, section, INICode.P, iniFile);
			}
			if (isValidateImage(images[2].getOriginalFilename())) {
				isChange |= saveFile(images[2], folder, section, INICode.D, iniFile);
			}
			if (isChange) {
				iniFile.wirteSelf();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private boolean saveFile(MultipartFile multiPartFile, String folder, String section, String property, INIFile iniFile) {
		try {
			String fileName = multiPartFile.getOriginalFilename();
			String preVal = iniFile.getProperty(section, INICode.N);
			if (!fileName.equals(preVal)) {
				iniFile.put(section, INICode.N, fileName);
				File imgFile = new File(folder, fileName); 
				if (!imgFile.exists()) {
					multiPartFile.transferTo(imgFile);
					multiPartFile.getInputStream().close();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isValidateImage(String fileName) {
		if (fileName == null || fileName.trim().equals("")) return false;
		return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg");
	}
	
	private INIFile getImgeINI(HttpSession session, String sessionKey, String path) {
		return session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
	}
}
