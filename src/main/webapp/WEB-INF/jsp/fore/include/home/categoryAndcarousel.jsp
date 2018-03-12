<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/1
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    function showProductsAsideCategorys(cid){
        $("div.eachCategory[cid="+cid+"]").css("background-color","white");
        $("div.eachCategory[cid="+cid+"] a").css("color","#87CEFA");
        $("div.productsAsideCategorys[cid="+cid+"]").show();
    }
    function hideProductsAsideCategorys(cid){
        $("div.eachCategory[cid="+cid+"]").css("background-color","#E2E2E3");
        $("div.eachCategory[cid="+cid+"] a").css("color","#000000");
        $("div.productsAsideCategorys[cid="+cid+"]").hide();
    }
    $(function(){
        //当鼠标指针穿过元素时发生mouseenter事件
        $("div.eachCategory").mouseenter(function(){
            var cid=$(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.eachCategory").mouseenter(function(){
            var cid=$(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseenter(function(){
            var cid=$(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseenter(function(){
            var cid=$(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });
        $("div.rightMenu span").mouseenter(function(){
            var left=$(this).position().left;
            var top=$(this).position().top;
            var width=$(this).css("width");
            var destLeft=parseInt(left)+parseInt(width)/2;
            $("img#catear").css("left",destLeft);
            $("img#catear").css("top",top-20);
            $("img#catear").fadeIn(500);
        });
        $("div.rightMenu span").mouseenter(function(){$("img#catear").hide();});
        var left=$("div#carousel-of-product").offset().left;
        $("div.categoryMenu").css("left",left-20);
        $("div.categoryWithCarousel div.head").css("margin-left",left);
        $("div.productAsideCategorys").css("left",left-20);
    });
</script>

<img src="img/site/catear.png" id="catear" class="catear">
<div class="categoryWithCarousel">
    <div class="headbar show1">
        <div class="head">
            <span style="margin-left: 10px" class="glyphicon glyphicon-th-list"></span>
            <span style="margin-left: 10px">商品分类</span>
        </div>
        <div class="rightMenu">
            <span><a href=""><img src="img/site/chaoshi.png"></a></span>
            <span><a href=""><img src="img/site/guoji.png"></a></span>
            <c:forEach items="${cs}" varStatus="st">
                <c:if test="${st.count<=4}">
                    <span><a href="forecategory?cid=${c.id}">${c.name}</a></span>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <div style="position:relative"><%@ include file="categoryMenu.jsp"%></div>
    <div style="position: relative;left: 0;top: 0"><%@ include file="productsAsideCategory.jsp"%></div>
    <%@ include file="carousel.jsp"%>
    <div class="carouselBackgroundDiv"></div>
</div>