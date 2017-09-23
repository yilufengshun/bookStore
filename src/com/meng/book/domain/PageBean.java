package com.meng.book.domain;

import java.util.List;

public class PageBean {
	//页码
	private int pageIndex;
	//页大小
	private int pageSize=4;
	//总数据个数
	private int totalCount;
	//总页数
	private int pageCount;
	
	//开始索引
	private int startIndex;
	//结束索引
	private int endIndex;
	
	//商品的类别
	private String category;
	//数据
	private List<Product> data;
	
	public PageBean() {
		// TODO Auto-generated constructor stub
	}
	
	public PageBean(int pageIndex,int totalCount,String category){
		this.pageIndex=pageIndex;
		this.totalCount=totalCount;
		this.pageCount=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		this.category=category;
		
		this.startIndex=pageIndex-5;
		this.endIndex=pageIndex+4;
		
		//处理页码小于6 
		if(pageIndex<6){
			this.startIndex=1;
			this.endIndex=10;
		}
		//处理页码大于总页数-4
		if(pageIndex>pageCount-4){
			this.startIndex=pageCount-9;
			this.endIndex=pageCount;
		}
		//处理总页数没有超过10页
		if(pageCount<10){
			this.startIndex=1;
			this.endIndex=pageCount;
		}
		
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Product> getData() {
		return data;
	}

	public void setData(List<Product> data) {
		this.data = data;
	}
	
}
