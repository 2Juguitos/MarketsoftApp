<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Proveedores</title>
</head>
<body>
    <h2>Lista de Proveedores</h2>

    <!-- Botón para redirigir al listado de teléfonos -->
    <button onclick="window.location.href='${pageContext.request.contextPath}/telefonosprov/listar'">
        Ver Teléfonos de Proveedores
    </button>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Correo</th>
                <th>Empresa</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Administrador</th>
                <th>Teléfonos</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="proveedor" items="${proveedores}">
                <tr>
                    <td>${proveedor.idProveedor}</td>
                    <td>${proveedor.correoProveedor}</td>
                    <td>${proveedor.empresaProv}</td>
                    <td>${proveedor.nombreProv}</td>
                    <td>${proveedor.apellidoProv}</td>
                    <td>${proveedor.administrador.idAdmin}</td>
                    <td>
                        <!-- Listar los teléfonos asociados al proveedor -->
                        <ul>
                            <c:forEach var="telefono" items="${proveedor.telefonos}">
                                <li>${telefono.telProv}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>
                        <!-- Link para editar -->
                        <a href="${pageContext.request.contextPath}/proveedores/editar/${proveedor.idProveedor}">Editar</a> |
                        <!-- Link para eliminar -->
                        <a href="${pageContext.request.contextPath}/proveedores/eliminar/${proveedor.idProveedor}"
                           onclick="return confirm('¿Estás seguro de eliminar este proveedor?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/proveedores/nuevo">Nuevo Proveedor</a>
</body>
</html>