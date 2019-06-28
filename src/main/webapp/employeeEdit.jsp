<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
    <script src="js/order.js"></script>
    <div class="container">
        <c:if test="${msg!=null}">
            <br><br><br>
            <div class="alert alert-danger" role="alert">
                    ${msg}
            </div>
        </c:if>
        <br><br><br>

        <div class="rounded border p-5">

            <h4 class="cover-heading">Edycja danych pracownika</h4>
            <p>
            <form style="text-align: left" action="${pageContext.request.contextPath}/employee/employeeEdit" method="post">
                <input type="hidden" value="${employee.id}" name="id">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="firstName">Imię</label>
                        <input required type="text" class="form-control" placeholder="Imię pracownika" name="firstName" id="firstName" value="${employee.firstName}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="lastName">Nazwisko</label>
                        <input type="text" class="form-control" placeholder="Nazwisko pracownika" name="lastName" id="lastName" value="${employee.lastName}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-10">
                        <label for="address">Adres</label>
                        <input type="text" class="form-control" placeholder="Adres zamieszkania pracownika" name="address" id="address" value="${employee.address}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="phoneNumber">Telefon</label>
                        <input type="text" class="form-control" placeholder="Telefon pracownika" name="phoneNumber" id="phoneNumber" value="${employee.phoneNumber}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="hourCost">Koszt roboczogodziny</label>
                        <input required type="number" class="form-control" placeholder="Koszt roboczogodziny pracownika" name="hourCost" id="hourCost" value="${employee.hourCost}">
                    </div>
                </div>

                Notatka
                <div class="form-group">
                    <textarea class="form-control" placeholder="Notatka dotycząca pracownika" rows="3" name="note" id="note">${employee.note}</textarea>
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

