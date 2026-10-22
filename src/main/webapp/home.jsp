<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19.10.2026
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div class="container mb-3 text-center">
    <h1>Homework 33</h1>
    <hr class="hr">
</div>

<c:if test="not empty message">
    <div class="container text-center">
            ${message}
    </div>
</c:if>


<button class="btn btn-outline-warning"
        type="button"
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
    Print User
</button>

<button class="btn btn-outline-warning"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#addUserForm"
        aria-expanded="false"
        aria-controls="addUserForm">
    Upgrade User
</button>

<div class="collapse mt-3" id="addUserForm">

    <%-- Add user --%>
    <form method="post"
          action="${pageContext.request.contextPath}/add">

        <div class="d-flex align-items-center gap-3 mb-3 flex-wrap">

            <label for="name" class="form-label mb-0">
                Name:
            </label>
            <input type="text"
                   class="form-control w-auto"
                   id="name"
                   name="name"
                   placeholder="name"
                   required>

            <label for="username" class="form-label mb-0">
                Username:
            </label>
            <input type="text"
                   class="form-control w-auto"
                   id="username"
                   name="username"
                   placeholder="username"
                   required>

            <label for="password" class="form-label mb-0">
                Password:
            </label>
            <input type="password"
                   class="form-control w-auto"
                   id="password"
                   name="password"
                   required>

            <button type="submit" class="btn btn-success">
                Save User
            </button>

        </div>
    </form>
</div>


<div class="collapse mt-3", id="deleteUserForm">
    <%--delete user by username--%>
    <form method="post" action="${pageContext.request.contextPath}/delete">
        <div class="d-flex align-items-center gap-3 mb-3">
            <label for="username" class="form-label mb-0">Enter username:</label>
            <input type="text"
                   class="form-control w-auto"
                   id="username"
                   name="username"
                   placeholder="username">
            <button type="submit" class="btn btn-outline-warning">Delete user</button>
        </div>
    </form>
</div>

<div class="collapse mt-3" id="printUsersForm">
    <form method="post" action="${pageContext.request.contextPath}/find">
        <c:if test="not empty users">
            <c:forEach var="user" items="${users}" >
                <p>${user.username}</p>
            </c:forEach>
        </c:if>
    </form>

</div>

<%--update user by username--%>
<form method="post" action="${pageContext.request.contextPath}/update-username">
    <div class="d-flex align-items-center gap-3 mb-3">
        <label for="username" class="form-label mb-0">Enter username:</label>
        <input type="text"
               class="form-control w-auto"
               id="username"
               name="username"
               placeholder="username">
        <button type="submit" class="btn btn-outline-warning">Delete user</button>
    </div>
</form>


<%--update user by password--%>
<form>
    <div class="d-flex align-items-center gap-3 mb-3">
        <label for="password" class="form-label mb-0">Enter password:</label>
        <input type="password"
               class="form-control w-auto"
               id="password"
               name="password"
               placeholder="password">
        <button type="submit" class="btn btn-outline-warning">Delete user</button>
    </div>
</form>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
