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
    <div class="py-5">
        <div class="container rounded border border-dark">
            <div class="row row-cols-1">
                <div class="row row-cols-3">
                    <div class="col-4">
                        ${bookSession.bookingName}
                    </div>
                    <div class="col-4">
                        ${bookSession.pin}
                    </div>
                    <div class="col-4">
                        <c:set var="users" value="${bookSession.users}" scope="request"/>
                        <jsp:include page="userModal.jsp"/>
                    </div>
                </div>

                <div class="col-7">
                    <div class="px-4 py-5 chat-box bg-white">
                        <!-- Sender Message-->
                        <div class="media w-50 mb-3">
                            <div class="media-body ml-3">
                                <div class="bg-light rounded py-2 px-3 mb-2">
                                    <p class="text-small mb-0 text-muted">Test which is a new approach all solutions</p>
                                </div>
                                <p class="small text-muted">12:00 PM | Aug 13</p>
                            </div>
                        </div>

                        <!-- Reciever Message-->
                        <div class="media w-50 ml-auto mb-3">
                            <div class="media-body">
                                <div class="bg-primary rounded py-2 px-3 mb-2">
                                    <p class="text-small mb-0 text-white">Test which is a new approach to have all
                                        solutions</p>
                                </div>
                                <p class="small text-muted">12:00 PM | Aug 13</p>
                            </div>
                        </div>

                        <!-- Sender Message-->
                        <div class="media w-50 mb-3"><img
                                src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg"
                                alt="user"
                                width="50" class="rounded-circle">
                            <div class="media-body ml-3">
                                <div class="bg-light rounded py-2 px-3 mb-2">
                                    <p class="text-small mb-0 text-muted">Test, which is a new approach to have</p>
                                </div>
                                <p class="small text-muted">12:00 PM | Aug 13</p>
                            </div>
                        </div>

                        <!-- Reciever Message-->
                        <div class="media w-50 ml-auto mb-3">
                            <div class="media-body">
                                <div class="bg-primary rounded py-2 px-3 mb-2">
                                    <p class="text-small mb-0 text-white">Apollo University, Delhi, India Test</p>
                                </div>
                                <p class="small text-muted">12:00 PM | Aug 13</p>
                            </div>
                        </div>

                        <!-- Sender Message-->
                        <div class="media w-50 mb-3"><img
                                src="https://res.cloudinary.com/mhmd/image/upload/v1564960395/avatar_usae7z.svg"
                                alt="user"
                                width="50" class="rounded-circle">
                            <div class="media-body ml-3">
                                <div class="bg-light rounded py-2 px-3 mb-2">
                                    <p class="text-small mb-0 text-muted">Test, which is a new approach</p>
                                </div>
                                <p class="small text-muted">12:00 PM | Aug 13</p>
                            </div>
                        </div>

                        <!-- Reciever Message-->
                        <div class="media w-50 ml-auto mb-3">
                            <div class="media-body">
                                <div class="bg-primary rounded py-2 px-3 mb-2">
                                    <p class="text-small mb-0 text-white">Apollo University, Delhi, India Test</p>
                                </div>
                                <p class="small text-muted">12:00 PM | Aug 13</p>
                            </div>
                        </div>

                    </div>

                    <form action="#" class="bg-light">
                        <div class="input-group">
                            <label>
                                <input type="text" placeholder="Type a message" aria-describedby="button-addon2"
                                       class="form-control rounded-0 border-0 py-4 bg-light">
                            </label>
                            <div class="input-group-append">
                                <button id="button-addon2" type="submit" class="btn btn-link"><i
                                        class="fa fa-paper-plane"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>
