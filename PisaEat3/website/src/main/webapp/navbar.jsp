<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="HomeServlet">PISA EAT</a>
    </div>
    <c:if test="${not empty sessionScope.name && not empty bookSession}">
        <c:if test="${sessionScope.name == bookSession.bookingName}">
            <form class="mb-0" method="post" action="TableServlet">
                <input value="leaveOperation" name="operation" hidden>
                <button type="submit" class="btn btn-danger">Leave</button>
            </form>
        </c:if>
    </c:if>
</nav>