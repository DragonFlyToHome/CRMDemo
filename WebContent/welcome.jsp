<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- fn不能直接使用，需要导入相应的jstl标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>欢迎登录${admin.user_name}</title>
<style type="text/css">
	.checkBoxOutline {
     	outline: 1px solid red;
	}
	
	.error_msg{	
		color:red;	
	}
</style>

<!-- 引入jq -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
		//对roleIds checkbox进行非空校验
        	function check_role() {
        		var roleIds = $(":checkbox[name='roleIds']:checked");
        		if(roleIds.length == 0) {
        			$("#role_msg").text("请至少选择一个模块.").addClass("error_msg");
        		} else {
        			$("#role_msg").text("").removeClass("error_msg");
        		}
        	}
</script>
</head>
<div>

	<form action="${pageContext.request.contextPath}/updateAdmin" method="post">
		<table>
		<tr>
			<td>用户名：</td>
			<td><label></label><input type="text" name="user_name" value="${adminsession.user_name}" /></td>
			</tr>
		<tr>
			<td>密码：</td>
			<td><input type="text" name="password" value="${adminsession.password}"/></td>
		</tr>
		<c:if test="${fn:contains(adminsession.roles,5)}">
			<tr>
			<td><c:forEach items="${rolesin}" var="role">
					<input type="checkbox" name="roleIds" 
						value="${role.role_id}" onclick="check_role();"
						<c:forEach items="${adminsession.roles}" var="adminRole">
							<c:if test="${adminRole.role_id==role.role_id}">checked</c:if>
						</c:forEach>
						/>${role.role_name}
				</c:forEach></td>
			</tr>
		</c:if>
		
		<tr>
			<td><input type="submit" value="提交"></td>
			<td><input type="reset" value="重置"></td>
		</tr>
	</table>
	</form>
	<form action="${pageContext.request.contextPath}/goCustomer" method="post">
		<table>
			<tr>
				<td><input type="submit" value="进入表单"></td>			
			</tr>
		</table>
	</form>
</div>

<body>
</body>
</html>