package com.ajax.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ajax.test.service.UserService;
import com.ajax.test.service.impl.UserServiceImpl;
import com.google.gson.Gson;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();
	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/") + 1;
		String cmd = request.getRequestURI().substring(idx);
		PrintWriter pw = response.getWriter();
		if ("checkid".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			Map<String, String> rMap = us.checkId(uiId);
			pw.println(g.toJson(rMap));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/") + 1;
		String cmd = request.getRequestURI().substring(idx);
		if ("login".equals(cmd)) {

			BufferedReader br = request.getReader();
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);

			}
			Map<String, String> pMap = g.fromJson(sb.toString(), Map.class);
			Map<String,Object> rMap = us.doLogin(pMap);
			if ("success".equals(rMap.get("result"))) {
				HttpSession session = request.getSession();
				session.setAttribute("user", rMap.get("user"));
				rMap.remove("user");
			}
			response.getWriter().append(g.toJson(rMap));
		} else if ("logout".equals(cmd)) {
			HttpSession session = request.getSession();
			session.invalidate();
			Map<String, String> rMap = new HashMap<>();
			rMap.put("msg", "로그아웃");
			response.getWriter().append(g.toJson(rMap));
		} else if ("join".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			if (uiId == null || uiId.trim().length() < 4) {
				throw new ServletException("NOT correct ID VALUE");
			}
			String uiPwd = request.getParameter("ui_password");
			if (uiPwd == null || uiPwd.trim().length() < 6) {
				throw new ServletException("NOT correct PWD VALUE");
			}
			String uiName = request.getParameter("ui_name");
			if (uiName == null || uiName.trim().length() < 2) {
				throw new ServletException("NOT correct NAME");
			}
			String uiAge = request.getParameter("ui_age");
			String uiBirth = request.getParameter("ui_birth");
			String uiEmail = request.getParameter("ui_email");
			String uiPhone = request.getParameter("ui_phone");
			String uiNickname = request.getParameter("ui_nickname");
			Map<String, Object> user = new HashMap<>();
			user.put("ui_id", uiId);
			user.put("ui_password", uiPwd);
			user.put("ui_name", uiName);
			user.put("ui_age", uiAge);
			user.put("ui_birth", uiBirth);
			user.put("ui_phone", uiPhone);
			user.put("ui_email", uiEmail);
			user.put("ui_nickname", uiNickname);
			Map<String, Object> rMap = us.joinUserInfo(user);
			request.setAttribute("rMap", rMap);
		}

	}

}
