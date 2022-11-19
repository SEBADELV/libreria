<%--
  Created by IntelliJ IDEA.
  User: sebah
  Date: 17/11/2022
  Time: 03:45 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar Clientes</title>
</head>
<body>
<h1 class="encabezado"> Registro Cliente</h1>
<form action="registroCliente" method="post">
  <div class="centrado">
    <label> Nombre:</label>
    <input name="nombre" type="text" class="campoTexto">
    <label> Rut:</label>
    <input name="rut" type="text" class="campoTexto">
    <label> Edad:</label>
    <input name="correo" type="number" class="campoTexto">
    <br><br>
    <input type="submit" value="enviar" class="boton">
  </div>
</form>
</body>
</html>
