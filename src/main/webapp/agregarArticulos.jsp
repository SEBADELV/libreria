<%--
  Created by IntelliJ IDEA.
  User: sebah
  Date: 17/11/2022
  Time: 03:40 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ingresar Articulo</title>
</head>
<body>
<h1 class="encabezado"> Registro usuario</h1>
<form action="AgregarArticulo" method="post">
    <div class="centrado">
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto">
        <label> Rut:</label>
        <input name="tipo de articulo" type="text" class="campoTexto">
        <label> Edad:</label>
        <input type="submit" value="enviar" class="boton">
    </div>
</form>
</body>
</html>
