<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title th:text="${pageTitle} ?: 'Invoice System'"></title>

    <!-- Favicon -->
    <link rel="icon" th:href="@{/image/favicon.png}" type="image/png">

    <th:block th:remove="tag">
        <!-- Non-Thymeleaf fallback -->
        <link rel="icon" href="/image/favicon.png" type="image/png">
    </th:block>

    <!-- Add this to safely handle request attributes -->
    <th:block th:with="currentRequestURI=${#request.getRequestURI()} ?: ''"></th:block>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <!-- Bootstrap 5 -->
    <link th:href="@{/webjars/bootstrap/5.3.0/css/bootstrap.min.css}" rel="stylesheet">

    <!-- Font Awesome 6 -->
    <link th:href="@{/webjars/font-awesome/6.4.0/css/all.min.css}" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link th:href="@{/webjars/bootstrap-icons/1.10.0/font/bootstrap-icons.css}" rel="stylesheet">

    <!-- AdminLTE Theme -->
    <link th:href="@{/webjars/AdminLTE/3.2.0/css/adminlte.min.css}" rel="stylesheet">

    <!-- Custom CSS -->
    <link th:href="@{/css/custom.css}" rel="stylesheet">
    <link rel="stylesheet" th:href="@{/css/custom.css}">

    <!-- DataTables CSS -->
    <link th:href="@{/webjars/datatables/2.1.8/css/dataTables.bootstrap5.min.css}" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini layout-fixed">

<!-- Add this layout fragment definition -->
<div th:fragment="layout(title, content)" class="wrapper">
    <head>
        <title th:replace="${title}">Default Title</title>
    </head>

    <!-- Preloader -->
    <div class="preloader flex-column justify-content-center align-items-center">
        <img class="animation__shake" th:src="@{/image/logo.jpg}" alt="Logo" height="60" width="60">
    </div>

    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button">
                    <i class="fas fa-bars"></i>
                </a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a th:href="@{/}" class="nav-link">Home</a>
            </li>
        </ul>
    </nav>

    <!-- Sidebar -->
    <div th:replace="~{fragments/sidebar :: sidebar}"></div>

    <!-- Content Wrapper -->
    <div class="content-wrapper">
        <!-- Content Header -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0" th:text="${pageTitle} ?: 'Dashboard'"></h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a th:href="@{/}">Home</a></li>
                            <li class="breadcrumb-item active" th:text="${pageTitle} ?: 'Dashboard'"></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <!-- Main Content -->
        <section class="content">
            <div class="container-fluid">
                <div th:replace="~{fragments/alerts :: alerts}"></div>
                <div th:replace="${content}"></div>
            </div>
        </section>
    </div>

    <!-- Footer -->
    <footer class="main-footer">
        <strong>Copyright &copy; <span th:text="${#temporals.year(#temporals.createNow())}"></span></strong>
        All rights reserved.
    </footer>
</div>

<!-- REQUIRED SCRIPTS -->
<!-- jQuery -->
<script th:src="@{/webjars/jquery/3.6.4/jquery.min.js}"></script>
<!-- Bootstrap 5 -->
<script th:src="@{/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js}"></script>
<!-- AdminLTE -->
<script th:src="@{/webjars/AdminLTE/3.2.0/js/adminlte.min.js}"></script>
<!-- DataTables -->
<script th:src="@{/webjars/datatables/2.1.8/js/jquery.dataTables.min.js}"></script>
<script th:src="@{/webjars/datatables/2.1.8/js/dataTables.bootstrap5.min.js}"></script>
<!-- Custom JS -->
<script th:src="@{/js/app.js}"></script>
</body>
</html>