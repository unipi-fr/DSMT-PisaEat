<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty bookSessionId}">
        <button type="button" class="btn btn-md btn-dark" data-toggle="modal" data-target="#modal${table.id}">Book
        </button>

        <jsp:include page="modal/bookModal.jsp"/>
    </c:when>
    <c:otherwise>
        <button type="button" class="btn btn-md btn-dark" disabled>Book</button>
    </c:otherwise>
</c:choose>