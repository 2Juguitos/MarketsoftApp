<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de Producto</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${producto.idProducto == null}">
                Nuevo Producto
            </c:when>
            <c:otherwise>
                Editar Producto
            </c:otherwise>
        </c:choose>
    </h2>

    <c:choose>
        <c:when test="${producto.idProducto == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/productos/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/productos/actualizar/${producto.idProducto}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${producto.idProducto != null}">
            <input type="hidden" name="idProducto" value="${producto.idProducto}" />
        </c:if>

        <label>Marca:</label>
        <input type="text" name="marcaProd" value="${producto.marcaProd}" required /><br><br>

        <label>Nombre:</label>
        <input type="text" name="nombreProd" value="${producto.nombreProd}" required /><br><br>

        <label>ID Categoría:</label>
        <input type="number" name="categoria.idCategoria" value="${producto.categoria.idCategoria}" required /><br><br>

        <!-- Campo oculto para Administrador -->
        <c:choose>
            <c:when test="${not empty producto.administrador}">
                <input type="hidden" name="administrador.idAdmin" value="${producto.administrador.idAdmin}" />
            </c:when>
            <c:otherwise>
                <input type="hidden" name="administrador.idAdmin" value="1" />
            </c:otherwise>
        </c:choose>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/productos/listar">Volver a la Lista</a>
</body>
</html>
