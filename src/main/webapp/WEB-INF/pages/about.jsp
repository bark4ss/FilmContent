<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="about.title"/></title>
</head>
<body>

<jsp:include page="./language_bar.jsp"/>
<jsp:include page="./nav_bar.jsp"/>

<spring:message code="about.description"/>
</body>
</html>
