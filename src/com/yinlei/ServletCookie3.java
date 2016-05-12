package com.yinlei;

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

/**
 * Servlet implementation class ServletCookie3 ��ʾ�Ƿ��ܹ���ȡCookie
 */
@WebServlet("/Servlet/ServletCookie3")
public class ServletCookie3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ��ͻ������е�Cookie
		Cookie[] cs = request.getCookies();
		// �õ��ϴη��ʵ�Cookie
		PrintWriter out = response.getWriter();
		out.write("�ϴη��ʵ�ʱ���ǣ�");
		if (cs != null && cs.length > 0) {
			for (Cookie cookie : cs) {
				if (cookie.getName().equals("lastaccesstime")) {
					// ˵���ҵ�����Ҫ��cookie
					String time = cookie.getValue();
					// ��timeת����long���͵�����
					//���ʱ��Ͳ��ܻ���ˣ���Ϊ������ʵ�·���ʹ洢��·���ǲ�һ���ģ�����/Servlet
					out.write(time);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
