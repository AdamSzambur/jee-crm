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

                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="status">Status zlecenia</label>
                        <div class="input-group" id="status">
                            <input readonly class="form-control" type="text" value="${order.statusValue}">
                        </div>
                    </div>
                </div>
                Klient
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <div class="input-group">
                            <input readonly type="text" class="form-control" value="${order.customerFirstName} ${order.customerLastName}">
                        </div>
                    </div>
                </div>
                Samochód [marka/model / numer rejestracyjny]
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <div class="input-group">
                            <input readonly type="text" class="form-control" value="${order.carBrand} ${order.model} ${order.registrationNumber}">
                        </div>
                    </div>
                </div>
                Data [dostarczenia / planowanej naprawy / rozpoczęcia naprawy]
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <div class="input-group">
                            <input readonly name="dateDeliveredToRepair" type="date"  class="form-control" placeholder="Data dostarczenia" value="${order.dateDeliveredToRepair}">
                            <input readonly name="datePlannedRepair" type="date" class="form-control" placeholder="Data planowanej naprawy" value="${order.datePlannedRepair}">
                            <input readonly name="dateStartedRepair" type="date" class="form-control" placeholder="Data rozpoczęcia naprawy" value="${order.dateStartedRepair}">
                        </div>
                    </div>
                </div>
                Pracownik
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <div class="input-group">
                            <input readonly type="text" class="form-control" value="${order.employeeFirstName} ${order.employeeLastName}">
                        </div>
                    </div>
                </div>
                Opis awarii
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <textarea readonly name="problemDescription" class="form-control" placeholder="Opis problemu / awarii" rows="3">${order.problemDescription}</textarea>
                    </div>
                </div>
                Opis naprawy
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <textarea readonly name="repairDescription" class="form-control" placeholder="Opis problemu / awarii" rows="3">${order.repairDescription}</textarea>
                    </div>
                </div>
                Koszt warsztatu [części / koszt roboczogodziny / liczba roboczogodzin]
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <div class="input-group">
                            <input readonly name="costOfParts" type="text" class="form-control" placeholder="Koszt części" value="${order.costOfParts}">
                            <input readonly name="costOfmanHour" id="costOfmanHour" type="text" class="form-control" placeholder="Koszt roboczogodziny" value="${order.costOfmanHour}">
                            <input readonly name="manHour" type="text" class="form-control" placeholder="Liczba roboczogodzin" value="${order.manHour}">
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="costCustomer">Koszt naprawy dla klienta</label>
                        <input  readonly name="repairCostForCustommer" type="text" class="form-control" placeholder="Koszt dla klienta" id="costCustomer" value="${order.repairCostForCustommer}">
                    </div>
                </div>
        </div>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

