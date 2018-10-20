<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>

<!-- Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js">
</script>
<title>Insert title here</title>

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}
input{
	background-color:transparent;
	border:0;
} 
.bg {
	max-width: 530px;
	padding: 15px;
	margin: 0 auto;
}
</style>

<script type="text/javascript">
	//分页函数：按页码进行分页
	function showPage(pageNo){
		//把当前页码赋值给表单的隐藏域
		$("#pageNo").val(pageNo);
		//JQ触发表单提交
		$("#myForm").submit();
	}
	
	//删除用户
	function deleteCustomr(customerId){
		//通过location.href发送get请求
		location.href=
		"${pageContext.request.contextPath}/customer/deleteCustomer?customer_id=" + customerId;
	}
	
	//修改客户
	function updateCustomer(){
		//JQ修改表单action值
		$("#myForm").attr("action",
			"${pageContext.request.contextPath}/customer/updateCustomer");
		//JQ触发表单提交
		$("#myForm").submit();
	}
	
	
	var pageNo = 0;
	var customerId = 0;
	var totalPages = 0;
	
	//按用户查询属于该用户的订单详情
	function showOrdersByCustomer(customer_id){
		//默认第一页
		pageNo = 1;
		customerId = customer_id;
		showOrdersByPage(customerId);
	}
	//上一页
	function prePage(){
		pageNo -= 1;
		showOrdersByPage();
	}
	
	//下一页
	function nextPage(){
		pageNo += 1;
		showOrdersByPage();
	}
	//按页码进行查询
	function selectByPage(i){
		pageNo = i;
		showOrdersByPage();
	}
	
	//根据主键删除订单
	function deleteOrder(orderId){
		var flag = confirm("确认删除吗？");
		if(flag){
			//发送ajax请求进行删除
			$.post("${pageContext.request.contextPath}/order/deleteOrder",
					{"orderId":orderId},
					function(data){
						if(data=="success"){
							//删除成功后回调showOrdersByPage展示数据
							//把页码重置为1
							pageNo = 1;
							showOrdersByPage();
						}
					}
			);
		}
	}
	
	//展示订单详情
	function showOrderDetail(orderId){
		//发送ajax请求更新order
		$.post("${pageContext.request.contextPath}/order/showOrderDetail",
				{"orderId":orderId},
				function(data){
					var jsonOrder = JSON.parse(data);
					var dataStr = "<tr>" +
					"<input type='hidden' value='"+ jsonOrder.order_id + "'/>"+
					"<input type='hidden' value='"+ jsonOrder.customer.customer_id + "'/>"+
					"<td><input value='"+ jsonOrder.customer.customer_name +"'/></td>"+
					"<td><input value='"+jsonOrder.customer.address +"'/></td>"+
					"<td><input value='"+ jsonOrder.customer.phone_num +"'/></td>"+
					"<td><input value='"+ jsonOrder.total_money +"'/></td>" +
					"<td><a href='javascript:updateOrder()'>修改</a></td>"
					"</tr>";
					$("#updateMsg").html(dataStr);
				}
		);
	}
	
	//input数据封装为jsonObject
	function getJSONData(){
		var tds = new Array();
		$("#updateMsg").find("input").each(function(){
			//this是js对象
			//js:js对象.value
			//jq:jq对象.val()
			tds.push($(this).val());
		});
		
		var json = {
				"order_id":tds[0],
				"customer":{
					"customer_id":tds[1],
					"customer_name":tds[2],
					"address":tds[3],
					"phone_num":tds[4]
				},
				"total_money":tds[5],
		};
		return json;
	}
	
	//修改订单
	function updateOrder(){
		alert(JSON.stringify(getJSONData()));
		$.ajax({
			 type: "post",
		      url: "${pageContext.request.contextPath}/order/updateOrder",
		      contentType:"application/json;charset=utf-8",
		      data:JSON.stringify(getJSONData()),
		      success:function(data){
		    	  	if(data=="success"){
		    	  		//更新成功：关闭更新对话框时，
		    	  		//回调showOrdersByPage展示数据:
		    	  		//刷新外层对话框数据

		    	  		$('#updateModal').modal('hide');	 
		    	  		pageNo = 1;
		    	  		showOrdersByPage();
		  		}
		      }
		      
		});
	}
	
	//关闭外层对话框时的刷新index.jsp
	$(function(){
		//在关闭对话框时绑定函数
		$('#myModal').on('hide.bs.modal', 
			function(){
			location.href="${pageContext.request.contextPath}/customer/customermain";
	   	})
	});
	
	//订单分页
	function showOrdersByPage(){
		//发送ajax请求
		//$.post(url,data,function):ajax post的请求
		alert("showOrdersByPage()");
		$.post("${pageContext.request.contextPath}/order/showOrdersByPage",
				{"customer_id":customerId,"pageNo":pageNo},
				function(data){
					//1.把JSONString-->JSONObject
					var pbObject = JSON.parse(data);
					//List--->JSONArray
					var pageContent = pbObject.pageContent;
					//2.通过DOM操作把数据填充到msg
					var dataStr = "";
					//遍历pageContent取出数据
					for (var i = 0; i < pageContent.length; i++) {
						dataStr += "<tr><td>"+ pageContent[i].order_id
						+"</td><td>"+ pageContent[i].customer.address
						+"</td><td>"+ pageContent[i].total_money
						+"</td><td>"+ pageContent[i].customer.customer_name
						+"</td><td><a href='javascript:deleteOrder("+ pageContent[i].order_id
								+")'>删除</a></td>"
						+"<td><a href='javascript:void(0);' "
						+" onclick = 'showOrderDetail("+ pageContent[i].order_id
						+")'  data-toggle='modal' data-target='#updateModal'>修改订单的详情</a></td>"
						+"</tr>";
					}
					$("#msg").html(dataStr);
					
					//3.通过DOM操作把分页导航栏填充到page
					var pageStr = '<ul class="pagination">';
					//当前页
					pageNo = pbObject.pageNo;
					totalPages = pbObject.totalPages;
					//拼接上一页
					if(pageNo==1){
						pageStr += '<li class="disabled"><a href="#">&laquo;</a></li>';
					}else{
						pageStr += '<li><a href="#" onclick="prePage()">&laquo;</a></li>';
					}
					
					//拼接中间页码
					for (var i = 1; i <= totalPages; i++) {
						if(i==pageNo){
							pageStr += '<li class="active"><a href="#" onclick="selectByPage('+ i +')">'+ i +'</a></li>';
						}else{
							pageStr += '<li><a href="#" onclick="selectByPage('+ i +')">'+ i +'</a></li>';
						}
					}
					
					
					//拼接下一页
					if(pageNo==totalPages){
						pageStr += '<li class="disabled"><a href="#">&raquo;</a></li>';
					}else{
						pageStr += '<li><a href="#" onclick="nextPage()">&raquo;</a></li>';
					}
					
					pageStr += '</ul>';
					//填充page
					$("#page").html(pageStr);
					
				}
		);
	}
	
	
