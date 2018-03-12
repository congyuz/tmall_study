<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
    <%--修改功能采用的是使用post方式提交ajax的异步调用方式--%>
    $(function(){
        $("input.pValue").keyup(function(){
            var value=$(this).val;
            var page="admin_propertyValue_update";
            var pvid=$(this).attr("pvid");
            var parentSpan=$(this).parent("span");
            parentSpan.css("border","1px solid yellow");
            //借助JQuery的ajax函数$.post把id和值提交到admin_propertyValue_update
            $.post(page,{"value":value,"id":pvid},function(result){
                //结合update方法上的注解@ResponseBody和return "success"
                //就会向浏览器返回字符串 "success"
                if(result=="success") parentSpan.css("border","1px solid green");
                else parentSpan.css("border","1px solid red");
            });
        });
    });
</script>

<html>
<head>
    <title>编辑产品属性值</title>
</head>
<body>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class=""active>编辑产品属性</li>
    </ol>
    <div class="editPVDiv">
        <c:forEach items="${pvs}" var="pv">
            <div class="eachPV">
                <span class="pvName">${pv.property.name}</span>
                <span class="pvValue"><input class="pvValue" pvid="${pv.id}" type="text" value="${pv.value}"/></span>
            </div>
        </c:forEach>
        <div style="clear:both"/>
    </div>
</div>
</body>
</html>
