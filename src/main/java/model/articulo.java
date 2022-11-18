package model;

public class articulo {
   private String nombre;
    private String tipoArticulo;

    public articulo(String nombre, String tipoArticulo) {
        this.nombre = nombre;
        this.tipoArticulo = tipoArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }
}
