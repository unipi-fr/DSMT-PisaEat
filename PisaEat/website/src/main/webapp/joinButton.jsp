<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty bookSessionId}">
        <button type="button" class="btn btn-md btn-dark" data-toggle="modal" data-target="#modal${table.id}">Join
        </button>

        <jsp:include page="modal/joinModal.jsp"/>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${bookSessionId == table.bookSessionId}">
                <a class="btn btn-md btn-dark" href="BookingServlet">Join</a>
            </c:when>
            <c:otherwise>
                <button type="button" class="btn btn-md btn-dark" disabled>
                    Join
                </button>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>