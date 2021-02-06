<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-10 ms-auto">
    <div class="card shadow-sm text-white bg-dark py-2 px-3 mb-3">
        <p class="card-text mb-0">${message.getMessage()}</p>
        <p class="small text-muted text-end m-0">${message.getLocalDatetime()}</p>
    </div>
</div>
