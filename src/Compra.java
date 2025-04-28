public class Compra {
    private String nombreCliente;
    private String nombrePelicula;
    private int cantidad;

    public Compra(String nombreCliente, String nombrePelicula, int cantidad) {
        this.nombreCliente = nombreCliente;
        this.nombrePelicula = nombrePelicula;
        this.cantidad = cantidad;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
