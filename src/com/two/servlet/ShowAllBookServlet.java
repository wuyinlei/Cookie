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
 * 功能有两个：
 * 		1. 显示所有的书的信息
 * 		2. 显示浏览的历史记录
 * @author Administrator
 *
 */
public class ShowAllBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		out.write("本站有以下好书：<br>") ;
		//1.显示所有的书
		//获取所有的书的集合
		Map<String ,Book> map = BookUtils.getAllBook() ;
		//遍历集合
		for (Map.Entry<String, Book> entry : map.entrySet()) {
			//拿到每一本的id
			String id = entry.getKey() ;
			//拿到每一本书
			Book book = entry.getValue() ;
			//输出书的名字
			out.write(book.getBookName() + "&nbsp;&nbsp;<a href = '"+ request.getContextPath()+"/servlet/ShowBookDetailServlet?id=" + id +"'>显示详细信息</a><br>") ;
		}
		out.write("<br><br><br><br>") ;
		//2显示浏览的历史记录: 假设存放历史记录的Cookie的名字叫history : 值的形式： 1-2-3
		//拿到客户端携带的所有的Cookie
		Cookie[] cs = request.getCookies() ;
		//循环判断
		for (int i = 0; cs !=null && i < cs.length; i++) {
			Cookie c = cs[i] ;
			if("history".equals(c.getName())){
				out.write("你的浏览历史记录如下： <br>") ;
				//找到了存放历史记录的Cookie
				String value = c.getValue() ;
				//将字符串拆分成成数组
				String[] ids = value.split("-") ;
				//循环数组，拿到每一本书，显示在页面上
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
