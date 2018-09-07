package com.cpl.tool.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpl.bean.INIFile;
import com.cpl.constants.INICode;
import com.cpl.constants.NetworkConstants;
import com.cpl.tool.manager.SkinFolders;
import com.cpl.utils.FileUtils;

@Service
public class ColorsService {

	public void save(HttpServletRequest mRequest) {
		HttpSession session = mRequest.getSession();
		String skinName = (String) session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
		INIFile colorsINI = getINI(session, NetworkConstants.SESSION_COLORS_INI, SkinFolders.get_SKIN_ROOT_COLORS(skinName));
		
		String mainColor = mRequest.getParameter(INICode.COLOR_FIR);
		String tabColor = mRequest.getParameter(INICode.COLOR_TAB_SELECT);
		String hlColor = mRequest.getParameter(INICode.COLOR_CANDS_HL);
		
		boolean change = false;
		change |= saveColors(mainColor, INICode.COLOR_FIR, colorsINI);
		change |= saveColors(tabColor, INICode.COLOR_TAB_SELECT, colorsINI);
		change |= saveColors(hlColor, INICode.COLOR_CANDS_HL, colorsINI);
		if (change) {
			System.out.println("change save color file");
			colorsINI.save();
		}
		
	}
	
	private boolean saveColors(String colorStr, String property, INIFile colorINI) {
		colorStr = getValidateColorStr(colorStr);
		if (colorStr == null) return false;
		String pre = colorINI.getStringProperty(INICode.COLORS, property);
		if (colorStr.equals(pre)) return false;
		colorINI.setStringProperty(INICode.COLORS, property, colorStr, null);
		if (INICode.COLOR_FIR.equals(property)) {//添加附属色系
			String sec = changeAlpha(colorStr, "99");
			colorINI.setStringProperty(INICode.COLORS, INICode.COLOR_SEC, sec, null);
			String thi = changeAlpha(colorStr, "66");
			colorINI.setStringProperty(INICode.COLORS, INICode.COLOR_THI, thi, null);
			String fou = changeAlpha(colorStr, "34");
			colorINI.setStringProperty(INICode.COLORS, INICode.COLOR_FOU, fou, null);
			String fif = changeAlpha(colorStr, "1A");
			colorINI.setStringProperty(INICode.COLORS, INICode.COLOR_FIF, fif, null);
			String six = changeAlpha(colorStr, "0D");
			colorINI.setStringProperty(INICode.COLORS, INICode.COLOR_SIX, six, null);
		}
		return true;
	}

	private String getValidateColorStr(String oriColorStr) {
		if (oriColorStr == null) return null;
		if (oriColorStr.length() == 6) {
			return "0xFF" + oriColorStr;
		} else if (oriColorStr.length() == 8) {
			return "0x" + oriColorStr;
		} else {
			return null;
		}
	}
	
	private String changeAlpha(String colorStr, String alpha) {
		String prefix = colorStr.substring(0, 2);
		String rgb = colorStr.substring(4);
		
		return prefix + alpha + rgb;
	}
	
	@Test
	public void testChangeAlpha() {
		String black = 	"0xFFFFFFFF";
		System.out.println(changeAlpha(black, "0D"));
	}

	private INIFile getINI(HttpSession session, String sessionKey, String path) {
		return session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
	}
}
