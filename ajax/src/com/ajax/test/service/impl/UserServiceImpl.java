package com.ajax.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ajax.test.service.UserService;

public class UserServiceImpl implements UserService {

	public Map<String, String> doLogin(Map<String, String> user) {
		Map<String, String> rMap = new HashMap<>();
		rMap.put("result", "fail");
		rMap.put("msg", "아이디 확인");
		if ("test".equals(user.get("id"))) {
			rMap.put("msg", "비밀번호 확인");
			if ("test".equals(user.get("pwd"))) {
				rMap.put("result", "success");
				rMap.put("msg", "완료");
			}
		}
		return rMap;
	}

}
