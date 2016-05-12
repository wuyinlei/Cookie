package com.two.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.two.bean.Book;
import com.two.utils.BookUtils;
/**
 * ������������
 * 		1. ��ʾ���е������Ϣ
 * 		2. ��ʾ�������ʷ��¼
 * @author Administrator
 *
 */
public class ShowAllBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		out.write("��վ�����º��飺<br>") ;
		//1.��ʾ���е���
		//��ȡ���е���ļ���
		Map<String ,Book> map = BookUtils.getAllBook() ;
		//��������
		for (Map.Entry<String, Book> entry : map.entrySet()) {
			//�õ�ÿһ����id
			String id = entry.getKey() ;
			//�õ�ÿһ����
			Book book = entry.getValue() ;
			//����������
			out.write(book.getBookName() + "&nbsp;&nbsp;<a href = '"+ request.getContextPath()+"/servlet/ShowBookDetailServlet?id=" + id +"'>��ʾ��ϸ��Ϣ</a><br>") ;
		}
		out.write("<br><br><br><br>") ;
		//2��ʾ�������ʷ��¼: ��������ʷ��¼��Cookie�����ֽ�history : ֵ����ʽ�� 1-2-3
		//�õ��ͻ���Я�������е�Cookie
		Cookie[] cs = request.getCookies() ;
		//ѭ���ж�
		for (int i = 0; cs !=null && i < cs.length; i++) {
			Cookie c = cs[i] ;
			if("history".equals(c.getName())){
				out.write("��������ʷ��¼���£� <br>") ;
				//�ҵ��˴����ʷ��¼��Cookie
				String value = c.getValue() ;
				//���ַ�����ֳɳ�����
				String[] ids = value.split("-") ;
				//ѭ�����飬�õ�ÿһ���飬��ʾ��ҳ����
				for (int j = 0; j < ids.length; j++) {
					Book b = BookUtils.getBookById(ids[j]) ;
					out.write(b.getBookName() + "<br>") ;
				}
			}
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
