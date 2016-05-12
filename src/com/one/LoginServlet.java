package com.one;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RememberServlet
 * <li>1���ж��û��Ƿ��ǺϷ��û�
 * <li>2�����û��Ƿ�ѡ���˼�¼�û��������룬���ѡ����Ҫ����Cookie
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// �õ����������ݵ�����
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		String remeber = request.getParameter("remember");

		// �ж��û��Ƿ��ǺϷ����û� �ٶ�name��pass������һ��

		String name1 = new StringBuffer(name).reverse().toString();

		// ѡ����
		Cookie c = new Cookie("name", name);
		Cookie c2 = new Cookie("pass", pass);
		// �ж�
		if (name.equals(name1)) {
			// �Ϸ��û�

			// �Ϸ��û�
			// �ж��Ƿ�ѡ���˼�ס�û���������
			if ("on".equals(remeber)) {

				// ���ü�¼��ʱ��
				c.setMaxAge(Integer.MAX_VALUE);
				c2.setMaxAge(Integer.MAX_VALUE);

			} else {
				// ���ô�����ʱ������ʧЧ
				c.setMaxAge(0);
				c2.setMaxAge(0);
			}

			// ���ô洢��Ӳ�̵�·������
			c.setPath(request.getContextPath());
			c2.setPath(request.getContextPath());
			// ��ͻ��˷���Cookie
			response.addCookie(c);
			response.addCookie(c2);
			
			request.setAttribute("name", name);
			request.getRequestDispatcher("MainServlet").forward(request, response);

			
			
		} else {
			// �Ƿ��û�
			request.setAttribute("error", "�û��������������");
			request.getRequestDispatcher("ServletUI").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
