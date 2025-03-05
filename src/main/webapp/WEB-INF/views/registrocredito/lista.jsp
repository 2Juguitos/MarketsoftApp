<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Registros de Crédito</title>
</head>
<body>
    <h1>Lista de Registros de Crédito</h1>
    <div>
        <a href="${pageContext.request.contextPath}/regcredito/form">Nuevo Registro</a>
    </div>
    <br>
    <table border="1">
        <thead>
            <tr>
                <th>ID Crédito</th>
                <th>ID Administrador</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="registro" items="${registros}">
                <tr>
                    <!-- Mostrar claves compuestas -->
                    <td>${registro.id.creditoIdCredito}</td>
                    <td>${registro.id.administradorIdAdmin}</td>

                    <!-- Acciones: Ver, Editar y Eliminar -->
                    <td>
                        <a href="${pageContext.request.contextPath}/regcredito/ver/${registro.id.creditoIdCredito}/${registro.id.administradorIdAdmin}">Ver</a>
                        |
                        <a href="${pageContext.request.contextPath}/regcredito/form?id.creditoIdCredito=${registro.id.creditoIdCredito}&id.administradorIdAdmin=${registro.id.administradorIdAdmin}">Editar</a>
                        |
                        <a href="${pageContext.request.contextPath}/regcredito/eliminar/${registro.id.creditoIdCredito}/${registro.id.administradorIdAdmin}"
                           onclick="return confirm('¿Estás seguro de eliminar este registro?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>