package com.two.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.two.bean.Book;
import com.two.utils.BookUtils;

/**
 * ��������������飺
 * 		  1. ��ʾ�����ϸ��Ϣ
 * 		  2. ������ʷ�����¼
 * @author Administrator
 *
 */
public class ShowBookDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		//1.��ʾ�����ϸ��Ϣ
		//�õ�ҳ�洫�ݵ�id
		String id = request.getParameter("id") ;
		//����id��ȡ��
		Book book = BookUtils.getBookById(id) ;
		//��ʾ��Ϣ
		out.write(book + "&nbsp;&nbsp;<a href = '" + request.getContextPath() +"/servlet/ShowAllBookServlet'>������ҳ�������</a><br>") ;
		
		//2.������ʷ�����¼
		//���ܣ���ȡ��ʷ��¼�ַ���
		String history = getHistory(request,id) ;
		//����Cookie������Cookie
		Cookie c = new Cookie("history",history) ;
		//����Cookie��ŵ��ͻ��˵�Ӳ����
		c.setMaxAge(Integer.MAX_VALUE) ;
		//���ÿͻ���Я��Cookie��·��
		c.setPath(request.getContextPath()) ;
		//����Cookie
		response.addCookie(c) ;
	}
	
	/**
	 *  �����Я������ʷ��¼��Cookie              ��������id                 ������Ҫ���͵��ַ���
	 *    1.  ��								1							1
	 *    2.  1						        1							1
	 *    3.  1								2							2-1
	 *    4.  1-2                           1							1-2
	 *    5.  1-2							2							2-1
	 *    6.  1-2							3							3-1-2
	 *    7.  1-2-3							1							1-2-3
	 *    8.  1-2-3							2							2-1-3
	 *    9.  1-2-3							3							3-1-2
	 *    10. 1-2-3							4							4-1-2
	 *  
	 */
	
	//��ȡҪ�����ͻ��˵���ʷ��¼���ַ���
	private String getHistory(HttpServletRequest request,String id) {
		//�趨һ��CookieΪ��
		Cookie history = null ;
		//�õ����е�Cookie
		Cookie[] cs = request.getCookies() ;
		//ѭ���жϸ�history��ֵ
		for (int i = 0;cs != null && i < cs.length; i++) {
			if(cs[i].getName().equals("history")){
				history = cs[i] ;
				break ;
			}
		}
		
		//�������1
		if(history == null)
			return id ;
		
		//�õ��ͻ��������Я������ʷ��¼���ַ���
		String value = history.getValue() ;
		
		//�������2,3
		if(value.length() == 1){
			if(value.equals(id))
				//˵����������������
				return id ;
			else
				//��������µ���
				return id + "-" + value ;
		}
		
		//�Ƚ���ʷ��¼�ַ�����ֳ�����,�������е�Ԫ�ط��뼯����
		String[] ids = value.split("-") ;
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(ids)) ;
		
		int index = list.indexOf(id) ;
		//�������4,5,6
		if(value.length() == 3){
			if(index == -1){
				//˵����idû�е����
				list.addFirst(id) ;
			}else{
				//˵�������id��ǰ�����
				list.remove(index) ;
				list.addFirst(id) ;
			}
		}
		
		//�������7��8��,9,10
		if(value.length() > 3){
			if(index == -1){
				//˵�������id��ǰû�е����
				list.removeLast() ;
				list.addFirst(id) ;
			}else{
				//˵�������id����������Ѿ�Я�����е�
				list.remove(index) ;
				list.addFirst(id) ;
			}
		}
		
		//��Ҫ�������е������γ���Ҫ���ַ���
		StringBuffer sb = new StringBuffer(list.get(0)) ;
		for (int i = 1; i < list.size(); i++) {
			sb.append("-" + list.get(i)) ;
		}
		
		return sb.toString();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
