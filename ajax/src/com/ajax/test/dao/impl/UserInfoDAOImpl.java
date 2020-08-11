package com.ajax.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajax.test.common.Conn;
import com.ajax.test.dao.UserInfoDAO;

public class UserInfoDAOImpl implements UserInfoDAO {

	@Override
	public int insertUser(Map<String, Object> uMap) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		int result = 0;
		try {
			con = Conn.open();
			sql = "insert into user_info";
			sql += " values(seq_ui_num.nextval,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setObject(1, uMap.get("ui_name"));
			ps.setObject(2, uMap.get("ui_age"));
			ps.setObject(3, uMap.get("ui_birth"));
			ps.setObject(4, uMap.get("ui_id"));
			ps.setObject(5, uMap.get("ui_password"));
			ps.setObject(6, uMap.get("ui_phone"));
			ps.setObject(7, uMap.get	("ui_email"));
			ps.setObject(8, uMap.get("ui_credat"));
			ps.setObject(9, uMap.get("ui_nickname"));
			result = ps.executeUpdate();
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(con);
			Conn.close(ps);
		}

		return result;
	}

	@Override
	public int deleteUser(Map<String, Object> uMap) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		int result = 0;
		try {
			con = Conn.open();
			sql = "delete from user_info where ui_num=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1, uMap.get("ui_num"));
			result = ps.executeUpdate();
			con.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(con);
			Conn.close(ps);
		}

		return result;
	}

	@Override
	public int updateUser(Map<String, Object> uMap) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		int result = 0;
		try {
			con = Conn.open();
			sql = "update user_info set ui_id=?,ui_credat=?";
			sql += " where ui_num=? ";
			ps = con.prepareStatement(sql);
			ps.setObject(1, uMap.get("ui_id"));
			ps.setObject(2, uMap.get("ui_credat"));
			ps.setObject(3, uMap.get("ui_num"));
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(con);
			Conn.close(ps);
		}

		return result;
	}

	@Override
	public Map<String, Object> selectUser(Map<String, Object> uMap) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = Conn.open();
			sql = "select * from user_info where ui_num=?";
			ps = con.prepareStatement(sql);
			ps.setObject(1, uMap.get("ui_num"));
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> u = new HashMap<>();
				u.put("ui_num", rs.getInt("ui_num"));
				u.put("ui_name", rs.getString("ui_name"));
				u.put("ui_id", rs.getString("ui_id"));
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(rs);
			Conn.close(ps);
			Conn.close(con);
		}

		return null;
	}

	@Override
	public List<Map<String, Object>> selectUserList(Map<String, Object> uMap) {
		List<Map<String, Object>> uList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = Conn.open();
			sql = "select * from user_info";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> u2 = new HashMap<>();
				u2.put("ui_num", rs.getInt("ui_num"));
				uList.add(u2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conn.close(rs);
			Conn.close(ps);
			Conn.close(con);

		}
		return uList;
	}

	public static void main(String[] args) {
		UserInfoDAO uid = new UserInfoDAOImpl();
		System.out.println(uid.selectUser(null));
	}
}
