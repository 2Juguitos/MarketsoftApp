<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario Teléfono Cliente</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${telefono.idTlfCliente == null}">
                Nuevo Teléfono de Cliente
            </c:when>
            <c:otherwise>
                Editar Teléfono de Cliente
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción -->
    <c:choose>
        <c:when test="${telefono.idTlfCliente == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/tlfcliente/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/tlfcliente/actualizar/${telefono.idTlfCliente}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${telefono.idTlfCliente != null}">
            <input type="hidden" name="idTlfCliente" value="${telefono.idTlfCliente}" />
        </c:if>

        <label>Teléfono:</label>
        <input type="number" name="tlfCliente" value="${telefono.tlfCliente}" required/><br><br>

        <label>ID Cliente:</label>
        <input type="number" name="cliente.idCliente" value="${telefono.cliente != null ? telefono.cliente.idCliente : ''}" required/><br><br>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/tlfcliente/listar">Volver a la Lista</a>
</body>
</html>
