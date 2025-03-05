<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de Inventario</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${inventario.id == null}">
                Nuevo Inventario
            </c:when>
            <c:otherwise>
                Editar Inventario
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción -->
    <c:choose>
        <c:when test="${inventario.id == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/inventario/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/inventario/actualizar/${inventario.id}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${inventario.id != null}">
            <input type="hidden" name="id" value="${inventario.id}" />
        </c:if>

        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="${inventario.precio}" required/><br><br>

        <label>Stock:</label>
        <input type="number" name="stock" value="${inventario.stock}" required/><br><br>

        <label>Fecha Registro:</label>
        <input type="date" name="fechaRegistro" value="${inventario.fechaRegistro}" required/><br><br>

        <label>Producto Disponible:</label>
        <input type="checkbox" name="productoDisponible" value="true" <c:if test="${inventario.productoDisponible}">checked</c:if> /><br><br>

        <label>ID Producto:</label>
        <input type="number" name="producto.idProducto" value="${inventario.producto.idProducto}" required/><br><br>

        <!-- Campo oculto para Administrador: si no se envía, se asigna por defecto "1" -->
        <c:choose>
            <c:when test="${not empty inventario.administrador}">
                <input type="hidden" name="administrador.idAdmin" value="${inventario.administrador.idAdmin}" />
            </c:when>
            <c:otherwise>
                <input type="hidden" name="administrador.idAdmin" value="1" />
            </c:otherwise>
        </c:choose>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/inventario/listar">Volver a la Lista</a>
</body>
</html>
