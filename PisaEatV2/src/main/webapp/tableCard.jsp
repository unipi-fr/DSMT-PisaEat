<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col">
    <div class="card shadow-lg">
        <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="img/restourant-table.jpg" alt="">

        <div class="card-body">
            <div class="row row-cols-2">
                <div class="col-6">
                    <p class="card-text">
                        Table: ${table.name}
                    </p>
                    <p class="card-text">
                        Seats: ${table.numberOfSeat}
                    </p>
                </div>

                <div class="col-6 d-flex justify-content-center align-items-center">
                    <jsp:include page="bookModal.jsp"/>
                </div>
            </div>

        </div>
    </div>
</div>
