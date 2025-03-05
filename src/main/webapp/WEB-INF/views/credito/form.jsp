<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${credito.idCredito != null ? "Editar Crédito" : "Nuevo Crédito"}</title>
</head>
<body>
    <h1>${credito.idCredito != null ? "Editar Crédito" : "Nuevo Crédito"}</h1>
    <form action="${pageContext.request.contextPath}/credito/guardar" method="post">

        <!-- ID del Crédito (oculto para edición) -->
        <c:if test="${credito.idCredito != null}">
            <input type="hidden" name="idCredito" value="${credito.idCredito}">
        </c:if>

        <!-- Campo de Cupo de Crédito -->
        <label for="cupoCredito">Cupo de Crédito:</label>
        <input type="number" step="0.01" name="cupoCredito" id="cupoCredito"
               value="${credito.cupoCredito}" required>
        <br><br>

        <!-- Campo de Cliente -->
        <label for="cliente">Cliente:</label>
        <select name="cliente.idCliente" id="cliente" required>
            <option value="" disabled selected>Seleccione un cliente</option>
            <c:forEach var="cliente" items="${clientes}">
                <option value="${cliente.idCliente}" ${credito.cliente != null && credito.cliente.idCliente == cliente.idCliente ? "selected" : ""}>
                    ${cliente.nombreCliente}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <!-- ID Administrador -->
        <label for="idAdministrador">ID Administrador:</label>
        <input type="number" name="idAdministrador" id="idAdministrador"
               value="${credito.idAdministrador}" required>
        <br><br>

        <!-- Botón para guardar -->
        <button type="submit">${credito.idCredito != null ? "Actualizar" : "Guardar"}</button>
        <a href="${pageContext.request.contextPath}/credito/lista">Cancelar</a>
    </form>

    <!-- Mensaje de error -->
    <c:if test="${not empty error}">
        <br>
        <div style="color: red;">${error}</div>
    </c:if>
</body>
</html>