<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="<c:url value="/res/styles.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/favicon.png"/>"/>
    <c:choose>
        <c:when test="${empty film.title}">
            <title><spring:message code="films.page.add"/></title>
        </c:when>
        <c:otherwise>
            <title><spring:message code="films.page.edit"/></title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<jsp:include page="./language_bar.jsp"/>
<c:url value="/add" var="addUrl"/>
<c:url value="/edit" var="editUrl"/>
<form class="style" action="${empty film.title ? addUrl : editUrl}" name="film" method="POST">
    <c:choose>
        <c:when test="${!empty film.title}">
            <p class="heading"><spring:message code="films.action.edit"/></p>
            <input type="hidden" name="id" value="${film.id}">
        </c:when>
        <c:otherwise>
            <p class="heading"><spring:message code="films.action.add"/></p>
        </c:otherwise>
    </c:choose>
    <p><input type="text" name="title" placeholder="title" value="${film.title}" maxlength="100" required autofocus
              pattern="^[^\s]+(\s.*)?$">
    <p><input type="number" name="year" placeholder="year" value="${film.year}" maxlength="4" required
              oninput="if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
    <p><input type="text" name="genre" placeholder="genre" value="${film.genre}" maxlength="20" required>
    <p class="checkbox">
        <label for="watched"><spring:message code="films.watched"/>
            <c:if test="${film.watched == true}">
                <input type="checkbox" name="watched" id="watched" value="${film.watched}" checked>
            </c:if>
            <c:if test="${film.watched != true}">
                <input type="checkbox" name="watched" id="watched">
            </c:if>
            <span class="checkbox-common checkbox-no"><spring:message code="films.edit.watched.false"/></span>
            <span class="checkbox-common checkbox-yes"><spring:message code="films.edit.watched.true"/></span>
        </label>
    </p>
    <p>
        <c:set value="add" var="add"/>
        <c:set value="edit" var="edit"/>
        <input type="submit" value="${empty film.title ? add : edit}">
    </p>
    <p class="heading">${message}</p>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
    <jsp:include page="./nav_bar.jsp"/>
</body>
</html>