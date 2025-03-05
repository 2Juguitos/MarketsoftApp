<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario ProductoProveedor</title>
</head>
<body>
    <h1>Formulario ProductoProveedor</h1>
    <form action="${pageContext.request.contextPath}/productoproveedor/guardar" method="post">
        <!-- Campo oculto para ID en caso de edición -->
        <input type="hidden" name="id" value="${productoProveedor.id}">

        <label for="productoId">ID Producto:</label>
        <input type="number" name="productoId" id="productoId" value="${productoProveedor.productoId}" required>
        <br><br>

        <label for="proveedorId">ID Proveedor:</label>
        <input type="number" name="proveedorId" id="proveedorId" value="${productoProveedor.proveedorId}" required>
        <br><br>

        <label for="cantidad">Cantidad:</label>
        <input type="number" name="cantidad" id="cantidad" value="${productoProveedor.cantidad}" required>
        <br><br>

        <label for="precioUnitario">Precio Unitario:</label>
        <input type="number" step="0.01" name="precioUnitario" id="precioUnitario" value="${productoProveedor.precioUnitario}" required>
        <br><br>

        <button type="submit">Guardar</button>
        <a href="${pageContext.request.contextPath}/productoproveedor/lista">Cancelar</a>
    </form>
</body>
</html>