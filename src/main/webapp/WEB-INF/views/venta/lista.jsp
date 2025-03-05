<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Ventas</title>
</head>
<body>
    <h2>Lista de Ventas</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Venta</th>
                <th>Precio Venta</th>
                <th>Descuento</th>
                <th>Fecha Venta</th>
                <th>IVA</th>
                <th>Total Final</th>
                <th>Detalle (ProductoVenta)</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="venta" items="${ventas}">
                <tr>
                    <td>${venta.idVenta}</td>
                    <td>${venta.precioVenta}</td>
                    <td>${venta.descuento}</td>
                    <td>${venta.fechaVenta}</td>
                    <td>${venta.iva}</td>
                    <td>${venta.totalFinal}</td>
                    <td>
                        <c:if test="${not empty venta.productoVenta}">
                            <c:forEach var="detalle" items="${venta.productoVenta}">
                                Precio Unitario: ${detalle.precioUnitario},
                                Cantidad: ${detalle.cantidad}<br/>
                                ID Producto: ${detalle.producto.idProducto}<br/><br/>
                            </c:forEach>
                        </c:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/venta/editar/${venta.idVenta}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/venta/eliminar/${venta.idVenta}" onclick="return confirm('¿Estás seguro de eliminar esta venta?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/venta/nuevo">Agregar Venta</a>
</body>
</html>
