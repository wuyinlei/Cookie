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
 * Servlet implementation class ServletCookie1 演示记录上次访问的时间 cookie存储到客户端的缓存里面
 * 演示记录上次访问的时间
 */
@WebServlet("/ServletCookie1")
public class ServletCookie1 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置字符集
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		// 拿到客户端携带的记录上次访问的cookie 假设上次的cookie的名架子是lastaccesstime，值是一个long类型的数字
		// 拿到客户端携带的所有的cookie
		Cookie[] ce = request.getCookies();
		out.write("您上次访问的时间是：");
		// 循环判断，拿到我们需要的cookie
		if (ce != null && ce.length > 0) {
			for (Cookie cookie : ce) {
				if (cookie.getName().equals("lastaccesstime")) {
					// 说明找到了需要的cookie
					String time = cookie.getValue();
					// 将time转换成long类型的数字
					long timeT = Long.parseLong(time); // 毫秒值
					// 格式化时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					// 创建一个date对象
					Date d = new Date(timeT);
					// 将数据输出到页面
					out.write(sdf.format(d) + "&nbsp;&nbsp;<a href = '" + request.getContextPath()
							+ "/ServletCookie2'>清除Cookie</a>");

				}
			}
		}
		// 向客户端发送cookie
		Cookie c = new Cookie("lastaccesstime", new java.util.Date().getTime() + "");
		// 设置cookie存活时间(只要设置了存活时间，那么这个cookie就将存储到客户端端的硬盘上，而不会缓存到存储中)
		c.setMaxAge(Integer.MAX_VALUE);
		//设置游览器携带的Cookie的路径
		//c.setPath(request.getContextPath());
		
		//设置路径如果是"/"，那就意味着你访问服务器上的任意工程资源都会携带此Cookie
		//c.setPath("/");   //设置路径是服务器的根路径  ： 协议 + 主机名 + 端口号
		
		// 发送到客户端
		response.addCookie(c);
		
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
