package com.one;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * Servlet implementation class ServletLogin ����ҳ��
 */
@WebServlet("/ServletUI")
public class ServletUI extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// �õ��������Ϣ
		String error = (String) request.getAttribute("error");
		if (error != null) {
			out.write("<font color = 'red'>" + error + "</font>");
		}

		// �õ��ͻ�����Я�������е�Cookie
		String username = "";
		String password = "";
		Cookie[] ce = request.getCookies();
		if (ce != null && ce.length > 0) {
			for (Cookie cookie : ce) {
				if (cookie.getName().equals("name")) {
					// �ҵ��˴洢�û�����ֵ
					username = cookie.getValue();
				}
				if (cookie.getName().equals("pass")) {
					password = cookie.getValue();
				}
			}
		}

		// ������¼ҳ��
		out.write("<form action = '" + request.getContextPath() + "/LoginServlet' method = 'post'>");
		out.write("����:<input type = 'text' name = 'username' value = '" + username + "'><br/>");
		out.write("����:<input type = 'text' name = 'password' value = '" + password + "'><br/>");
		out.write(":<input type = 'checkbox' name = 'remember' value = 'on'>��ס�û�������������<br/>");
		out.write("<input type = 'submit' value = '��¼'><br/>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
