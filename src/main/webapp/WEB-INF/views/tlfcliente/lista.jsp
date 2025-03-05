<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Teléfonos de Cliente</title>
</head>
<body>
    <h2>Lista de Teléfonos de Cliente</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Teléfono</th>
                <th>Teléfono</th>
                <th>ID Cliente</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tel" items="${telefonos}">
                <tr>
                    <td>${tel.idTlfCliente}</td>
                    <td>${tel.tlfCliente}</td>
                    <td>
                        <c:if test="${tel.cliente != null}">
                            ${tel.cliente.idCliente}
                        </c:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/tlfcliente/editar/${tel.idTlfCliente}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/tlfcliente/eliminar/${tel.idTlfCliente}" onclick="return confirm('¿Está seguro de eliminar este teléfono?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/tlfcliente/nuevo">Agregar Teléfono</a>
</body>
</html>
