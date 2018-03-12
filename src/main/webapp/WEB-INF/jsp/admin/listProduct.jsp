<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/25
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $("#addForm").submit(function(){
            if(checkEmpty("name","产品名称")) return false;
            if(checkEmpty("originalPrice","原价格")) return false;
            if(checkEmpty("promotePrice","优惠价")) return false;
            if(checkEmpty("stock","库存")) return false;
            return true;
        });
    });
</script>

<title>产品管理</title>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">产品管理</li>
    </ol>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condesed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>产品名称</th>
                <th>小标题</th>
                <th width="50px">原价格</th>
                <th width="50px">优惠价</th>
                <th width="50px">库存</th>
                <th width="50px">图片管理</th>
                <th width="50px">设置属性</th>
                <th width="50px">编辑</th>
                <th width="50px">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <c:if test="${!empty p.firstProductImage}">
                            <img width="40px" src="img/productSingle/${p.firstProductImage.id}.jpg">
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.subTitle}</td>
                    <td>${p.originalPrice}</td>
                    <td>${p.promotePrice}</td>
                    <td>${p.stock}</td>
                    <td><a href="admin_productImage_list?pid=${p.id}"><span class="glyphicon glyphicon-picture"></span></a></td>
                    <td><a href="admin_propertyValue_edit?pid=${p.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="admin_product_edit?id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="admin_product_delete?id=${p.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pageDiv">
        <%@ include file="../include/admin/adminPage.jsp"%>
    </div>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增产品</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_product_add">
                <table class="addTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>小标题</td>
                        <td><input id="subTitle" name="subTitle" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="originalPrice" name="originalPrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价</td>
                        <td><input id="promotePrice" name="promotePrice" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" name="stock" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input name="cid" type="hidden" value="${c.id}">
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/admin/adminFooter.jsp"%>