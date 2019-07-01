<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
    <div class="container">
        <c:if test="${msg!=null}">
            <br><br><br>
            <div class="alert alert-danger" role="alert">
                    ${msg}
            </div>
        </c:if>
        <br><br><br>

        <div class="rounded border p-5">

            <h4 class="cover-heading">Dodawanie nowego pojazdu</h4>
            <p>
            <form style="text-align: left" action="${pageContext.request.contextPath}/${cat}/vehicleAdd" method="post">
                <input type="hidden" value="${vehicle.id}" name="id">
                <input type="hidden" value="${customerId}" name="customerId">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="vehicleCustomerId">Klient</label>
                        <select id="vehicleCustomerId" class="custom-select" aria-label="Imie i nazwisko klienta" name="vehicleCustomerId">
                            <c:forEach items="${customerList}" var="customer">
                                <option value="${customer.id}" <c:if test="${customer.id == customerId}">selected</c:if> >${customer.firstName} ${customer.lastName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="carBrand">Marka</label>
                        <input required type="text" class="form-control" placeholder="Marka" name="carBrand" id="carBrand" value="${vehicle.carBrand}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="model">Model</label>
                        <input type="text" class="form-control" placeholder="Model" name="model" id="model" value="${vehicle.model}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="registrationNumber">Nr rejestracyjny</label>
                        <input required type="text" class="form-control" placeholder="Numer rejestracyjny" name="registrationNumber" id="registrationNumber" value="${vehicle.registrationNumber}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="productionYear">Rok produkcji</label>
                        <input type="text" pattern="[12][0-9]{3}" class="form-control" placeholder="Rok produkcji" name="productionYear" id="productionYear" value="${vehicle.productionYear}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="nextTechnicalInspection">Data następnego przeglądu</label>
                        <input type="date" class="form-control" placeholder="Data następnego przeglądu" name="nextTechnicalInspection" id="nextTechnicalInspection" value="${vehicle.nextTechnicalInspection}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <button type="submit" class="btn btn-primary">Zapisz zmiany</button>
                    </div>
                </div>
            </form>
        </div>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

