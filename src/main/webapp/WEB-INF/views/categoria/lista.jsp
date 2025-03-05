<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Categorías</title>
</head>
<body>
    <h2>Lista de Categorías</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Fecha Registro</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="categoria" items="${categorias}">
                <tr>
                    <td>${categoria.idCategoria}</td>
                    <td>${categoria.nombreCategoria}</td>
                    <td>${categoria.fechaRegistroCategoria}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/categorias/editar/${categoria.idCategoria}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/categorias/eliminar/${categoria.idCategoria}" onclick="return confirm('¿Estás seguro de eliminar esta categoría?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/categorias/nuevo">Agregar Categoría</a>
</body>
</html>
