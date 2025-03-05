<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Créditos</title>
</head>
<body>
    <h1>Lista de Créditos</h1>
    <a href="${pageContext.request.contextPath}/credito/form">Nuevo Crédito</a>
    <br><br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Cupo de Crédito</th>
                <th>Saldo Pendiente</th>
                <th>Cliente</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="credito" items="${creditos}">
                <tr>
                    <td>${credito.idCredito}</td>
                    <td>${credito.cupoCredito}</td>
                    <td>${credito.saldoPendCredito}</td>
                    <td>${credito.cliente.nombreCliente}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/credito/ver/${credito.idCredito}">Ver</a> |
                        <a href="${pageContext.request.contextPath}/credito/editar/${credito.idCredito}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/credito/eliminar/${credito.idCredito}"
                           onclick="return confirm('¿Estás seguro de eliminar este crédito?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>