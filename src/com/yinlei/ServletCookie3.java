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
 * Servlet implementation class ServletCookie3 演示是否能够获取Cookie
 */
@WebServlet("/Servlet/ServletCookie3")
public class ServletCookie3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 拿到客户端所有的Cookie
		Cookie[] cs = request.getCookies();
		// 拿到上次访问的Cookie
		PrintWriter out = response.getWriter();
		out.write("上次访问的时间是：");
		if (cs != null && cs.length > 0) {
			for (Cookie cookie : cs) {
				if (cookie.getName().equals("lastaccesstime")) {
					// 说明找到了需要的cookie
					String time = cookie.getValue();
					// 将time转换成long类型的数字
					//这个时候就不能获得了，因为这个访问的路径和存储的路径是不一样的，多了/Servlet
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
