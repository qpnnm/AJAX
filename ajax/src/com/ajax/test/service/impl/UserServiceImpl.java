package com.ajax.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ajax.test.dao.UserInfoDAO;
import com.ajax.test.dao.impl.UserInfoDAOImpl;
import com.ajax.test.service.UserService;

public class UserServiceImpl implements UserService {
	private UserInfoDAO uiDao = new UserInfoDAOImpl();

	public Map<String, Object> doLogin(Map<String, String> user) {
		Map<String,Object> rMap = new HashMap<>();
		rMap.put("result", "fail");
		rMap.put("msg", "아이디 확인");
		String uiId = user.get("ui_id");
		System.out.println(uiId);
		Map<String, Object> tmpUser = uiDao.selectUserInfoByUiId(uiId);
		if (tmpUser != null) {
			String tmpUiPwd = tmpUser.get("ui_password").toString();
			System.out.println(tmpUiPwd);
			String uiPwd = user.get("ui_password");
			System.out.println(uiPwd); 
			if(tmpUiPwd.equals(uiPwd)) {
				rMap.put("result", "success");
				rMap.put("msg", "로그인 완료");
				rMap.put("user", tmpUser);
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
