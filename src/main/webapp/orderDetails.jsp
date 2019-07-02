<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
    <div class="container">
        <%--        message from servlet--%>
        <c:if test="${msg!=null}">
            <br>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <br>

        <div class="rounded border p-5">

        <h4 class="cover-heading">Szczegóły zlecenia</h4>
        <p>
        <form style="text-align: left">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="status">Status zlecenia</label>
                    <div class="input-group" id="status">
                        <select class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon">
                            <c:forEach items="${statusList}" var="status">
                                <option value="${status.id}" <c:if test="${status.id == order.statusId}">selected</c:if> >${status.value}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button">Zmień</button>
                        </div>
                    </div>
            </div>
            </div>

            Klient
            <div class="form-row">
                <div class="form-group col-md-8">
                    <select id="clientSelect" class="custom-select" aria-label="Imie i nazwisko klienta">
                        <option value=""></option>
                        <c:forEach items="${customerList}" var="customer">
                            <option value="${customer.id}" <c:if test="${customer.id == customerId}">selected</c:if> >${customer.firstName} ${customer.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            Samochód [marka/model / numer rejestracyjny]
            <div class="form-row">
                <div class="form-group col-md-8">
                    <select id="vehicleSelect" class="custom-select" aria-label="Marka i model pojazdu">
                        <option value=""></option>
                        <c:forEach items="${vehicleList}" var="vehicle">
                            <option value="${vehicle.id}" <c:if test="${vehicle.id == order.vehicleId}">selected</c:if> >${vehicle.carBrand} ${vehicle.model} ${vehicle.registrationNumber}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>
            Data [dostarczenia / planowanej naprawy / rozpoczęcia naprawy]
            <div class="input-group">
                <input type="text"  class="form-control" placeholder="Data dostarczenia" value="${order.dateDeliveredToRepair}">
                <input type="text" class="form-control" placeholder="Data planowanej naprawy" value="${order.datePlannedRepair}">
                <input type="text" class="form-control" placeholder="Data rozpoczęcia naprawy" value="${order.dateStartedRepair}">
            </div>
            <br>

            Pracownik
            <div class="form-row">
                <div class="form-group col-md-6">
                    <select id="employeeSelect" class="custom-select" aria-label="Imie i nazwisko pracownika">
                        <option value=""></option>
                        <c:forEach items="${employeeList}" var="employee">
                            <option value="${employee.id}" <c:if test="${employee.id == order.employeeId}">selected</c:if> >${employee.firstName} ${employee.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            Opis awarii
            <div class="form-group">
                <textarea class="form-control" placeholder="Opis problemu / awarii" rows="3">${order.problemDescription}</textarea>
            </div>

            Opis naprawy
            <div class="form-group">
                <textarea class="form-control" placeholder="Opis problemu / awarii" rows="3">${order.repairDescription}</textarea>
            </div>

            Koszt warsztatu [części / koszt roboczogodziny / liczba roboczogodzin]
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Koszt części" value="${order.costOfParts}">
                <input id="costOfmanHour" type="text" class="form-control" placeholder="Koszt roboczogodziny" value="${order.costOfmanHour}">
                <input type="text" class="form-control" placeholder="Liczba roboczogodzin" value="${order.manHour}">
            </div>
            <br>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="costCustomer">Koszt naprawy dla klienta</label>
                    <input type="text" class="form-control" placeholder="Koszt dla klienta" id="costCustomer" value="${order.repairCostForCustommer}">
                </div>

            </div>
        </form>
        </div>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

