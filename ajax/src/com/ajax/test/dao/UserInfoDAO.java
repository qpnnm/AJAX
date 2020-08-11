package com.ajax.test.dao;

import java.util.List;
import java.util.Map;

public interface UserInfoDAO {
	int insertUser(Map<String, Object> uMap);

	int deleteUser(Map<String, Object> uMap);

	int updateUser(Map<String, Object> uMap);

	Map<String, Object> selectUser(Map<String, Object> uMap);

	List<Map<String, Object>> selectUserList(Map<String, Object> uMap);
}
