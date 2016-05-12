package com.yinlei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCookie2 删除客户端缓存的cookie
 */
@WebServlet("/ServletCookie2")
public class ServletCookie2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 要想删除客户端存储的cookie，采用的办法是
		// 创建一个同名的cookie，然后将存活时间设置为0(毫秒级的数)，然后覆盖客户端存储的cookie
		
		Cookie cookie = new Cookie("lastaccesstime", "");
		cookie.setMaxAge(0);
		//发送到客户端
		response.addCookie(cookie);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
