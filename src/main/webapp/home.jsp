<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Homework 33</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mb-3 text-center">
    <h1>Homework 33</h1>
    <hr class="hr">
</div>

<c:if test="${not empty message}">
    <div class="container text-center alert alert-info">
            ${message}
    </div>
</c:if>

<div class="container mb-4 d-flex gap-2 flex-wrap">
    <button class="btn btn-outline-warning" type="button"
            data-bs-toggle="collapse"
            data-bs-target="#addUserForm"
            aria-expanded="false"
            aria-controls="addUserForm">
        Add User
    </button>
    <button class="btn btn-outline-warning"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#deleteUserForm"
            aria-expanded="false"
            aria-controls="deleteUserForm">
        Delete User
    </button>
    <button class="btn btn-outline-warning"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#printUsersForm"
            aria-expanded="false"
            aria-controls="printUsersForm">
        Print Users
    </button>
    <button class="btn btn-outline-warning"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#updateUserByUsernameForm"
            aria-expanded="false"
            aria-controls="updateUserByUsernameForm">
        Update User by username
    </button>
</div>

<div class="container">

    <!-- ФОРМА ДОБАВЛЕНИЯ -->
    <div class="collapse mt-3" id="addUserForm">
        <div class="card card-body">
            <form method="post" action="${pageContext.request.contextPath}/add">
                <div class="d-flex align-items-center gap-3 mb-3 flex-wrap">
                    <label for="add-name" class="form-label mb-0">Name:</label>
                    <input type="text" class="form-control w-auto" id="add-name" name="name" placeholder="name" required>

                    <label for="add-username" class="form-label mb-0">Username:</label>
                    <input type="text" class="form-control w-auto" id="add-username" name="username" placeholder="username" required>

                    <label for="add-password" class="form-label mb-0">Password:</label>
                    <input type="password" class="form-control w-auto" id="add-password" name="password" required>

                    <button type="submit" class="btn btn-success">Save User</button>
                </div>
            </form>
        </div>
    </div>

    <!-- ФОРМА УДАЛЕНИЯ (Исправлена лишняя запятая в атрибутах div!) -->
    <div class="collapse mt-3" id="deleteUserForm">
        <div class="card card-body">
            <form method="post" action="${pageContext.request.contextPath}/delete">
                <div class="d-flex align-items-center gap-3 mb-3">
                    <label for="delete-username" class="form-label mb-0">Enter username:</label>
                    <input type="text" class="form-control w-auto" id="delete-username" name="username" placeholder="username" required>
                    <button type="submit" class="btn btn-danger">Delete user</button>
                </div>
            </form>
        </div>
    </div>

    <!-- ФОРМА ВЫВОДА ПОЛЬЗОВАТЕЛЕЙ -->
    <div class="collapse mt-3" id="printUsersForm">
        <div class="card card-body">
            <h5>Список пользователей:</h5>
            <a href="${pageContext.request.contextPath}/find" class="btn btn-sm btn-outline-secondary">
                Обновить
            </a>
            <c:if test="${not empty users}">
                <c:forEach var="user" items="${users}">
                    <p class="mb-1">${user.username} (${user.name})</p>
                </c:forEach>
            </c:if>
            <c:if test="${empty users}">
                <p>Список пуст.</p>
            </c:if>
        </div>
    </div>

    <!-- ФОРМА ОБНОВЛЕНИЯ ПОЛЬЗОВАТЕЛЕЙ ПО USERNAME -->
    <div class="collapse mt-3" id="updateUserByUsernameForm">
        <div class="card card-body">
            <form method="post" action="${pageContext.request.contextPath}/update-username">
                <div class="d-flex align-items-center gap-3 mb-3">
                    <label for="upgrade-username" class="form-label mb-0">Enter username:</label>
                    <input type="text" class="form-control w-auto" id="upgrade-username" name="oldUsername" placeholder="old username" required>
                    <input type="text" class="form-control w-auto" id="upgrade-username" name="newUsername" placeholder="new username" required>
                    <button type="submit" class="btn btn-danger">Upgrade user by username</button>
                </div>
            </form>
        </div>
    </div>


</div>

<!-- Подключение JS Bootstrap 5.3.3 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>