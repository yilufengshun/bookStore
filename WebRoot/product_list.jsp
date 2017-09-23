<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${pageBean.category }&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${pageBean.category==""?"全部商品":pageBean.category }</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pageBean.totalCount }种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist">
									<tr>
									  <c:choose>
									  <c:when test="${empty pageBean.data}">
									    <td>
                          没有找到此类商品数据
                      <td>
                   </c:when>
                   <c:otherwise>
										<c:forEach items="${pageBean.data }" var="p" varStatus="vs">
										<td>

											<div class="divbookpic">
												<p>
													<a href="findProductById?product_id=${p.id }"><img src="bookcover/${p.upload }" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="findProductById?product_id=${p.id }">书名:${p.name }<br />售价:${p.price } </a>
											</div></td>
											</c:forEach>
                      </c:otherwise>
                      </c:choose>
									






									</tr>
								</table>















								<div class="pagination" style="width: 100%">
									<ul>


										<li class="disablepage"><a href="showProductByPage?category=${pageBean.category}&pageIndex=${pageBean.pageIndex==1?1:pageBean.pageIndex-1}">上一页 &lt;&lt;</a></li>
										
										
										<c:forEach begin="${pageBean.startIndex }" end="${pageBean.endIndex }" var="num">
											<c:choose>
												<c:when test="${pageBean.pageIndex==num }">
													<li class="currentpage">${num }</li>
												</c:when>
												<c:otherwise>
													<li><a href="showProductByPage?category=${pageBean.category }&pageIndex=${num}">${num }</a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>



										<li class="nextpage"><a href="showProductByPage?category=${pageBean.category}&pageIndex=${pageBean.pageIndex==pageBean.pageCount?pageBean.pageCount:pageBean.pageIndex+1}">下一页&gt;&gt;</a>
										</li>
								
								<li>当前第【${pageBean.pageIndex }】页，共【${pageBean.pageCount }】页</li>
								<li> <input type="number" id="page_num" min="1" /><input type="button" value="跳转" onclick="jump()"/></li>
									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />
<script type="text/javascript">
	function jump(){
		var num=document.getElementById("page_num").value;
		if(num==null||num.trim()==""){
			alert("buweikong");
			return;	
			}
		window.location.href="showProductByPage?category=${pageBean.category}&pageIndex="+num;
	}


</script>

</body>
</html>
