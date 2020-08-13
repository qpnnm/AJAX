package com.ajax.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ajax.test.dao.UserInfoDAO;
import com.ajax.test.dao.impl.UserInfoDAOImpl;
import com.ajax.test.service.UserService;

public class UserServiceImpl implements UserService {
	private UserInfoDAO uiDao = new UserInfoDAOImpl();

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

	@Override
	public Map<String, Object> joinUserInfo(Map<String, Object> user) {
		int result = uiDao.insertUserInfo(user);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("result", result);
		rMap.put("msg", "가입실패");

		if (result == 1) {
			rMap.put("msg", "가입성공");
			rMap.put("url", "/views/login");
		}
		return rMap;
	}

	@Override
	public Map<String, String> checkId(String uiId) {
		Map<String, String> rMap = new HashMap<>();
		rMap.put("msg", "이미 존재하는  아이디입니다.");
		rMap.put("result", "false");
		if (uiDao.selectUserInfoByUiId(uiId) == null) {
			rMap.put("msg", "사용 가능한 아이디입니다.");
                rMap.put("result", "true");
		}
		return rMap;
	}

}
