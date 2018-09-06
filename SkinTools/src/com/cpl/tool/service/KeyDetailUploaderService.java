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
		//
		String res1080Dir = SkinFolders.get_SKIN_1080_RES_DIR(skinName);
		String res720Dir = SkinFolders.get_SKIN_720_RES_DIR(skinName);
		String res480Dir = SkinFolders.get_SKIN_480_RES_DIR(skinName);
		//
		String [] scale1080 = new String[3];
		String [] scale720 = new String[3];
		String [] scale480 = new String[3];
		scale1080[0] = (String) mRequest.getParameter(NetworkConstants.PARAMS_N_1080);
		scale1080[1] = (String) mRequest.getParameter(NetworkConstants.PARAMS_P_1080);
		scale1080[2] = (String) mRequest.getParameter(NetworkConstants.PARAMS_D_1080);
		scale720[0] = (String) mRequest.getParameter(NetworkConstants.PARAMS_N_720);
		scale720[1] = (String) mRequest.getParameter(NetworkConstants.PARAMS_P_720);
		scale720[2] = (String) mRequest.getParameter(NetworkConstants.PARAMS_D_720);
		scale480[0] = (String) mRequest.getParameter(NetworkConstants.PARAMS_N_480);
		scale480[1] = (String) mRequest.getParameter(NetworkConstants.PARAMS_P_480);
		scale480[2] = (String) mRequest.getParameter(NetworkConstants.PARAMS_D_480);
		
		System.out.println("1080 scale : " + scale1080[0]);
		//
//		for test
//		FileUtils.clearDir(new File(res1080Dir));
//		FileUtils.clearDir(new File(res720Dir));
//		FileUtils.clearDir(new File(res480Dir));
		mChangeAnything = false;
		saveFiles(imgs1080, scale1080, res1080Dir, imageSection, img1080INI, skinINI, keySection);
		saveFiles(imgs720, scale720, res720Dir, imageSection, img720INI, skinINI, keySection);
		saveFiles(imgs480, scale480, res480Dir, imageSection, img480INI, skinINI, keySection);
		if (mChangeAnything) skinINI.save();
		
	}

	public void saveFiles(MultipartFile[] images, String[] scaleStrs, String folder, String imgSection, INIFile imgINIFile, INIFile skinINI, String keySection) {
		try {
			boolean isChange = false;
			if (isValidateImage(images[0].getOriginalFilename())) {
				isChange |= saveFile(images[0], folder, imgSection, INICode.N, scaleStrs[0], imgINIFile);
				if (keySection.equals(INICode.SECTION_KEY)) {//symbol area 默认和key一样
					String scale = getValideScaleString(scaleStrs[0]);
					String saveFileName = images[0].getOriginalFilename();
					if (scale != null) {
						saveFileName = get9PathFileName(saveFileName);
					} else {
						scale = "";
					}
					imgINIFile.setStringProperty(INICode.SECTION_BG_SYMBOL_AREA, INICode.IMAGE_TYPE, "1", null);
					imgINIFile.setStringProperty(INICode.SECTION_BG_SYMBOL_AREA, INICode.IMAGE, saveFileName + scale, null);
				}
			}
			if (isValidateImage(images[1].getOriginalFilename())) {
				
				isChange |= saveFile(images[1], folder, imgSection, INICode.P, scaleStrs[1], imgINIFile);
			}
			if (isValidateImage(images[2].getOriginalFilename())) {
				isChange |= saveFile(images[2], folder, imgSection, INICode.D, scaleStrs[2], imgINIFile);
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
	
	private boolean saveFile(MultipartFile multiPartFile, String folder, String section, String property, String scaleStr, INIFile iniFile) {
		try {
			String scale = getValideScaleString(scaleStr);
			String saveFileName = multiPartFile.getOriginalFilename();
			if (scale != null) {
				saveFileName = get9PathFileName(saveFileName);
			} else {
				scale = "";
			}
			
			String fileName = saveFileName;
			Map<String, INIProperty> props = iniFile.getProperties(section);
			if (props == null) {
				iniFile.addSection(section, null);
				props = iniFile.getProperties(section);
			}
			INIProperty iniProperty = props.get(property);
			if (iniProperty == null) {
				iniProperty = new INIProperty(property, fileName + scale);
				props.put(property, iniProperty);
				File imgFile = new File(folder, fileName); 
				if (!imgFile.exists()) {
					multiPartFile.transferTo(imgFile);
				}
				return true;
			} else {
				String preVal = iniProperty.getPropValue();
				if (!(fileName+scale).equals(preVal)) {
					iniProperty.setPropValue(fileName + scale);
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
	
	private String getValideScaleString(String str) {
		if (str == null || str.split(",").length != 4) return null;
		return ","+str;
	}
	
	private String get9PathFileName(String oriName) {
		if (oriName == null) return null;
		String suf = oriName.substring(oriName.lastIndexOf("."));
		String simpleName = oriName.substring(0, oriName.lastIndexOf("."));
		return simpleName + INICode.NINE_PATCH_SUFFIX + suf;
	}
	
	private boolean isValidateImage(String fileName) {
		if (fileName == null || fileName.trim().equals("")) return false;
		return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg");
	}
	
	private INIFile getINI(HttpSession session, String sessionKey, String path) {
		return session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
	}
}
