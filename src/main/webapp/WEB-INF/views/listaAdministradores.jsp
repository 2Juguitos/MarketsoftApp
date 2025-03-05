<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lista de Administradores</title>
</head>
<body>
    <h2>Lista de Administradores</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="administrador" items="${administradores}">
                <tr>
                    <td>${administrador.idAdmin}</td>
                    <td>${administrador.nombreAdmin}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/administradores/editar/${administrador.idAdmin}">Editar</a>
                         |
                        <a href="${pageContext.request.contextPath}/administradores/eliminar/${administrador.idAdmin}" onclick="return confirm('¿Estás seguro de eliminar este administrador?')">Eliminar</a>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/administradores/nuevo">Agregar Administrador</a>


</body>
</html>

