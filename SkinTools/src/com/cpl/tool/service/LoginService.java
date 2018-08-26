package com.cpl.tool.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean doLogin(String userName, String password) {
		return ("cpl".equals(userName) && "123".equals(password));
	}

}
