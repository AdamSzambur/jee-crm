<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
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
                Czy napewno chcesz usunąć ten pojazd ?
                <br><br>
                <a role="button" class="btn btn-danger" href="" id="deleteBtn">Usuń</a>
                <a role="button" class="btn btn-success" href="" onclick="event.preventDefault(); $('#deleteMsg').toggleClass('invisible');">Anuluj</a>
            </p>
        </div>

        <br>
        <h4 class="cover-heading">Lista Pojazdów </h4>
        <p>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Klient</th>
                <th scope="col">Marka</th>
                <th scope="col">Model</th>
                <th scope="col">Numer rejestracyjny</th>
                <th scope="col">Rok produkcji</th>
                <th scope="col">Data nastepnego przeglądu</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>

                <c:forEach items="${vehicles}" var="vehicle" varStatus="i">
                <tr>
                    <th scope="row">${i.index+1}</th>
                    <td>${vehicle.customerId}</td>
                    <td>${vehicle.carBrand}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.registrationNumber}</td>
                    <td>${vehicle.productionYear}</td>
                    <td>${vehicle.nextTechnicalInspection}</td>
                    <td>
                        <div class="btn-group" role="group" aria-label="First group">
                            <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/vehicle/vehicleEdit?vehicleId=${vehicle.id}'">Edytuj</button>
                            <button type="button" class="btn btn-danger"  onclick="event.preventDefault();$('#deleteBtn').attr('href','${pageContext.request.contextPath}/vehicle/vehicleDel?vehicleId=${vehicle.id}'); $('#deleteMsg').toggleClass('invisible');">Usuń</button>
                            <button type="button" class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/vehicle/vehicleOrder?vehicleId=${vehicle.id}'">Zlecenia</button>
                        </div>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="8">
                        <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/vehicle/vehicleAdd'">Dodaj nowy pojazd</button>
                    </td>
                </tr>
            </tbody>
        </table>
        </p>
    </div>



</main>
<jsp:include page="footer.jsp"/>

