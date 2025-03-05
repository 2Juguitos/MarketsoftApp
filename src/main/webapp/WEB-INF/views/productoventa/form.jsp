<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de ProductoVenta</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${prodVenta.id == null}">
                Nuevo Registro ProductoVenta
            </c:when>
            <c:otherwise>
                Editar Registro ProductoVenta
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción -->
    <c:choose>
        <c:when test="${prodVenta.id == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/productoventa/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/productoventa/actualizar/${prodVenta.id}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${prodVenta.id != null}">
            <input type="hidden" name="id" value="${prodVenta.id}" />
        </c:if>

        <label>Precio Unitario:</label>
        <input type="number" step="0.01" name="precioUnitario" value="${prodVenta.precioUnitario}" required/><br><br>

        <label>Cantidad:</label>
        <input type="number" name="cantidad" value="${prodVenta.cantidad}" required/><br><br>


        <label>ID Producto:</label>
        <input type="number" name="producto.idProducto" value="${prodVenta.producto.idProducto}" required/><br><br>


        <label>ID Venta:</label>
        <input type="number" name="venta.idVenta" value="${prodVenta.venta.idVenta}" required/><br><br>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/productoventa/listar">Volver a la Lista</a>
</body>
</html>
