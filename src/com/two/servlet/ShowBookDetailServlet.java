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
 * 此类完成两件事情：
 * 		  1. 显示书的详细信息
 * 		  2. 发送历史浏览记录
 * @author Administrator
 *
 */
public class ShowBookDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		//1.显示书的详细信息
		//拿到页面传递的id
		String id = request.getParameter("id") ;
		//根据id获取书
		Book book = BookUtils.getBookById(id) ;
		//显示信息
		out.write(book + "&nbsp;&nbsp;<a href = '" + request.getContextPath() +"/servlet/ShowAllBookServlet'>返回主页继续浏览</a><br>") ;
		
		//2.发送历史浏览记录
		//功能：获取历史记录字符串
		String history = getHistory(request,id) ;
		//创建Cookie，发送Cookie
		Cookie c = new Cookie("history",history) ;
		//设置Cookie存放到客户端的硬盘上
		c.setMaxAge(Integer.MAX_VALUE) ;
		//设置客户端携带Cookie的路径
		c.setPath(request.getContextPath()) ;
		//发送Cookie
		response.addCookie(c) ;
	}
	
	/**
	 *  浏览器携带的历史记录的Cookie              点击的书的id                 最终需要发送的字符串
	 *    1.  无								1							1
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
	
	//获取要发往客户端的历史记录的字符串
	private String getHistory(HttpServletRequest request,String id) {
		//设定一个Cookie为空
		Cookie history = null ;
		//拿到所有的Cookie
		Cookie[] cs = request.getCookies() ;
		//循环判断给history赋值
		for (int i = 0;cs != null && i < cs.length; i++) {
			if(cs[i].getName().equals("history")){
				history = cs[i] ;
				break ;
			}
		}
		
		//考虑情况1
		if(history == null)
			return id ;
		
		//拿到客户端浏览器携带的历史记录的字符串
		String value = history.getValue() ;
		
		//考虑情况2,3
		if(value.length() == 1){
			if(value.equals(id))
				//说明点击的是浏览过的
				return id ;
			else
				//点击的是新的书
				return id + "-" + value ;
		}
		
		//先将历史记录字符串拆分成数组,将数组中的元素放入集合中
		String[] ids = value.split("-") ;
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(ids)) ;
		
		int index = list.indexOf(id) ;
		//考虑情况4,5,6
		if(value.length() == 3){
			if(index == -1){
				//说明此id没有点击过
				list.addFirst(id) ;
			}else{
				//说明点击的id以前点击过
				list.remove(index) ;
				list.addFirst(id) ;
			}
		}
		
		//考虑情况7，8，,9,10
		if(value.length() > 3){
			if(index == -1){
				//说明点击的id以前没有点击过
				list.removeLast() ;
				list.addFirst(id) ;
			}else{
				//说明点击的id是浏览器中已经携带的有的
				list.remove(index) ;
				list.addFirst(id) ;
			}
		}
		
		//需要将集合中的数据形成需要的字符串
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
