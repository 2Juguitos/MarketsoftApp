<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Inventarios</title>
</head>
<body>
    <h2>Lista de Inventarios</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Precio</th>
                <th>Stock</th>
                <th>Fecha Registro</th>
                <th>Producto Disponible</th>
                <th>ID Producto</th>
                <th>ID Administrador</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="inventario" items="${inventarios}">
                <tr>
                    <td>${inventario.id}</td>
                    <td>${inventario.precio}</td>
                    <td>${inventario.stock}</td>
                    <td>${inventario.fechaRegistro}</td>
                    <td>${inventario.productoDisponible}</td>
                    <td>${inventario.producto.idProducto}</td>
                    <td>${inventario.administrador.idAdmin}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/inventario/editar/${inventario.id}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/inventario/eliminar/${inventario.id}" onclick="return confirm('¿Estás seguro de eliminar este inventario?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/inventario/nuevo">Agregar Inventario</a>
</body>
</html>
