<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/25
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $(".addFormSingle").submit(function(){
            if(!checkEmpty("filepathSingle","图片文件")){
                $("#filepathSingle").value("");
                return true;
            }
            return false;
        });
        $(".addFormDetail").submit(function(){
            if(!checkEmpty("filepathDetail","图片文件")) return true;
            return false;
        });
    });
</script>

<title>产品图片管理</title>
<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">产品图片管理</li>
    </ol>

    <table class="addPictureTable" align="center">
        <tr>
            <td class="addPictureTableTD">
                <div>
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary">单个</b>图片</div>
                        <div class="panel-body">
                            <form method="post" id="addFormSingle" action="admin_productImage_add" enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr><td>请选择本地图片</td></tr>
                                    <%--accept="image/*"--%>
                                    <tr><td><input id="filepathSingle" type="file" name="image"></td></tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="type_single">
                                            <input type="hidden" name="pid" value="${p.id}">
                                            <button type="submit" class="btn btn-success">提交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品单个缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pisSingle}" var="pi">
                            <tr>
                                <td>${pi.id}</td>
                                <td><a title="点击查看原图" href="img/productSingle/${pi.id}.jpg"><img height="50px" src="img/productSingle/${pi.id}.jpg"></a></td>
                                <td><a deleteLink="true" href="admin_productImage_delete?id=${pi.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </td>
            <td class="addPictureTableTD">
                <div>
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary">详细</b>图片</div>
                        <div class="panel-body">
                            <form method="post" id="addFormDetail" action="admin_productImage_add" enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr><td>请选择本地图片</td></tr>
                                    <%--accept="image/*"--%>
                                    <tr><td><input id="filepathDetail" type="file" name="image"></td></tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="type_detail">
                                            <input type="hidden" name="pid" value="${p.id}">
                                            <button type="submit" class="btn btn-success">提交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品详情缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pisDetail}" var="pi">
                            <tr>
                                <td>${pi.id}</td>
                                <td><a title="点击查看原图" href="img/productDetail/${pi.id}.jpg"><img height="50px" src="img/productDetail/${pi.id}.jpg"></a></td>
                                <td><a deleteLink="true" href="admin_productImage_delete?id=${pi.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</div>

<%@ include file="../include/admin/adminFooter.jsp"%>