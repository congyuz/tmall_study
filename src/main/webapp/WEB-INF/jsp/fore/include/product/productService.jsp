<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/5
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<title>模仿天猫官网 ${p.name}</title>
<div class="categoryPictureInProductPageDiv"><img class="categoryPictureInProductPage" src="img/category/${p.category.id}.jpg"></div>

<div class="productPageDiv">
    <%@include file="imgAndInfo.jsp" %>
    <%@include file="productReview.jsp" %>
    <%@include file="productDetail.jsp" %>
</div>
