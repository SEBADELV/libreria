package model.data.gestorLibreria;

import model.articulo;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;
public class gestorArticulo {
    public static void agregarArticulo(DSLContext query, articulo articulo){
        Table tablaArticulo = table(name("Articulo"));
        Field[] columnas = tablaArticulo.fields("nombre","tipoarticulo");
        query.insertInto(tablaArticulo, columnas[0], columnas[1])
                .values(articulo.getNombre(),articulo.getTipoArticulo())
                .execute();
    }
    public static void eliminarArticulo(DSLContext query, String Articulo){
        Table tablaArticulo= table(name("Articulo"));
        query.delete(DSL.table("Articulo")).where(DSL.field("Articulo").eq(Articulo)).execute();
    }
    private static List obtenerListaArticulo(Result resultados){
        List<articulo> articulos= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String nombre = (String) resultados.getValue(fila,"nombre");
            String tipoArticulo= (String) resultados.getValue(fila,"tipoArticulo");
            articulos.add(new articulo(nombre,tipoArticulo));
        }
        return articulos;
    }

    public static List buscararticulonombre(DSLContext query, String columnaTabla, String dato){
        Result resultados = query.select().from(DSL.table("Articulo")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaArticulo(resultados);
    }

}
