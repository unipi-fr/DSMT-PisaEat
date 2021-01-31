<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col">
    <div class="card shadow-sm">
        <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="img/restourant-table.jpg" alt="">

        <div class="card-body">
            <p class="card-text">
                ${table.name} - ${table.numberOfSeat}
            </p>
            <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
                <small class="text-muted">9 mins</small>
            </div>
        </div>
    </div>
</div>