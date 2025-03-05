<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de Categoría</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${categoria.idCategoria == null}">
                Nueva Categoría
            </c:when>
            <c:otherwise>
                Editar Categoría
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción -->
    <c:choose>
        <c:when test="${categoria.idCategoria == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/categorias/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/categorias/actualizar/${categoria.idCategoria}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <!-- Si se está editando, incluir el id oculto -->
        <c:if test="${categoria.idCategoria != null}">
            <input type="hidden" name="idCategoria" value="${categoria.idCategoria}" />
        </c:if>
        <label>Nombre:</label>
        <input type="text" name="nombreCategoria" value="${categoria.nombreCategoria}" required /><br><br>

        <label>Fecha Registro:</label>
        <input type="date" name="fechaRegistroCategoria" value="${categoria.fechaRegistroCategoria}" required /><br><br>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/categorias/listar">Volver a la Lista</a>
</body>
</html>
