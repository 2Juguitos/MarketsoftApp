<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Registro de Crédito</title>
</head>
<body>
    <h1>${registroCredito.id.creditoIdCredito != null ? "Editar Registro de Crédito" : "Nuevo Registro de Crédito"}</h1>
    <form action="${pageContext.request.contextPath}/regcredito/guardar" method="post">

        <!-- Campo para el ID del Crédito -->
        <label for="creditoIdCredito">ID Crédito:</label>
        <input type="number" name="id.creditoIdCredito" id="creditoIdCredito"
               value="${registroCredito.id.creditoIdCredito}" required>
        <br><br>

        <!-- Campo para el ID del Administrador -->
        <label for="administradorIdAdmin">ID Administrador:</label>
        <input type="number" name="id.administradorIdAdmin" id="administradorIdAdmin"
               value="${registroCredito.id.administradorIdAdmin}" required>
        <br><br>

        <!-- Botón para enviar el formulario -->
        <button type="submit">${registroCredito.id.creditoIdCredito != null ? "Actualizar" : "Guardar"}</button>
        <a href="${pageContext.request.contextPath}/regcredito/lista">Cancelar</a>
    </form>

    <!-- Visualización de errores -->
    <c:if test="${not empty error}">
        <br><br>
        <div style="color: red;">${error}</div>
    </c:if>
</body>
</html>