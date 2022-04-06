
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.productos.Productos"%>
<%
    ArrayList<Productos> lista = (ArrayList<Productos>) session.getAttribute("listaper");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <table border="0">
            <thead>
                <tr>
                    <th> PRIMER PARCIAL TEM-742</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>NOMBRE: ALBERTO ALARCON MAMANI</td>
                </tr>
                <tr>
                    <td>CARNET: 8296768</td>
                </tr>
                <tr>
                    <td>RU: 200029162</td>
                </tr>
            </tbody>
        </table>
    </center>
        
    <centro><h1>LISTADO DE PRODUCTOS</h1></centro>
        <a href="MainController?op=nuevo">Nuevo Producto</a>
        <table border = "1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
                
                <%
                if(lista!=null){
                    for(Productos item : lista){
                %>   
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getDescripcion()%></td>
                <td><%=item.getCantidad()%></td>
                <td><%=item.getPrecio()%></td>
                <td><%=item.getCategoria()%></td>
                <td><a href="MainController?op=editar&id="<%=item.getId()%>">Editar</a></td>
                <td><a href ="MainController?op=eliminar&id=<%=item.getId()%>"
                       onclick='return confirm("esta seguro de eliminar el registro?");'>Eliminar</a></td>
            </tr>
            <%
                    }
                }
            %>                
        </table>
    </body>
</html>
