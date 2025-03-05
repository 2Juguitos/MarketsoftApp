<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Teléfonos Proveedor</title>
</head>
<body>
    <h2>Lista de Teléfonos Proveedor</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Teléfono</th>
                <th>Teléfono</th>
                <th>ID Proveedor</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tel" items="${telefonos}">
                <tr>
                    <td>${tel.idTelprov}</td>
                    <td>${tel.telProv}</td>
                    <td>
                        <c:if test="${tel.proveedor != null}">
                            ${tel.proveedor.idProveedor}
                        </c:if>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/telefonosprov/editar/${tel.idTelprov}">Editar</a> |
                        <a href="${pageContext.request.contextPath}/telefonosprov/eliminar/${tel.idTelprov}" onclick="return confirm('¿Está seguro de eliminar este registro?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>

    <!-- Enlace para agregar un nuevo teléfono -->
    <a href="${pageContext.request.contextPath}/telefonosprov/nuevo">Agregar Teléfono Proveedor</a>

    <br><br>

    <!-- Botón para redirigir a la lista de proveedores -->
    <button onclick="window.location.href='${pageContext.request.contextPath}/proveedores/listar'">
        Volver a Lista de Proveedores
    </button>
</body>
</html>