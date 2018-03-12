<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/31
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<title>天猫</title>

<div class="homepageDiv">
    <%--分类和轮播--%>
    <%@ include file="categoryAndcarousel.jsp"%>
    <%--总体17种分类以及每种分类对应的5个产品--%>
    <%@ include file="homepageCategoryProducts.jsp"%>
</div>