</script>

</head>
<body>

<form id="myForm" action="${pageContext.request.contextPath}/customer/showCustomerByPage" method="post">
	
	<table class="table table-hover table-bordered bg">
		<tr>
			<td colspan="4">
			 <a
				href="${pageContext.request.contextPath}/addCustomer.jsp"
				class="btn btn-primary btn-lg active" role="button">新增客户</a>
			</td>
		</tr>
		<tr>
			<td>序号</td>
			<td>照片</td>
			<td>客户名称</td>
			<td>联系电话</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${usersPage.pageContent}" var="pc" varStatus="vs">
			<tr>
			<input type="hidden" name="customer_name" value="${pc.customer_id}"/>
				<!-- 序号：vs.index起始值为0 -->
				<td>${(usersPage.pageNo-1)*usersPage.pageSize+vs.index+1}</td>
				<td><img src="${pageContext.request.contextPath}/pics/${pc.self_pai_pic}"
					class="img-circle"></td>
				<td><input type="text" name="customer_name" value="${pc.customer_name}"/></td>
				<td><input type="text" name="phone_num" value="${pc.phone_num}"/></td>
				<td>
				<a href="${pageContext.request.contextPath}/customer/deletecustomer?targetcustomer=${pc.customer_id}" 
					class="btn btn-primary btn-sm">删除客户</a>
				<a href="javascript:void(0)" id="orderDetail"
					onclick="showOrdersByCustomer(${pc.customer_id})"
					class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal">订单详情</a></td>
			</tr>
		</c:forEach>
		<tr>
		<td colspan="4" align="center">
			<c:forEach begin="1" end="${usersPage.totalPage}" var="page">
			
			<c:choose>
			<c:when test="${page==usersPage.pageNo}">
			  <a class="choosed" href="javascript:showBypage(${page})">&nbsp;in${page}&nbsp;</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/customer/customermain?pageNoUp=${page}">&nbsp;${page}ss&nbsp;</a>
			</c:otherwise>
			</c:choose>					
			</c:forEach>
		</td>
		<td><a href="${pageContext.request.contextPath}/updatecustomers" 
					class="btn btn-primary btn-sm">保存页面</a></td>
		</tr>
	</table>
	

	<!-- 分页组件
			//展示分页组件
	/* <ul class="pagination">
						<li class="disabled"><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
	</ul>	 */	

	 -->
	<div style="text-align:center;">
		<ul class="pagination">
			
		</ul>
	</div>
</form>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">订单详情</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered .table-hover">
						<tr>
							<td>订单编号</td>
							<td>收货地址</td>
							<td>订单价格</td>
							<td>客户名称</td>
							<td>操作</td>
						</tr>
						<tbody id="msg">
						
							<a href="javascript:void(0)" id="operateDetail"
							class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#OrderModal">订单</a> 
							
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="OrderModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">订单详情</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered .table-hover">
						<tr>
							<td>订单编号</td>
							<td>收货地址</td>
							<td>订单价格</td>
							<td>客户名称</td>
							<td>操作</td>
						</tr>
						<tbody id="OrderMsg">

						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
	</div>
<%-- 	<div>
		<table>
			<tr>
				<th>数据测试</th>
				<td>页号：${requestScope.usersPage.pageNo}</td>
				<td>页长：${usersPage.pageSize}</td>
				<td>总数：${usersPage.totalCount}</td>
				<td>总页：${usersPage.totalPage}</td>
				<td>${userPages.pageContext}</td>
				<c:if test="${userPages.pageContext!=null}">存在</c:if>
								
			</tr>
			<tr></tr>
			<tr></tr>
		</table>
	</div> --%>
</body>
</html>