<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario de Proveedor</title>
</head>
<body>
    <!-- Título dinámico dependiendo si es un nuevo proveedor o edición -->
    <h2>
        <c:choose>
            <c:when test="${empty proveedor.idProveedor}">
                Nuevo Proveedor
            </c:when>
            <c:otherwise>
                Editar Proveedor
            </c:otherwise>
        </c:choose>
    </h2>

    <!-- Lógica para determinar la URL de acción del formulario -->
    <c:choose>
        <c:when test="${empty proveedor.idProveedor}">
            <!-- Acción para un nuevo proveedor -->
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/proveedores/guardar" />
        </c:when>
        <c:otherwise>
            <!-- Acción para editar un proveedor existente -->
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/proveedores/actualizar/${proveedor.idProveedor}" />
        </c:otherwise>
    </c:choose>

    <!-- Inicio del formulario -->
    <form action="${actionUrl}" method="post">
        <!-- Campo del correo -->
        <label for="correoProveedor">Correo del Proveedor:</label>
        <input type="email" id="correoProveedor" name="correoProveedor"
               value="${proveedor.correoProveedor}" required /><br><br>

        <!-- Campo de la empresa -->
        <label for="empresaProv">Empresa:</label>
        <input type="text" id="empresaProv" name="empresaProv"
               value="${proveedor.empresaProv}" required /><br><br>

        <!-- Campo del nombre -->
        <label for="nombreProv">Nombre:</label>
        <input type="text" id="nombreProv" name="nombreProv"
               value="${proveedor.nombreProv}" required /><br><br>

        <!-- Campo del apellido -->
        <label for="apellidoProv">Apellido:</label>
        <input type="text" id="apellidoProv" name="apellidoProv"
               value="${proveedor.apellidoProv}" required /><br><br>

        <!-- Administrador -->
        <label for="administrador">Administrador (ID):</label>
        <input type="text" id="administrador" name="administrador.idAdmin"
               value="${proveedor.administrador != null ? proveedor.administrador.idAdmin : ''}" required /><br><br>

        <!-- Botón para guardar -->
        <button type="submit">Guardar</button>
    </form>

    <!-- Enlace para regresar a la lista -->
    <br>
    <a href="${pageContext.request.contextPath}/proveedores/listar">Volver a la Lista</a>
</body>
</html>