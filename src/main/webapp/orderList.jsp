<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <br>
        <%--        message from servlet--%>

        <c:if test="${not empty param.msg}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${param.msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <%--        popup alert--%>
        <div class="alert alert-danger modal invisible" id="deleteMsg">
            <button type="button" class="close" onclick="event.preventDefault(); $('#deleteMsg').toggleClass('invisible');">
                <span>&times;</span>
            </button><br>
            <p style="text-align: center">
                Czy napewno chcesz usunąć zlecenie ?
                <br><br>
                <a role="button" class="btn btn-danger" href="" id="deleteBtn">Usuń</a>
                <a role="button" class="btn btn-success" href="" onclick="event.preventDefault(); $('#deleteMsg').toggleClass('invisible');">Anuluj</a>
            </p>
        </div>

        <br>

        <div class="form-row">
            <div class="form-group col-md-8">
                <h4 class="cover-heading">Aktualne zlecenia</h4>
            </div>
            <div class="form-group col-md-4" style="text-align: right">
                <button class="btn btn-primary" type="button" onclick="window.location.href='${pageContext.request.contextPath}/order/orderAdd'">Dodaj nowe zlecenie</button>
            </div>
        </div>
            <form method="get" action="${pageContext.request.contextPath}/order/orderList">
                <div class="input-group mb-3" >
                    <input name="filter" id="filter" type="text" class="form-control" <c:if test="${not empty filter}">value="${filter}"</c:if> placeholder="Podaj szukaną frazę w tablicy zleceń" aria-label="Podaj szukaną frazę w tablicy zleceń" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-danger" type="button" id="button-addon2" onclick="$('#filter').attr('value',''); $(this).closest('form').submit()">Wyczyść filtr</button>
                    </div>
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button" id="button-addon3" onclick="$(this).closest('form').submit()">Szukaj</button>
                    </div>
                </div>
                <small id="emailHelp" class="form-text text-muted">Podaj szykaną frazę w tablicy zleceń.</small>
            </form>
        <p>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Marka</th>
                <th scope="col">Model</th>
                <th scope="col">Nr rejestracyjny</th>
                <th scope="col">Klient</th>
                <th scope="col">Opis</th>
                <th scope="col">Pracownik</th>
                <th scope="col">Status</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${orders}" var="order" varStatus="i">
                <tr>
                    <th scope="row">${i.index+1}</th>
                    <td>${order.carBrand}</td>
                    <td>${order.model}</td>
                    <td>${order.registrationNumber}</td>
                    <td>${order.customerFirstName} ${order.customerLastName}</td>
                    <td>${order.problemDescription}</td>
                    <td>${order.employeeFirstName} ${order.employeeLastName}</td>
                    <td>${order.statusValue}</td>
                    <td>
                        <div class="btn-group" role="group" aria-label="First group">
                            <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/order/orderEdit?orderId=${order.id}'">Edytuj</button>
                            <button type="button" class="btn btn-danger"  onclick="event.preventDefault();$('#deleteBtn').attr('href','${pageContext.request.contextPath}/order/orderDel?orderId=${order.id}'); $('#deleteMsg').toggleClass('invisible');">Usuń</button>
                            <button type="button" class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/order/orderDetails?orderId=${order.id}'">Szczegóły</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

