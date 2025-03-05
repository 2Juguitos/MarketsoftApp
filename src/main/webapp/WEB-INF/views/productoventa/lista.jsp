<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de ProductoVenta</title>
</head>
<body>
    <h2>Lista de ProductoVenta</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Precio Unitario</th>
                <th>Cantidad</th>
                <th>ID Producto</th>
                <th>ID Venta</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pv" items="${prodVentas}">
                <tr>
                    <td>${pv.id}</td>
                    <td>${pv.precioUnitario}</td>
                    <td>${pv.cantidad}</td>
                    <!-- Acceder a la propiedad 'producto' en minúscula -->
                    <td>${pv.producto.idProducto}</td>
                    <!-- Acceder a la propiedad 'venta' en minúscula -->
                    <td>${pv.venta.idVenta}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productoventa/editar/${pv.id}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/productoventa/eliminar/${pv.id}" onclick="return confirm('¿Está seguro de eliminar este registro?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/productoventa/nuevo">Agregar ProductoVenta</a>
</body>
</html>
