package com.cpl.tool.service;

import org.springframework.stereotype.Service;

import com.cpl.tool.manager.SkinFolders;

@Service
public class StartSkinService {
	public void startSkin(String skinName) {
		SkinFolders folers = new SkinFolders();
		folers.startNewSkin(skinName);
		System.out.println("create skin dirs");
	}
}
