<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-10 me-auto">
    <div class="card shadow-sm py-2 bg-light px-3 mb-3">
        <p class="card-title mb-0">${message.getUsername()}:</p>
        <p class="card-text mb-0">${message.getMessage()}</p>
        <p class="small text-muted text-end m-0">${message.getLocalDatetime()}</p>
    </div>
</div>
