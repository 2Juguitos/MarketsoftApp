<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario Administrador</title>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${administrador.idAdmin == null}">
                Nuevo Administrador
            </c:when>
            <c:otherwise>
                Editar Administrador
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Definir la URL de acción en una variable -->
    <c:choose>
        <c:when test="${administrador.idAdmin == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/administradores/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/administradores/actualizar/${administrador.idAdmin}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">

        <c:if test="${administrador.idAdmin != null}">
            <input type="hidden" name="idAdmin" value="${administrador.idAdmin}" />
        </c:if>

        <label>Nombre:</label>
        <input type="text" name="nombreAdmin" value="${administrador.nombreAdmin}" required/><br>

        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/administradores/listar">Volver a la Lista</a>
</body>
</html>
