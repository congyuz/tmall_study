<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/25
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $("#editForm").submit(function(){
            if(checkEmpty("name","产品名称")) return false;
            if(checkEmpty("originalPrice","原价格")) return false;
            if(checkEmpty("promotePrice","优惠价")) return false;
            if(checkEmpty("stock","库存")) return false;
            return true;
        });
    });
</script>

<title>编辑产品</title>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">产品管理</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑产品</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_product_update">
                <table class="editTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" value="${p.name}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>小标题</td>
                        <td><input id="subTitle" name="subTitle" value="${p.subTitle}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="originalPrice" name="originalPrice" value="${p.originalPrice}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价</td>
                        <td><input id="promotePrice" name="promotePrice" value="${p.promotePrice}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" name="stock" value="${p.stock}" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input name="id" type="hidden" value="${p.id}"/>
                            <input name="cid" type="hidden" value="${p.category.id}"/>
                            <button type="submit" class="btn btn-success">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>