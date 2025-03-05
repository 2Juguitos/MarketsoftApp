<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Productos</title>
</head>
<body>
    <h2>Lista de Productos</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Marca</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="producto" items="${productos}">
                <tr>
                    <td>${producto.idProducto}</td>
                    <td>${producto.marcaProd}</td>
                    <td>${producto.nombreProd}</td>
                    <td>
                        <c:if test="${producto.categoria != null}">
                            ${producto.categoria.nombreCategoria}
                        </c:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productos/editar/${producto.idProducto}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/productos/eliminar/${producto.idProducto}" onclick="return confirm('¿Está seguro de eliminar este producto?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/productos/nuevo">Agregar Producto</a>
</body>
</html>
