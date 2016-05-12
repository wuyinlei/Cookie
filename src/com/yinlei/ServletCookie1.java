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
 * Servlet implementation class ServletCookie1 ��ʾ��¼�ϴη��ʵ�ʱ�� cookie�洢���ͻ��˵Ļ�������
 * ��ʾ��¼�ϴη��ʵ�ʱ��
 */
@WebServlet("/ServletCookie1")
public class ServletCookie1 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// �����ַ���
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		// �õ��ͻ���Я���ļ�¼�ϴη��ʵ�cookie �����ϴε�cookie����������lastaccesstime��ֵ��һ��long���͵�����
		// �õ��ͻ���Я�������е�cookie
		Cookie[] ce = request.getCookies();
		out.write("���ϴη��ʵ�ʱ���ǣ�");
		// ѭ���жϣ��õ�������Ҫ��cookie
		if (ce != null && ce.length > 0) {
			for (Cookie cookie : ce) {
				if (cookie.getName().equals("lastaccesstime")) {
					// ˵���ҵ�����Ҫ��cookie
					String time = cookie.getValue();
					// ��timeת����long���͵�����
					long timeT = Long.parseLong(time); // ����ֵ
					// ��ʽ��ʱ��
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					// ����һ��date����
					Date d = new Date(timeT);
					// �����������ҳ��
					out.write(sdf.format(d) + "&nbsp;&nbsp;<a href = '" + request.getContextPath()
							+ "/ServletCookie2'>���Cookie</a>");

				}
			}
		}
		// ��ͻ��˷���cookie
		Cookie c = new Cookie("lastaccesstime", new java.util.Date().getTime() + "");
		// ����cookie���ʱ��(ֻҪ�����˴��ʱ�䣬��ô���cookie�ͽ��洢���ͻ��˶˵�Ӳ���ϣ������Ỻ�浽�洢��)
		c.setMaxAge(Integer.MAX_VALUE);
		//����������Я����Cookie��·��
		//c.setPath(request.getContextPath());
		
		//����·�������"/"���Ǿ���ζ������ʷ������ϵ����⹤����Դ����Я����Cookie
		//c.setPath("/");   //����·���Ƿ������ĸ�·��  �� Э�� + ������ + �˿ں�
		
		// ���͵��ͻ���
		response.addCookie(c);
		
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
