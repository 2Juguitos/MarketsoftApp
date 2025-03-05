<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario Teléfono Proveedor</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${telefono.idTelprov == null}">
                Nuevo Teléfono Proveedor
            </c:when>
            <c:otherwise>
                Editar Teléfono Proveedor
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción según si es creación o edición -->
    <c:choose>
        <c:when test="${telefono.idTelprov == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/telefonosprov/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/telefonosprov/actualizar/${telefono.idTelprov}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${telefono.idTelprov != null}">
            <input type="hidden" name="idTelprov" value="${telefono.idTelprov}" />
        </c:if>

        <label>Teléfono:</label>
        <input type="number" name="telProv" value="${telefono.telProv}" required/><br><br>

        <label>ID Proveedor:</label>
        <input type="number" name="proveedor.idProveedor" value="${telefono.proveedor != null ? telefono.proveedor.idProveedor : ''}" required/><br><br>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/telefonosprov/listar">Volver a la Lista</a>
</body>
</html>
