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
	String sql = "{call increase_salary(?,?)}";
	try {
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, "dragon");
		cs.setFloat(2,1.05F);
		int result = cs.executeUpdate();
		out.println("결과 :" + result + "<br>");
		out.println("dragon의 임금이 0.05  인상되었습니다.");
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