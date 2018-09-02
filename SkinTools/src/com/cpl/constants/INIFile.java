package com.cpl.constants;
//TODO cpl! 暂时

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.cpl.utils.FileUtils;

public class INIFile {
	String mPath;
	public INIFile(String path) {
		mPath = path;
	}
	
	public Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
	
	public void put(String section, String property, String value) {
		Map<String, String> props = map.get(section);
		if (props == null) {
			props = new HashMap<String, String>();
			map.put(section, props);
		}
		props.put(property, value);
	}
	
	public String getProperty(String section, String property) {
		Map<String, String> props = map.get(section);
		if (props == null) return null;
		return props.get(property);
	}

	public void wirteSelf() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(mPath));
			bw = new BufferedWriter(fw);
			for (Entry<String, Map<String, String>> entry : map.entrySet()) {
				String section = entry.getKey();
				Map<String, String> props = entry.getValue();
				bw.write("[" + section + "]" + "\r\n");
				for (Entry<String, String> ps : props.entrySet()) {
					bw.write(ps.getKey() + "=" +ps.getValue() + "\r\n");
				}
				bw.write("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(bw);
			FileUtils.closeStream(fw);
		}
		
	}
}
