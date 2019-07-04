<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<main role="main" class="flex-shrink-0">
    <br>
    <div class="container">
        <%--        message from servlet--%>
        <c:if test="${param.msg!=null}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    ${param.msg}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
            <c:forEach items="${birthdayList}" var="birthday">
                <form action="${pageContext.request.contextPath}/sendNotify" method="post">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <button type="submit" class="btn btn-success"><i class="far fa-envelope"></i> Wyślij</button>
                        życzenia urodzinowe do klienta ${birthday.firstName} ${birthday.lastName}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <input type="hidden" name="id" value="${birthday.id}">
                    <input type="hidden" name="table" value="customer">
                </form>
            </c:forEach>

            <c:forEach items="${inspectionList}" var="inspection">
                <form action="${pageContext.request.contextPath}/sendNotify" method="post">
                    <div class="alert alert-primary alert-dismissible fade show" role="alert">
                        <button type="submit" class="btn btn-primary"><i class="far fa-envelope"></i> Wyślij</button>
                        przypomnienie o inspekcji dla ${inspection.carBrand} ${inspection.model} ${inspection.registrationNumber}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <input type="hidden" name="id" value="${inspection.id}">
                    <input type="hidden" name="table" value="vehicle">
                </form>
            </c:forEach>

        <br>
        <h4 class="cover-heading">Aktualne zlecenia </h4>
        <p>
        <table class="table table-striped">
            <thead class="thead-dark">
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
    </div>
</main>
<jsp:include page="footer.jsp"/>

