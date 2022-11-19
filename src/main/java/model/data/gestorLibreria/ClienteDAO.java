package model.data.gestorLibreria;

import model.cliente;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class ClienteDAO {
    public static void agregarCliente(DSLContext query, cliente cliente){
        Table tablaCliente= table(name("Cliente"));
        Field[] columnas = tablaCliente.fields("rut","nombre","correo");
        query.insertInto(tablaCliente, columnas[0], columnas[1],columnas[2])
                .values(cliente.getRut(),cliente.getNombre(),cliente.getCorreo())
                .execute();
    }
    public static void modificarCliente(DSLContext query, String rut, String columnaTabla, Object dato){
        query.update(DSL.table("Cliente")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("rut").eq(rut)).execute();
    }
    public static List obtenerCliente(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Cliente")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaClientes(resultados);
    }
    public static List obtenerClientes(DSLContext query){
        Result resultados = query.select().from(DSL.table("Cliente")).fetch();
        return obtenerListaClientes(resultados);
    }
    public static void eliminarCliente(DSLContext query, String rut){
        Table tablaCliente= table(name("Cliente"));
        query.delete(DSL.table("Cliente")).where(DSL.field("rut").eq(rut)).execute();
    }
    private static List obtenerListaClientes(Result resultados){
        List<cliente> clientes= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String rut = (String) resultados.getValue(fila,"rut");
            String nombre = (String) resultados.getValue(fila,"nombre");
            String correo = (String) resultados.getValue(fila,"correo");
            clientes.add(new cliente(nombre,correo,rut));
        }
        return clientes;
    }


}
