package com.cpl.tool.service;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpl.bean.INIFile;
import com.cpl.bean.INIFile.INIProperty;
import com.cpl.constants.INICode;
import com.cpl.constants.NetworkConstants;
import com.cpl.tool.manager.SkinFolders;
import com.cpl.utils.FileUtils;

@Service
public class KeyDetailUploaderService {
	private boolean mChangeAnything = false;
	public void save(MultipartFile[] imgs1080, MultipartFile[] imgs720, MultipartFile[] imgs480,
			HttpServletRequest mRequest) {
		String keySection = mRequest.getParameter(NetworkConstants.PARAMS_KEY_SECTION_NAME);
		String imageSection = "IMG_" + keySection;
		System.out.println("imageSession : " + imageSection);
		HttpSession session = mRequest.getSession();
		
		String skinName = (String) session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		INIFile img1080INI = getINI(session, NetworkConstants.SESSION_1080_IMAGE, SkinFolders.get_SKIN_1080_IMAGE(skinName));
		INIFile img720INI = getINI(session, NetworkConstants.SESSION_720_IMAGE, SkinFolders.get_SKIN_720_IMAGE(skinName));
		INIFile img480INI = getINI(session, NetworkConstants.SESSION_480_IMAGE, SkinFolders.get_SKIN_480_IMAGE(skinName));
		INIFile skinINI = getINI(session, NetworkConstants.SESSION_SKIN_INI, SkinFolders.get_SKIN_ROOT_SKIN_INI(skinName));

		String res1080Dir = SkinFolders.get_SKIN_1080_RES_DIR(skinName);
		String res720Dir = SkinFolders.get_SKIN_720_RES_DIR(skinName);
		String res480Dir = SkinFolders.get_SKIN_480_RES_DIR(skinName);
		//
//		for test
//		FileUtils.clearDir(new File(res1080Dir));
//		FileUtils.clearDir(new File(res720Dir));
//		FileUtils.clearDir(new File(res480Dir));
		mChangeAnything = false;
		saveFiles(imgs1080, res1080Dir, imageSection, img1080INI, skinINI, keySection);
		saveFiles(imgs720, res720Dir, imageSection, img720INI, skinINI, keySection);
		saveFiles(imgs480, res480Dir, imageSection, img480INI, skinINI, keySection);
		if (mChangeAnything) skinINI.save();
		
	}

	public void saveFiles(MultipartFile[] images, String folder, String imgSection, INIFile imgINIFile, INIFile skinINI, String keySection) {
		try {
			boolean isChange = false;
			System.out.println("N : " + images[0].getOriginalFilename());
			if (isValidateImage(images[0].getOriginalFilename())) {
				isChange |= saveFile(images[0], folder, imgSection, INICode.N, imgINIFile);
				if (keySection.equals(INICode.SECTION_KEY)) {//symbol area 默认和key一样
					imgINIFile.setStringProperty(INICode.SECTION_BG_SYMBOL_AREA, INICode.IMAGE_TYPE, "1", null);
					imgINIFile.setStringProperty(INICode.SECTION_BG_SYMBOL_AREA, INICode.IMAGE, images[0].getOriginalFilename(), null);
				}
			}
			if (isValidateImage(images[1].getOriginalFilename())) {
				isChange |= saveFile(images[1], folder, imgSection, INICode.P, imgINIFile);
			}
			if (isValidateImage(images[2].getOriginalFilename())) {
				isChange |= saveFile(images[2], folder, imgSection, INICode.D, imgINIFile);
			}
			if (isChange) {
				imgINIFile.save();
				skinINI.setStringProperty(keySection, INICode.BG, imgSection, null);
				mChangeAnything = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private boolean saveFile(MultipartFile multiPartFile, String folder, String section, String property, INIFile iniFile) {
		try {
			String fileName = multiPartFile.getOriginalFilename();
			Map<String, INIProperty> props = iniFile.getProperties(section);
			if (props == null) {
				iniFile.addSection(section, null);
				props = iniFile.getProperties(section);
			}
			INIProperty iniProperty = props.get(property);
			if (iniProperty == null) {
				iniProperty = new INIProperty(property, fileName);
				props.put(property, iniProperty);
				File imgFile = new File(folder, fileName); 
				if (!imgFile.exists()) {
					multiPartFile.transferTo(imgFile);
				}
				return true;
			} else {
				String preVal = iniProperty.getPropValue();
				if (!fileName.equals(preVal)) {
					iniProperty.setPropValue(fileName);
					File imgFile = new File(folder, fileName);
					File preFile = new File(folder, preVal);
					FileUtils.deleteFile(preFile);
					if (!imgFile.exists()) {
						multiPartFile.transferTo(imgFile);
					}
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
	
	private INIFile getINI(HttpSession session, String sessionKey, String path) {
		return session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
	}
}
