package com.ajax.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/calc2")
public class Calc2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private Gson g = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String params = request.getParameter("params");
		Map<String,String> pMap = g.fromJson(params, Map.class);
		System.out.println(pMap);
		PrintWriter pw = response.getWriter();
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
