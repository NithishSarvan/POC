<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div th:fragment="alerts">
    <!-- Success Alert -->
    <div th:if="${successMessage != null}" class="alert alert-success alert-dismissible fade show">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <span th:text="${successMessage}"></span>
    </div>

    <!-- Error Alert -->
    <div th:if="${errorMessage != null}" class="alert alert-danger alert-dismissible fade show">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <span th:text="${errorMessage}"></span>
    </div>

    <!-- Validation Errors -->
<!--    <div th:if="${#fields.hasErrors('*')}" class="alert alert-danger alert-dismissible fade show">-->
<!--        <button type="button" class="close" data-dismiss="alert">&times;</button>-->
<!--        <h5 class="alert-heading">Please fix these errors:</h5>-->
<!--        <ul>-->
<!--&lt;!&ndash;            <li th:each="err : ${#fields.errors('*')}" th:text="${err}"></li>&ndash;&gt;-->
<!--        </ul>-->
<!--    </div>-->
</div>
</body>
</html>