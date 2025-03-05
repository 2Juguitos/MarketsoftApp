<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de Cliente</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${cliente.idCliente == null}">
                Nuevo Cliente
            </c:when>
            <c:otherwise>
                Editar Cliente
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción -->
    <c:choose>
        <c:when test="${cliente.idCliente == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/clientes/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/clientes/actualizar/${cliente.idCliente}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${cliente.idCliente != null}">
            <input type="hidden" name="idCliente" value="${cliente.idCliente}" />
        </c:if>
        <label>Nombre:</label>
        <input type="text" name="nombreCliente" value="${cliente.nombreCliente}" required /><br><br>

        <label>Apellido:</label>
        <input type="text" name="apellidoCliente" value="${cliente.apellidoCliente}" required /><br><br>

        <!-- Para la relación con Administrador, si se desea asignar un valor por defecto, se puede usar un campo oculto -->
        <input type="hidden" name="administrador.idAdmin" value="${cliente.administrador != null ? cliente.administrador.idAdmin : 1}" />

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/clientes/listar">Volver a la Lista</a>
</body>
</html>
