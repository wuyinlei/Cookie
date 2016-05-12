package com.yinlei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCookie2 ɾ���ͻ��˻����cookie
 */
@WebServlet("/ServletCookie2")
public class ServletCookie2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Ҫ��ɾ���ͻ��˴洢��cookie�����õİ취��
		// ����һ��ͬ����cookie��Ȼ�󽫴��ʱ������Ϊ0(���뼶����)��Ȼ�󸲸ǿͻ��˴洢��cookie
		
		Cookie cookie = new Cookie("lastaccesstime", "");
		cookie.setMaxAge(0);
		//���͵��ͻ���
		response.addCookie(cookie);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
