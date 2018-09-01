package com.cpl.constants;
//TODO cpl! 暂时

import java.util.HashMap;
import java.util.Map;

public class INIFile {
	public INIFile() {}
	public INIFile(String path) {}
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
		
	}
}
