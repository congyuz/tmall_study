<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/31
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<nav class="top">
    <a href="${contextPath}"><span style="color:#c40000;margin:0px" class="glyphicon glyphicon-home redColor"/>天猫首页</a>
    <span>喵，欢迎来天猫</span>
    <c:if test="${!empty user}">
        <a href="login.jsp">${user.name}</a>
        <a href="forelogout">退出</a>
    </c:if>
    <c:if test="${empty user}">
        <%--不能直接写.jsp文件名--%>
        <a href="loginPage">请登录</a>
        <a href="registerPage">免费注册</a>
    </c:if>
    <span class="pull-right">
        <a href="forebought">我的订单</a>
        <a href="forecart"><span style="color: #c40000;margin: 0px" class="glyphicon glyphicon-shopping-cart redColor"/>
        购物车<strong>${cartTotalItemNumber}</strong>件</a>
    </span>
</nav>

