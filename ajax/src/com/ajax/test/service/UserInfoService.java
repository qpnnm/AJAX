package com.ajax.test.service;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
	Map<String, Object> insertUser(Map<String, Object>uMap);

	Map<String, Object> updateUser(Map<String, Object>uMap);

	Map<String, Object> deleteUser(Map<String, Object>uMap);

	Map<String, Object> selectUser(Map<String, Object>uMap);

	List<Map<String, Object>> selectUserList(Map<String, Object>uMap);
}