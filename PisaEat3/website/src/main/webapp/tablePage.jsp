<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <%@include file="header.jsp" %>
    <script>
        /*
        $(document).ready(function () {
            $('#send-message').click(function () {
                $.ajax({
                    type: 'POST',
                    url: 'SessionServlet',
                    data: {
                        messageBody: $("input[name=messageBody]").val()
                    },
                    error: function (response) {
                        // Gets called when an error occurs with error details in variable response
                    },
                    success: function (response) {
                        // Gets called when the action is successful with server response in variable response
                        location.reload();
                    }
                });
            });
        });*/

        var wsocket;
        var serviceLocation = "ws://localhost:8080/chat-WebSocket-1.0-SNAPSHOT/chat/";
        var $username;
        var $message;
        var $chatWindow;

        function onMessageReceived(evt) {
            var msg = JSON.parse(evt.data);
            if (msg.username.localeCompare("${sessionScope.name}") == 0)
            {
                var $receivedMessage = $('<div class="col-sm-10 ms-auto"> <div class="card shadow-sm text-white bg-dark py-2 px-3 mb-3"> <p class="card-text mb-0">' + msg.message + '</p> <p class="small text-muted text-end m-0">' + msg.localDatetime + '</p> </div> </div>');
            } else {
                var $receivedMessage = $('<div class="col-sm-10 me-auto"><div class="card shadow-sm py-2 bg-light px-3 mb-3"><p class="card-title mb-0">' + msg.username + ':</p><p class="card-text mb-0">' + msg.message + '</p><p class="small text-muted text-end m-0">' + msg.localDatetime + '</p></div></div>');
            }
            $chatWindow.append($receivedMessage);
        }

        function sendMessage() {
            var msg = '{"message":"' + $message.val() + '", "username":"'
            + $username + '"}';

            wsocket.send(msg);
        }

        function connectToChatserver() {
            bookSessionId = "${bookSession.id}";
                wsocket = new WebSocket(serviceLocation + bookSessionId);
            wsocket.onmessage = onMessageReceived;
        }

        $(document).ready(function () {
            $username = "${sessionScope.name}";
            $message = $('#messageBody');
            $chatWindow = $('#chatWindow');

            connectToChatserver();

            $('#send-message').click(function (evt) {
                evt.preventDefault();
                sendMessage();
                $message.val('');
            });
        });

    </script>

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
