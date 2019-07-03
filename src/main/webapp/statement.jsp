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
        <h4 class="cover-heading">Zestawienie : ${statementName}</h4>
        <h6>Zakres czasu  : od ${dateFrom} do ${dateTo}</h6>
        <p>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <c:forEach var="col" items="${statementRows[0]}">
                    <th scope="col">${col.key}</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${statementRows}" var="row" varStatus="i">
                <tr>
                    <th scope="row">${i.index+1}</th>
                    <c:forEach items="${row}" var="col">
                        <td>${row[col.key]}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>
    </div>
</main>
<jsp:include page="footer.jsp"/>

