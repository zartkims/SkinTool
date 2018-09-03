package com.cpl.tool.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cpl.bean.INIFile;
import com.cpl.constants.NetworkConstants;
import com.cpl.tool.manager.SkinFolders;

@Service
public class StartSkinService {
	public void startSkin(String skinName) {
		SkinFolders.startNewSkin(skinName);
		System.out.println("create skin dirs");
	}

	public INIFile readSkinINI(String skinName, HttpSession session) {
		INIFile skinINI = new INIFile(SkinFolders.get_SKIN_ROOT_SKIN_INI(skinName));
		//假装从文件读了
		return skinINI;
		
	}

	public void readImageINI(String skinName, HttpSession session) {
		
		String image1080 = SkinFolders.get_SKIN_1080_IMAGE(skinName);
		String image720 = SkinFolders.get_SKIN_720_IMAGE(skinName);
		String image480 = SkinFolders.get_SKIN_480_IMAGE(skinName);
		INIFile img1080INI = new INIFile(image1080);
		INIFile img720INI = new INIFile(image720);
		INIFile img480INI = new INIFile(image480);
		//假装从文件读了
		session.setAttribute(NetworkConstants.SESSION_1080_IMAGE, img1080INI);
		session.setAttribute(NetworkConstants.SESSION_720_IMAGE, img720INI);
		session.setAttribute(NetworkConstants.SESSION_480_IMAGE, img480INI);
	}
}
