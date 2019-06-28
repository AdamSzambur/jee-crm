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
                Czy napewno chcesz usunąć tego pracownika
                <br><br>
                <a role="button" class="btn btn-danger" href="" id="deleteBtn">Usuń</a>
                <a role="button" class="btn btn-success" href="" onclick="event.preventDefault(); $('#deleteMsg').toggleClass('invisible');">Anuluj</a>
            </p>
        </div>

        <br>
        <h4 class="cover-heading">Lista Pracowników </h4>
        <p>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Imię</th>
                <th scope="col">Nazwisko</th>
                <th scope="col">Adres</th>
                <th scope="col">Telefon</th>
                <th scope="col">Koszt [pln/godz]</th>
                <th scope="col">Notatka</th>
                <th scope="col">Akcja</th>
            </tr>
            </thead>
            <tbody>

                <c:forEach items="${employees}" var="employee" varStatus="i">
                <tr>
                    <th scope="row">${i.index+1}</th>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.address}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.hourCost}</td>
                    <td>${employee.note}</td>
                    <td>
                        <div class="btn-group" role="group" aria-label="First group">
                            <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/employee/employeeEdit?employeeId=${employee.id}'">Edytuj</button>
                            <button type="button" class="btn btn-danger"  onclick="event.preventDefault();$('#deleteBtn').attr('href','${pageContext.request.contextPath}/employee/employeeDel?employeeId=${employee.id}'); $('#deleteMsg').toggleClass('invisible');">Usuń</button>
                            <button type="button" class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/employee/employeeOrder?employeeId=${employee.id}'">Zlecenia</button>
                        </div>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td colspan="8">
                        <button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/employee/employeeAdd'">Dodaj nowego pracownika</button>
                    </td>
                </tr>
            </tbody>
        </table>
        </p>
    </div>



</main>
<jsp:include page="footer.jsp"/>

