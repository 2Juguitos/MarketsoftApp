<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
    <h2>Lista de Clientes</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Cliente</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>ID Administrador</th>
                <th>Teléfonos</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.nombreCliente}</td>
                    <td>${cliente.apellidoCliente}</td>
                    <td>
                        <c:if test="${cliente.administrador != null}">
                            ${cliente.administrador.idAdmin}
                        </c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty cliente.telefonos}">
                                <c:forEach var="telefono" items="${cliente.telefonos}">
                                    ${telefono.tlfCliente}<br/>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                Sin Teléfonos
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/clientes/editar/${cliente.idCliente}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/clientes/eliminar/${cliente.idCliente}" onclick="return confirm('¿Estás seguro de eliminar este cliente?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/clientes/nuevo">Agregar Cliente</a>
</body>
</html>
