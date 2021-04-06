<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header>
    <div class="header-left">
        <ul>
            <li><a href="${request.contextPath}?lang=en" class="link_language"><spring:message code="language.en"/></a></li>
            <li><a href="${request.contextPath}?lang=ru" class="link_language"><spring:message code="language.ru"/></a></li>
        </ul>
    </div>
    <div class="header-right">
        <sec:authorize access="!isAuthenticated()">
            <a href="${request.contextPath}/login" class="link_language"><spring:message code="films.page.login"/></a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="${request.contextPath}/login?logout" class="link_language"><spring:message code="films.page.logout"/></a>
        </sec:authorize>
        </div>
</header>

