<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <%@include file="header.jsp" %>
    <title>TablePage</title>
</head>
<body>
<%@include file="navbar.jsp" %>

<main>
    <div class="container">
        <c:set var="users" value="${bookSession.users}" scope="request" />
        <jsp:include page="userModal.jsp"/>
    </div>
</main>

</body>
</html>
