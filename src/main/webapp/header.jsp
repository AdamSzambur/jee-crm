<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="pl" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Warsztat samochodowy</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/da6d9e6874.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
</head>
<body class="d-flex flex-column h-100">
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">JEE CRM</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item <c:if test="${empty cat}">active</c:if>">
                    <a class="nav-link" href="${pageContext.request.contextPath}"><i class="fas fa-home"></i> Strona główna<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle <c:if test="${cat == 'customer'}">active</c:if>" href="#" id="navbarDropdownMenuLink1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-secret"></i> Klienci
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/customerList">Lista klientów</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/customerAdd">Dodaj klienta</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/customerVehicle">Pojazdy klienta</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/customerOrder">Zlecenia klienta</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle <c:if test="${cat == 'vehicle'}">active</c:if>" href="#" id="navbarDropdownMenuLink2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-car"></i> Pojazdy
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/vehicle/vehicleList">Lista pojazdów</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/vehicle/vehicleAdd">Dodaj pojazd</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle <c:if test="${cat == 'order'}">active</c:if>" href="#" id="navbarDropdownMenuLink3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-folder-open"></i> Zlecenia
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink3">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/order/orderList">Lista zleceń</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/order/orderAdd">Dodaj zlecenie</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle <c:if test="${cat == 'employee'}">active</c:if>" href="#" id="navbarDropdownMenuLink4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-users"></i> Pracownicy
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink4">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/employee/employeeList">Lista pracowników</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/employee/employeeAdd">Dodaj pracownika</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/employee/employeeOrder">Zlecenia pracownika</a>
                    </div>
                </li>

                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle <c:if test="${cat == 'statement'}">active</c:if>" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-sort-amount-up-alt"></i> Zestawienia
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/statement/Statement?statement=StatementEmployeeHours&statementName=Liczba%20przepracowanych%20godzin%20przez%20pracownikow">Liczba przepracowanych godzin</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/statement/Statement?statement=StatementProfit&statementName=Zyski%20NETTO%20z%20dzialalnosci">Zysk NETTO z działalności</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</header>
