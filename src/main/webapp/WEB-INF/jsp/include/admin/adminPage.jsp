<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/22
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>

<script>
    $(function(){
        $("a ul.pagination li.disabled").click(function () {
            return false;
        });
    });
</script>
<nav>
    <ul class="pagination">
        <%--首页、上一页、中间页、下一页、尾页--%>
        <%--若没有前一页或后一页则超链接不可用--%>
        <li <c:if test="${page.isHasPrevious()==false}">class="disabled"</c:if>>
            <a href="?start=0${page.param}" aria-label="Previous"><span aria-hidden="true"> &laquo; </span></a>
        </li>
        <li <c:if test="${page.isHasPrevious()==false}">class="disabled"</c:if>>
            <%--不可用时保证误点击仍停留在此页--%>
            <c:if test="${page.isHasPrevious()==false}"><a href="?start=${page.start}${page.param}" aria-label="Previous">
                <span aria-hidden="true"> &lt; </span></a></c:if>
            <c:if test="${page.isHasPrevious()}"><a href="?start=${page.start-page.count}${page.param}" aria-label="Previous">
                <span aria-hidden="true"> &lt; </span></a></c:if>
        </li>
        <c:forEach begin="0" end="${page.getTotalPage()-1}" varStatus="status">
            <%--<c:if test="${status.count*page.count-page.start<=20 && status.count*page.count-page.start>=-10}">--%>
                <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                    <a href="?start=${status.index*page.count}${page.param}"<c:if test="${status.index*page.count==page.start}">
                    class="current"</c:if>>${status.count}</a>
                </li>
            <%--</c:if>--%>
        </c:forEach>
        <li <c:if test="${page.isHasNext()==false}">class="disabled"</c:if>>
            <a href="?start=${page.start+page.count}${page.param}" aria-label="Next"><span aria-hidden="true"> &gt; </span></a>
        </li>
        <li <c:if test="${page.isHasNext()==false}">class="disabled"</c:if>>
            <a href="?start=${page.getLast()}${page.param}" aria-label="Next"><span aria-hidden="true"> &raquo; </span></a>
        </li>
    </ul>
</nav>


