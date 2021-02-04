<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <%@include file="header.jsp" %>
    <title>Pisa Eat</title>
</head>
<body>

<%@include file="navbar.jsp" %>

<main>
    <div class="py-5">
        <div class="container">
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mb-5" role="alert">
                        ${errorMessage}
                </div>
            </c:if>

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <table>
                    <c:forEach items="${Tables}" var="table">
                        <c:set var="table" value="${table}" scope="request"/>
                        <jsp:include page="tableCard.jsp"/>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</main>
</body>
</html>
