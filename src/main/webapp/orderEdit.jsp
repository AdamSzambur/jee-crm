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

        <h4 class="cover-heading">Edycja zlecenia</h4>
        <p>
        <form style="text-align: left" method="post" action="${pageContext.request.contextPath}/order/orderEdit">
            <input hidden name="orderId" value="${order.id}">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="status">Status zlecenia</label>
                    <div class="input-group" id="status">
                        <select name="statusId" class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon">
                            <c:forEach items="${statusList}" var="status">
                                <option value="${status.id}" <c:if test="${status.id == order.statusId}">selected</c:if> >${status.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            Klient
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="input-group">
                        <select name="customerId"  id="clientSelect" class="custom-select" aria-label="Imie i nazwisko klienta" onchange="$(this).siblings('input').click()">
                            <option value="0"></option>
                            <c:forEach items="${customerList}" var="customer">
                                <option value="${customer.id}" <c:if test="${customer.id == order.customerId}">selected</c:if> >${customer.firstName} ${customer.lastName}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" name="action" value="customer" hidden>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="window.location.href='${pageContext.request.contextPath}/order/customerAdd?orderId=${order.id}'">Nowy klient</button>
                        </div>
                    </div>
                </div>
            </div>
            Samochód [marka/model / numer rejestracyjny]
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="input-group">
                        <select name="vehicleId" id="vehicleSelect" class="custom-select" aria-label="Marka i model pojazdu" onchange="$(this).siblings('input').click()">
                            <option value="0"></option>
                            <c:forEach items="${vehicleList}" var="vehicle">
                                <option value="${vehicle.id}" <c:if test="${vehicle.id == order.vehicleId}">selected</c:if> >${vehicle.carBrand} ${vehicle.model} ${vehicle.registrationNumber}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" name="action" value="vehicle" hidden>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" onclick="window.location.href='${pageContext.request.contextPath}/order/vehicleAdd?orderId=${order.id}&customerId=${order.customerId}'">Nowy pojazd</button>
                        </div>
                    </div>
                </div>
            </div>
            Data [dostarczenia / planowanej naprawy / rozpoczęcia naprawy]
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="input-group">
                        <input name="dateDeliveredToRepair" type="date"  class="form-control" placeholder="Data dostarczenia" value="${order.dateDeliveredToRepair}">
                        <input name="datePlannedRepair" type="date" class="form-control" placeholder="Data planowanej naprawy" value="${order.datePlannedRepair}">
                        <input name="dateStartedRepair" type="date" class="form-control" placeholder="Data rozpoczęcia naprawy" value="${order.dateStartedRepair}">
                    </div>
                </div>
            </div>
            Pracownik
            <div class="form-row">
                <div class="form-group col-md-4">
                    <div class="input-group">
                        <select name="employeeId" id="employeeSelect" class="custom-select" onchange="$(this).siblings('input').click()">
                            <option value="0"></option>
                            <c:forEach items="${employeeList}" var="employee">
                                <option value="${employee.id}" <c:if test="${employee.id == order.employeeId}">selected</c:if> >${employee.firstName} ${employee.lastName}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" name="action" value="employee" hidden>
                    </div>
                </div>
            </div>
            Opis awarii
            <div class="form-row">
                <div class="form-group col-md-12">
                    <textarea name="problemDescription" class="form-control" placeholder="Opis problemu / awarii" rows="3">${order.problemDescription}</textarea>
                </div>
            </div>
            Opis naprawy
            <div class="form-row">
                <div class="form-group col-md-12">
                    <textarea name="repairDescription" class="form-control" placeholder="Opis problemu / awarii" rows="3">${order.repairDescription}</textarea>
                </div>
            </div>
            Koszt warsztatu [części / koszt roboczogodziny / liczba roboczogodzin]
            <div class="form-row">
                <div class="form-group col-md-6">
                    <div class="input-group">
                        <input name="costOfParts" type="text" class="form-control" placeholder="Koszt części" value="${order.costOfParts}">
                        <input name="costOfmanHour" id="costOfmanHour" type="text" class="form-control" placeholder="Koszt roboczogodziny" value="${order.costOfmanHour}">
                        <input name="manHour" type="text" class="form-control" placeholder="Liczba roboczogodzin" value="${order.manHour}">
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="costCustomer">Koszt naprawy dla klienta</label>
                    <input name="repairCostForCustommer" type="text" class="form-control" placeholder="Koszt dla klienta" id="costCustomer" value="${order.repairCostForCustommer}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-12">
                    <button type="submit" class="btn btn-primary" name="action" value="update">Zapisz zmiany</button>
                </div>
            </div>
        </form>
        </div>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

