<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty table.bookSessionId}">
        <button type="button" class="btn btn-md btn-dark" data-toggle="modal" data-target="#modal${table.id}">Book</button>
    </c:when>
    <c:otherwise>
        <button type="button" class="btn btn-md btn-dark" data-toggle="modal" data-target="#modal${table.id}">Join</button>
    </c:otherwise>
</c:choose>

<!-- The Modal -->
<div class="modal fade" id="modal${table.id}">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <form method="post" action="TableServlet">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Book Table ${table.name}</h4>
                    <button type="button" class="btn btn-close" data-dismiss="modal"></button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <div class="form-group">
                        <label for="input${table.id}"> Name: </label>
                        <input type="text" class="form-control" placeholder="Enter your name" id="input${table.id}" name = "bookingName">
                    </div>

                    <label>
                        <input value="${table.id}" name="idTable" hidden>
                    </label>

                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-md btn-dark">Confirm Book</button>
                </div>

            </form>

        </div>
    </div>
</div>