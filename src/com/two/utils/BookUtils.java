package com.two.utils;

import java.util.HashMap;
import java.util.Map;

import com.two.bean.Book;


public class BookUtils {

	private static Map<String,Book> map = new HashMap<String,Book>() ;
	
	static{
		map.put("1", new Book("1","葵花宝典","安倍晋三",100,"欲练神功，必须先练好基本功")) ;
		map.put("2", new Book("2","辟邪剑谱","陈冠希",80,"绝世好书")) ;
		map.put("3", new Book("3","西游记","吴承恩",50,"一群小猴子的故事")) ;
		map.put("4", new Book("4","水浒传","施耐庵",90,"三个女人和105个男人的故事")) ;
		map.put("5", new Book("5","西厢记","阿娇",70,"好好看啊。。。。。。")) ;
		map.put("6", new Book("6","神雕侠侣","金庸",100,"感天动地的旷世绝恋")) ;
		map.put("7", new Book("7","红楼梦","葫芦娃",60,"男人的梦想。。。。。。。")) ;
	}
	//获取所有的书
	public  static Map<String,Book> getAllBook(){
		return map ;
	}
	
	//根据书的id获取谋一本书
	public static Book getBookById(String id){
		return map.get(id) ;
	}
}
