<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: szambur
  Date: 25.05.19
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
    <main role="main" class="inner cover">

        <c:if test="${msg!=null}">
        <div class="alert alert-danger" role="alert">
            ${msg}
        </div>
        <br><br><br><br>
        </c:if>
        <h4 class="cover-heading">Aktualne zlecenia </h4>
        <p>
        <table class="table table-dark">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Marka</th>
                <th scope="col">Model</th>
                <th scope="col">Opis</th>
                <th scope="col">Pracownik</th>
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
                <td><a role="button" class="btn btn-primary" href="${pageContext.request.contextPath}/orderDetails?orderId=${order.id}">Szczegóły</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>

    </main>
<jsp:include page="footer.jsp"/>
