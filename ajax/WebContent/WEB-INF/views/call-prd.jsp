<%@page import="com.ajax.test.servlet.InitServlet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection con = InitServlet.getConnection();
	String sql = "{call prd_increase_sal(?,?)}";
	try {
		CallableStatement cs = con.prepareCall(sql);
		cs.setInt(1, 1);
		cs.setDouble(2, 1.2);
		int result = cs.executeUpdate();
		out.println("결과 :" + result + "<br>");
		out.println("1번 사원의 임금이 20 % 인상되었습니다.");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	%>
</body>
</html>