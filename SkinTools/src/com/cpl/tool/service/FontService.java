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
public class FontService {

	public void save(MultipartFile normalFont, HttpServletRequest mRequest) {
		try {
			HttpSession session = mRequest.getSession();
			String skinName = (String) session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
			INIFile skinINI = getINI(session, NetworkConstants.SESSION_SKIN_INI, SkinFolders.get_SKIN_ROOT_SKIN_INI(skinName));
			String resDir = SkinFolders.get_SKIN_ROOT_RES_DIR(skinName);
			if (normalFont != null) {
				if (normalFont.getOriginalFilename().endsWith(".ttf")) {
					System.out.println("right iconfont");
					String preTypeFace = skinINI.getStringProperty(INICode.SECTION_TEXT_DEF_DOUBLE_STYLE, INICode.TYPEFACE);
					//此处由于大概率名字一样，因此最好无脑删一次
					if (preTypeFace != null && !"".equals(preTypeFace)) {
						File preFile = new File(resDir, preTypeFace);
						FileUtils.deleteFile(preFile);
					}
					File curFile = new File(resDir, normalFont.getOriginalFilename());
					normalFont.transferTo(curFile);
					//
					skinINI.setStringProperty(INICode.SECTION_TEXT_DEF_DOUBLE_STYLE, INICode.TYPEFACE, normalFont.getOriginalFilename(), null);
					skinINI.setStringProperty(INICode.SECTION_TEXT_DEF_SINGLE_STYLE, INICode.TYPEFACE, normalFont.getOriginalFilename(), null);
					skinINI.save();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private INIFile getINI(HttpSession session, String sessionKey, String path) {
		return session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
	}
}
