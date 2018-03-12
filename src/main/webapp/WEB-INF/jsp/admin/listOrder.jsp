<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $("button.orderPageCheckOrderItems").click(function(){
            var oid=$(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });
</script>

<title>订单管理</title>

<div class="workingArea">
    <h1 class="label label-info">订单管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>状态</th>
                <th>金额</th>
                <th width="100px">商品数量</th>
                <th width="100px">买家名称</th>
                <th>创建时间</th>
                <th>支付时间</th>
                <th>发货时间</th>
                <th>确认收货时间</th>
                <th width="120px">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${os}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.statusDesc}</td>
                    <td>¥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></td>
                    <td aligin="center">${o.totalNumber}</td>
                    <td aligin="center">${o.user.name}</td>
                    <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-mm-dd hh:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.payDate}" pattern="yyyy-mm-dd hh:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.deliveryDate}" pattern="yyyy-mm-dd hh:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.confirmDate}" pattern="yyyy-mm-dd hh:mm:ss"/></td>
                    <td>
                        <button class="orderPageCheckOrderItems btn btn-primary btn-xs" oid=${o.id}>查看详情</button>
                        <c:if test="${o.status=='waitDelivery'}">
                            <a href="admin_order_delivery?id=${o.id}"><button class="btn btn-primary btn-xs">发货</button></a>
                        </c:if>
                    </td>
                </tr>
                <tr class="orderPageOrderItemTR" id="${o.id}">
                    <td colspan="10" align="center">
                        <div class="orderPageOrderItem">
                            <table width="800px" align="center" class="orderPageOrderItemTable">
                                <c:forEach items="${o.orderItems}" var="oi">
                                    <tr>
                                        <td align="left"><img width="40px" height="40px" src="img/productSingle/${oi.product.firstProductImage.id}.jpg"/></td>
                                        <td><a href="foreproduct?pid=${oi.product.id}"><span>${oi.product.name}</span></a></td>
                                        <td align="right"><span class="text-muted">${oi.number}</span></td>
                                        <td align="right"><span class="text-muted">单价：${oi.product.promotePrice}元</span></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<div class="pageDiv"><%@ include file="../include/admin/adminPage.jsp"%></div>
</div>
<%@ include file="../include/admin/adminFooter.jsp"%>