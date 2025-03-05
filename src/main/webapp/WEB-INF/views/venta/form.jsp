<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Formulario de Venta</title>
    <script>
        let productoIndex = 0; // Contador para los productos dinámicos

        function agregarProducto() {
            productoIndex++; // Incrementa el índice

            let container = document.getElementById("productosContainer");
            let nuevoProducto = document.createElement("div");
            nuevoProducto.innerHTML = `
                <hr>
                <label>Precio Unitario:</label>
                <input type="number" step="0.01" name="productoVenta[\${productoIndex}].precioUnitario" /><br><br>

                <label>Cantidad:</label>
                <input type="number" name="productoVenta[\${productoIndex}].cantidad" required/><br><br>

                <label>ID Producto:</label>
                <input type="number" name="productoVenta[\${productoIndex}].producto.idProducto" required/><br><br>

                <button type="button" onclick="eliminarProducto(this)">Eliminar Producto</button>
                <hr>
            `;
            container.appendChild(nuevoProducto);
        }

        function eliminarProducto(button) {
            let container = document.getElementById("productosContainer");
            container.removeChild(button.parentElement);
        }
    </script>
</head>
<body>
    <h2>
        <c:choose>
            <c:when test="${venta.idVenta == null}">
                Nueva Venta
            </c:when>
            <c:otherwise>
                Editar Venta
            </c:otherwise>
        </c:choose>
    </h2>

    <c:choose>
        <c:when test="${venta.idVenta == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/venta/guardar" />
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/venta/actualizar/${venta.idVenta}" />
        </c:otherwise>
    </c:choose>

    <form action="${actionUrl}" method="post">
        <c:if test="${venta.idVenta != null}">
            <input type="hidden" name="idVenta" value="${venta.idVenta}" />
        </c:if>

        <label>Descuento:</label>
        <input type="number" step="0.01" name="descuento" value="${venta.descuento}" required/><br><br>

        <label>Fecha Venta:</label>
        <input type="date" name="fechaVenta" value="${venta.fechaVenta}" required/><br><br>

        <label>ID Cliente (opcional):</label>
        <input type="number" name="cliente.idCliente" value="${venta.cliente != null ? venta.cliente.idCliente : ''}" /><br><br>

        <c:choose>
            <c:when test="${venta.administrador != null}">
                <input type="hidden" name="administrador.idAdmin" value="${venta.administrador.idAdmin}" />
            </c:when>
            <c:otherwise>
                <input type="hidden" name="administrador.idAdmin" value="1" />
            </c:otherwise>
        </c:choose>

        <h3>Detalle de Venta (ProductoVenta)</h3>

        <div id="productosContainer">
            <c:if test="${not empty venta.productoVenta}">
                <c:forEach var="detalle" items="${venta.productoVenta}" varStatus="status">
                    <div>
                        <label>Precio Unitario (Opcional)  :</label>
                        <input type="number" step="0.01" name="productoVenta[${status.index}].precioUnitario" value="${detalle.precioUnitario}" /><br><br>

                        <label>Cantidad:</label>
                        <input type="number" name="productoVenta[${status.index}].cantidad" value="${detalle.cantidad}" required/><br><br>

                        <label>ID Producto:</label>
                        <input type="number" name="productoVenta[${status.index}].producto.idProducto" value="${detalle.producto.idProducto}" required/><br><br>

                        <button type="button" onclick="eliminarProducto(this)">Eliminar Producto</button>
                        <hr>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <button type="button" onclick="agregarProducto()">Agregar Producto</button>
        <br><br>
        <button type="submit">Guardar</button>
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/venta/listar">Volver a la Lista</a>
</body>
</html>
