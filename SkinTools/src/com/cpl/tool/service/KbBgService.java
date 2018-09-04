package com.cpl.tool.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpl.bean.INIFile;
import com.cpl.constants.INICode;
import com.cpl.constants.NetworkConstants;
import com.cpl.tool.manager.SkinFolders;
import com.cpl.utils.FileUtils;

@Service
public class KbBgService {

	public void save(MultipartFile kbBG, MultipartFile candsBG, HttpServletRequest mRequest) {
		HttpSession session = mRequest.getSession();
		String skinName = (String) session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		INIFile img1080INI = getINI(session, NetworkConstants.SESSION_1080_IMAGE, SkinFolders.get_SKIN_1080_IMAGE(skinName));
		INIFile img720INI = getINI(session, NetworkConstants.SESSION_720_IMAGE, SkinFolders.get_SKIN_720_IMAGE(skinName));
		INIFile img480INI = getINI(session, NetworkConstants.SESSION_480_IMAGE, SkinFolders.get_SKIN_480_IMAGE(skinName));
//		String res1080Dir = SkinFolders.get_SKIN_1080_RES_DIR(skinName);
//		String res720Dir = SkinFolders.get_SKIN_720_RES_DIR(skinName);
//		String res480Dir = SkinFolders.get_SKIN_480_RES_DIR(skinName);
		String rooResDir = SkinFolders.get_SKIN_ROOT_RES_DIR(skinName);
		
		saveFile(kbBG, candsBG, rooResDir, img1080INI);
		saveFile(kbBG, candsBG, rooResDir, img720INI);
		saveFile(kbBG, candsBG, rooResDir, img480INI);
		
	}
	
	private void saveFile(MultipartFile kbBG, MultipartFile candsBG, String resDir, INIFile imgINI) {
		try {
			boolean isChange = false;
			if (isValidateImage(kbBG.getOriginalFilename())) {
				isChange = true;
				String pre = imgINI.getStringProperty(INICode.SECTION_KB_BG, INICode.IMAGE);
				if (pre != null && !pre.equals("")) {
					FileUtils.deleteFile(new File(resDir, pre));
				}
				File curImage = new File(resDir, kbBG.getOriginalFilename());
				kbBG.transferTo(curImage);
				imgINI.setStringProperty(INICode.SECTION_KB_BG, INICode.IMAGE_TYPE, "1", null);
				imgINI.setStringProperty(INICode.SECTION_KB_BG, INICode.IMAGE, kbBG.getOriginalFilename(), null);
			}
			if (isValidateImage(candsBG.getOriginalFilename())) {
				isChange = true;
				String pre = imgINI.getStringProperty(INICode.SECTION_BG_CANDS, INICode.IMAGE);
				if (pre != null && !pre.equals("")) {
					FileUtils.deleteFile(new File(resDir, pre));
				}
				File curImage = new File(resDir, candsBG.getOriginalFilename());
				candsBG.transferTo(curImage);
				imgINI.setStringProperty(INICode.SECTION_BG_CANDS, INICode.IMAGE_TYPE, "1", null);
				imgINI.setStringProperty(INICode.SECTION_BG_CANDS, INICode.IMAGE, candsBG.getOriginalFilename(), null);
			}
			if (isChange) {
				imgINI.save();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isValidateImage(String fileName) {
		if (fileName == null || fileName.trim().equals("")) return false;
		return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg");
	}

	private INIFile getINI(HttpSession session, String sessionKey, String path) {
		return session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
	}
}
