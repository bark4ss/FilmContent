<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="films.page.title"/></title>
    <link href="<c:url value="/res/styles.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
</head>
<body>
<jsp:include page="./language_bar.jsp"/>
<table class="style">
    <caption class="heading"><spring:message code="films.page.title"/></caption>
    <c:if test="${filmsCount > 0}">
        <tr>
            <th class="left-side">№</th>
            <th style="width: 100%"><spring:message code="films.title"/></th>
            <th><spring:message code="films.year"/></th>
            <th><spring:message code="films.genre"/></th>
            <th><spring:message code="films.watched"/></th>
            <th colspan="2" class="right-side"><spring:message code="films.action"/></th>
        </tr>
        <c:forEach var="film" items="${filmsList}" varStatus="i">
            <tr>
                <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                <td class="title">${film.title}</td>
                <td>${film.year}</td>
                <td>${film.genre}</td>
                <td>
                    <c:if test="${film.watched == true}">
                        <span class="icon icon-watched"></span>
                    </c:if>
                </td>
                <td>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                        <a href="/edit/${film.id}">
                            <span class="icon icon-edit"></span>
                        </a>
                    </sec:authorize>
                </td>
                <td class="right-side">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/delete/${film.id}">
                            <span class="icon icon-delete"></span>
                        </a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${filmsCount == 0}">
        <tr>
            <td colspan="7" style="font-size: 150%" class="left-side right-side">
                <spring:message code="films.page.emptyList"/>
            </td>
        </tr>
    </c:if>
    <tr>
        <td colspan="7" class="left-side link right-side">
            <a style="margin-right: 70px; font-size: 100%" href="<c:url value="/add"/>">
                <span class="icon icon-add"></span><spring:message code="films.page.add"/>
            </a>
            <c:if test="${pagesCount > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>
                <c:url value="/" var="url">
                    <c:param name="page" value="1"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-first"></span>
                </a>
                <c:url value="/" var="url">
                    <c:param name="page" value="${page - 1}"/>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    <span class="icon icon-prev"></span>
                </a>

                <c:if test="${pagesCount <= 5}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${pagesCount}"/>
                </c:if>
                <c:if test="${pagesCount > 5}">
                    <c:choose>
                        <c:when test="${page < 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <c:when test="${page > pagesCount - 2}">
                            <c:set var="begin" value="${pagesCount - 4}"/>
                            <c:set var="end" value="${pagesCount}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${page - 2}"/>
                            <c:set var="end" value="${page + 2}"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                    <c:url value="/" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <c:set value="current-page" var="current"/>
                    <c:set value="" var="perspective"/>
                    <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                </c:forEach>

                <c:url value="/" var="url">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-next"></span>
                </a>
                <c:url value="/" var="url">
                    <c:param name="page" value="${pagesCount}"/>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    <span class="icon icon-last"></span>
                </a>
            </c:if>
            <span style="margin-left: 70px; font-size: 120%"><spring:message code="films.page.totalNumber"/>${filmsCount}</span>
        </td>
    </tr>
</table>
<footer>
    <jsp:include page="./nav_bar.jsp"/>
</footer>
</body>
</html>