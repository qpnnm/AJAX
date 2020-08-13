package com.ajax.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajax.test.common.Conn;
import com.ajax.test.dao.UserInfoDAO;
import com.ajax.test.servlet.InitServlet;

public class UserInfoDAOImpl implements UserInfoDAO {

	@Override
	public int insertUserInfo(Map<String, Object> ui) {
		String sql;
		int result = 0;
		Connection conn = Conn.open();
		PreparedStatement ps = null;
		try {
			sql = "insert into user_info(ui_num\r\n" + ",ui_name\r\n" + ",ui_age\r\n" + ",ui_birth\r\n" + ",ui_id\r\n"
					+ ",ui_password\r\n" + ",ui_phone\r\n" + ",ui_email\r\n" + ",ui_credat\r\n" + ",ui_nickname) ";
			sql += " values(seq_ui_num.nextval,?,?,?,?,?,?,?,sysdate,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, ui.get("ui_name"));
			ps.setObject(2, ui.get("ui_age"));
			ps.setObject(3, ui.get("ui_birth"));
			ps.setObject(4, ui.get("ui_id"));
			ps.setObject(5, ui.get("ui_password"));
			ps.setObject(6, ui.get("ui_phone"));
			ps.setObject(7, ui.get("ui_email"));
			ps.setObject(8, ui.get("ui_nickname"));
			result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conn.close(ps, conn);
		}
		return result;
	}

	@Override
	public int deleteUserInfo(Map<String, Object> ui) {
		String sql;
		int result = 0;
		Connection conn = Conn.open();
		PreparedStatement ps = null;
		try {
			sql = "delete from user_info where ui_num=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, ui.get("ui_num"));
			result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conn.close(ps, conn);
		}
		return result;
	}

	@Override
	public int updateUserInfo(Map<String, Object> ui) {
		String sql;
		int result = 0;
		Connection conn = Conn.open();
		PreparedStatement ps = null;
		try {
			sql = "update set ui_name=?,ui_age=?,ui_birth=?,ui_password=?,ui_phone=?,ui_email=?,ui_nickname=? from user_info";
			sql += "where ui_num=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, ui.get("ui_name"));
			ps.setObject(2, ui.get("ui_age"));
			ps.setObject(3, ui.get("ui_birth"));
			ps.setObject(4, ui.get("ui_password"));
			ps.setObject(5, ui.get("ui_phone"));
			ps.setObject(6, ui.get("ui_email"));
			ps.setObject(7, ui.get("ui_nickname"));
			ps.setObject(8, ui.get("ui_num"));
			result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conn.close(ps, conn);
		}
		return result;
	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> ui) {
		String sql;
		Connection conn = Conn.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<>();

		try {
			sql = "select * from user_info where ui_num=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, ui.get("ui_num"));
			rs = ps.executeQuery();
			if (rs.next()) {
				map.put("ui_num", rs.getString("ui_num"));
				map.put("ui_name", rs.getString("ui_name"));
				map.put("ui_age", rs.getString("ui_age"));
				map.put("ui_birth", rs.getString("ui_birth"));
				map.put("ui_id", rs.getString("ui_id"));
				map.put("ui_phone", rs.getString("ui_phone"));
				map.put("ui_email", rs.getString("ui_email"));
				map.put("ui_credat", rs.getString("ui_credat"));
				map.put("ui_nickname", rs.getString("ui_nickname"));
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conn.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> selectListUserInfo(Map<String, Object> ui) {
		String sql;
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = Conn.open();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			sql = "select *from user_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ui_num", rs.getString("ui_num"));
				map.put("ui_name", rs.getString("ui_name"));
				map.put("ui_age", rs.getString("ui_age"));
				map.put("ui_birth", rs.getString("ui_birth"));
				map.put("ui_id", rs.getString("ui_id"));
				map.put("ui_phone", rs.getString("ui_phone"));
				map.put("ui_email", rs.getString("ui_email"));
				map.put("ui_credat", rs.getString("ui_credat"));
				map.put("ui_nickname", rs.getString("ui_nickname"));
				list.add(map);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conn.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public Map<String, Object> selectUserInfoByUiId(String uiId) {
		String sql;
		Connection conn = Conn.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<>();

		try {
			sql = "select *from user_info where ui_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uiId);
			rs = ps.executeQuery();
			if (rs.next()) {
				map.put("ui_num", rs.getString("ui_num"));
				map.put("ui_name", rs.getString("ui_name"));
				map.put("ui_age", rs.getString("ui_age"));
				map.put("ui_birth", rs.getString("ui_birth"));
				map.put("ui_id", rs.getString("ui_id"));
				map.put("ui_phone", rs.getString("ui_phone"));
				map.put("ui_email", rs.getString("ui_email"));
				map.put("ui_credat", rs.getString("ui_credat"));
				map.put("ui_nickname", rs.getString("ui_nickname"));
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conn.close(rs, ps, conn);
		}
		return null;
	}
	public static void main(String[] args) {
		InitServlet is = new InitServlet();
		is.init();
		UserInfoDAOImpl uiDao = new UserInfoDAOImpl();
	}
}
