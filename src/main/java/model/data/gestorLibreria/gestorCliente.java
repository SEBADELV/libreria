package model.data.gestorLibreria;
import model.cliente;
import org.jooq.*;
import org.jooq.impl.DSL;
import model.data.*;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;
public class gestorCliente {
    public static void agregarCliente(DSLContext query, cliente cliente){
        Table tablaCliente= table(name("Cliente"));
        Field[] columnas = tablaCliente.fields("nombre","rut","correo");
        query.insertInto(tablaCliente, columnas[0], columnas[1],columnas[2])
                .values(cliente.getRut(),cliente.getNombre(),cliente.getCorreo())
                .execute();
    }
    public static void modificarUsuario(DSLContext query, String rut, String columnaTabla, Object dato){
        query.update(DSL.table("Cliente")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("rut").eq(rut)).execute();
    }
    public static List obtenerUsuario(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Usuario")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaEstudiantes(resultados);
    }
    public static List obtenerUsuarios(DSLContext query){
        Result resultados = query.select().from(DSL.table("Usuario")).fetch();
        return obtenerListaEstudiantes(resultados);
    }
    public static void eliminarEstudiante(DSLContext query, String rut){
        Table tablaEstudiante= table(name("Usuario"));
        query.delete(DSL.table("Usuario")).where(DSL.field("rut").eq(rut)).execute();
    }
    private static List obtenerListaEstudiantes(Result resultados){
        List<Usuario> usuarios= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String rut = (String) resultados.getValue(fila,"rut");
            String nombre = (String) resultados.getValue(fila,"nombre");
            int edad = (Integer) resultados.getValue(fila,"edad");
            usuarios.add(new Usuario(nombre,edad,rut));
        }
        return usuarios;
    }
}
