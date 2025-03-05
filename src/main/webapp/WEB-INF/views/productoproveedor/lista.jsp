<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de ProductoProveedor</title>
</head>
<body>
    <h1>Lista de ProductoProveedor</h1>
    <div>
        <a href="${pageContext.request.contextPath}/productoproveedor/form">Nuevo ProductoProveedor</a>
    </div>
    <br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>ID Producto</th>
                <th>ID Proveedor</th>
                <th>Cantidad</th>
                <th>Precio Unitario</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="productoProveedor" items="${productoProveedores}">
                <tr>
                    <td>${productoProveedor.id}</td>
                    <td>${productoProveedor.productoId}</td>
                    <td>${productoProveedor.proveedorId}</td>
                    <td>${productoProveedor.cantidad}</td>
                    <td>${productoProveedor.precioUnitario}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productoproveedor/eliminar/${productoProveedor.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>