package com.cpl.tool.manager;

import java.io.File;

import com.cpl.utils.FileUtils;

public class SkinFolders {
//	private static String skinName;
	
	private static final String ALL_SKIN_ROOT = "allSkins";
	private static String SKIN_ROOT_DIR = ALL_SKIN_ROOT + "/curSkin";

	
	private static String SKIN_ROOT_RES_DIR = SKIN_ROOT_DIR + "/res";
	private static String SKIN_ROOT_COLORS = SKIN_ROOT_DIR + "/colors.ini";
	private static String SKIN_ROOT_PREVIEW = SKIN_ROOT_DIR + "/preview.png";
	private static String SKIN_ROOT_SKIN_INI = SKIN_ROOT_DIR + "/skin.ini";
	private static String SKIN_ROOT_CONFIG = SKIN_ROOT_DIR + "/config";
	
	private static String SKIN_480_DIR = SKIN_ROOT_DIR + "/480";
	private static String SKIN_720_DIR = SKIN_ROOT_DIR + "/720";
	private static String SKIN_1080_DIR = SKIN_ROOT_DIR + "/1080";
	private static String SKIN_480_RES_DIR = SKIN_480_DIR + "/res";
	private static String SKIN_480_IMAGE = SKIN_480_DIR + "/image";
	private static String SKIN_720_RES_DIR = SKIN_720_DIR + "/res";
	private static String SKIN_720_IMAGE = SKIN_720_DIR + "/image";
	private static String SKIN_1080_RES_DIR = SKIN_1080_DIR + "/res";
	private static String SKIN_1080_IMAGE = SKIN_1080_DIR + "/image";

	private SkinFolders() {
	}
	
	public static void startNewSkin(String skinName) {
		invalidatePath(skinName);
//		FileUtils.clearDir(new File(SKIN_ROOT_DIR));
		createAllDir(skinName);
	}
	
	private static void createAllDir(String skinName) {
		invalidatePath(skinName);
		FileUtils.createDir(ALL_SKIN_ROOT);
		FileUtils.createDir(SKIN_480_DIR);
		FileUtils.createDir(SKIN_720_DIR);
		FileUtils.createDir(SKIN_1080_DIR);
		FileUtils.createDir(SKIN_480_RES_DIR);
		FileUtils.createDir(SKIN_720_RES_DIR);
		FileUtils.createDir(SKIN_1080_RES_DIR);
	}
	
	
	private static void invalidatePath(String skinName) {
		SKIN_ROOT_DIR = ALL_SKIN_ROOT + "/" + skinName;
		SKIN_ROOT_RES_DIR = SKIN_ROOT_DIR + "/res";
		SKIN_ROOT_COLORS = SKIN_ROOT_DIR + "/colors.ini";
		SKIN_ROOT_PREVIEW = SKIN_ROOT_DIR + "/preview.png";
		SKIN_ROOT_SKIN_INI = SKIN_ROOT_DIR + "/skin.ini";
		SKIN_ROOT_CONFIG = SKIN_ROOT_DIR + "/config";
		
		SKIN_480_DIR = SKIN_ROOT_DIR + "/480";
		SKIN_720_DIR = SKIN_ROOT_DIR + "/720";
		SKIN_1080_DIR = SKIN_ROOT_DIR + "/1080";
		SKIN_480_RES_DIR = SKIN_480_DIR + "/res";
		SKIN_480_IMAGE = SKIN_480_DIR + "/image";
		SKIN_720_RES_DIR = SKIN_720_DIR + "/res";
		SKIN_720_IMAGE = SKIN_720_DIR + "/image";
		SKIN_1080_RES_DIR = SKIN_1080_DIR + "/res";
		SKIN_1080_IMAGE = SKIN_1080_DIR + "/image";
	}

	public static String get_SKIN_ROOT_DIR(String skinName) {
		return ALL_SKIN_ROOT + "/" + skinName;
	}

	public static String get_SKIN_ROOT_RES_DIR(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/res";
	}

	public static String get_SKIN_ROOT_COLORS(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/colors.ini";
	}

	public static String get_SKIN_ROOT_PREVIEW(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/preview.png";
	}

	public static String get_SKIN_ROOT_SKIN_INI(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/skin.ini";
	}

	public static String get_SKIN_ROOT_CONFIG(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/config";
	}

	public static String get_SKIN_480_DIR(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/480";
	}

	public static String get_SKIN_720_DIR(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/720";
	}

	public static String get_SKIN_1080_DIR(String skinName) {
		return get_SKIN_ROOT_DIR(skinName) + "/1080";
	}

	public static String get_SKIN_480_RES_DIR(String skinName) {
		return get_SKIN_480_DIR(skinName) + "/res";
	}

	public static String get_SKIN_480_IMAGE(String skinName) {
		return get_SKIN_480_DIR(skinName) + "/image";
	}

	public static String get_SKIN_720_RES_DIR(String skinName) {
		return get_SKIN_720_DIR(skinName) + "/res";
	}

	public static String get_SKIN_720_IMAGE(String skinName) {
		return get_SKIN_720_DIR(skinName) + "/image";
	}

	public static String get_SKIN_1080_RES_DIR(String skinName) {
		return get_SKIN_1080_DIR(skinName) + "/res";
	}

	public static String get_SKIN_1080_IMAGE(String skinName) {
		return get_SKIN_1080_DIR(skinName) + "/image";
	}
	
	
}
