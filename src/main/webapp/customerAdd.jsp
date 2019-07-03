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

            <h4 class="cover-heading">Dodawanie nowego klienta</h4>
            <p>
            <form style="text-align: left" action="${pageContext.request.contextPath}/${cat}/customerAdd" method="post">
                <input hidden name="orderId" value="${orderId}">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="firstName">Imię</label>
                        <input required type="text" class="form-control" placeholder="Imię klienta" name="firstName" id="firstName" value="${customer.firstName}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="lastName">Nazwisko</label>
                        <input type="text" class="form-control" placeholder="Nazwisko klienta" name="lastName" id="lastName" value="${customer.lastName}">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="birthDate">Data urodzenia</label>
                        <input type="date" class="form-control" placeholder="Data urodzenia" name="birthDate" id="birthDate" value="${customer.birthDate}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="email">E-mail</label>
                        <input type="email" class="form-control" placeholder="Adres email" name="email" id="email" value="${customer.email}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <button type="submit" class="btn btn-primary">Dodaj nowego klienta</button>
                    </div>
                </div>
            </form>
        </div>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

