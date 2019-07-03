<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <%--        message from servlet--%>
        <c:if test="${msg!=null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <br>
            <h4 class="cover-heading">Zlecenia pracownika </h4>
            <form action="${pageContext.request.contextPath}/employee/employeeOrder" method="get">
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <div class="input-group">
                            <select id="employeeId" class="custom-select" onchange="this.form.submit()" name="employeeId">
                                <option value="">Wszyscy pracownicy</option>
                                <c:forEach items="${employeeList}" var="employee">
                                    <option value="${employee.id}" <c:if test="${employee.id == employeeId}">selected</c:if> >${employee.firstName} ${employee.lastName}</option>
                                </c:forEach>
                            </select>
                            <select id="statusId" class="custom-select" onchange="this.form.submit()" name="statusId">
                                <option value="">Wszyskie statusy</option>
                                <c:forEach items="${statusList}" var="status">
                                    <option value="${status.id}" <c:if test="${status.id == statusId}">selected</c:if> >${status.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </form>

            <p>
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Marka</th>
                    <th scope="col">Model</th>
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
                        <td>${order.problemDescription}</td>
                        <td>${order.employeeFirstName} ${order.employeeLastName}</td>
                        <td><c:forEach items="${statusList}" var="status"><c:if test="${order.statusId == status.id}">${status.value}</c:if></c:forEach></td>
                        <td><a role="button" class="btn btn-primary" href="${pageContext.request.contextPath}/employee/orderDetails?orderId=${order.id}">Szczegóły</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

