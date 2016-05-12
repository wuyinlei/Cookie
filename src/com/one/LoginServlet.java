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
 * <li>1、判断用户是否是合法用户
 * <li>2、看用户是否选择了记录用户名和密码，如果选择需要发送Cookie
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 拿到游览器传递的数据
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		String remeber = request.getParameter("remember");

		// 判断用户是否是合法的用户 假定name和pass的逆序一样

		String name1 = new StringBuffer(name).reverse().toString();

		// 选择了
		Cookie c = new Cookie("name", name);
		Cookie c2 = new Cookie("pass", pass);
		// 判断
		if (name.equals(name1)) {
			// 合法用户

			// 合法用户
			// 判断是否选择了记住用户名和密码
			if ("on".equals(remeber)) {

				// 设置记录的时间
				c.setMaxAge(Integer.MAX_VALUE);
				c2.setMaxAge(Integer.MAX_VALUE);

			} else {
				// 设置存数的时间立刻失效
				c.setMaxAge(0);
				c2.setMaxAge(0);
			}

			// 设置存储到硬盘的路径访问
			c.setPath(request.getContextPath());
			c2.setPath(request.getContextPath());
			// 向客户端发送Cookie
			response.addCookie(c);
			response.addCookie(c2);
			
			request.setAttribute("name", name);
			request.getRequestDispatcher("MainServlet").forward(request, response);

			
			
		} else {
			// 非法用户
			request.setAttribute("error", "用户名或者密码错误");
			request.getRequestDispatcher("ServletUI").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
