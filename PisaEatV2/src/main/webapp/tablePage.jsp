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
    <div class="py-sm-5">
        <div class="container rounded border border-dark">
            <div class="list-group list-group-flush">
                <div class="list-group-item list-group-horizontal d-flex justify-content-between">
                    <div>
                        Booking name: ${bookSession.bookingName}
                    </div>
                    <div>
                        pin: ${bookSession.pin}
                    </div>
                    <div>
                        <c:set var="users" value="${bookSession.users}" scope="request"/>
                        <jsp:include page="userModal.jsp"/>
                    </div>
                </div>
                <div class="list-group-item">
                    <jsp:include page="chat/chat.jsp"/>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>
