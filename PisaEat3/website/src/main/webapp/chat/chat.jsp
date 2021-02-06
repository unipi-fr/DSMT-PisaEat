<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CHAT BOX -->
<div id='chatWindow' class="py-sm-3 px-sm-5 d-flex flex-column overflow-auto vh-60">
    <c:forEach items="${bookSessionMessages}" var="message">
        <c:set var="message" value="${message}" scope="request"/>
        <c:choose>
            <c:when test="${message.getUsername() == sessionName}">
                <jsp:include page="receiverMessage.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="messageSender.jsp"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>

<!-- WRITE BOX -->
<form id="doChat">
    <div class="input-group">
        <input type="text" name="username" id="username" hidden>
        <input type="text" name="messageBody" placeholder="Type a message for the kitchen"
               aria-describedby="send-message"
               class="form-control rounded-0 border-0 py-4 bg-light">
        <div class="input-group-append d-flex align-content-center align-items-center">
            <button id="send-message" type="submit" class="btn btn-outline-light text-dark">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                     class="bi bi-arrow-up-circle-fill" viewBox="0 0 16 16">
                    <path d="M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z"/>
                </svg>
            </button>
        </div>
    </div>
</form>