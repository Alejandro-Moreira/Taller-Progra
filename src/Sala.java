import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nombre;
    private int capacidad = 20;
    private List<Compra> compras = new ArrayList<>();

    public Sala(String nombre) {
        this.nombre = nombre;
    }

    public boolean agregarCompra(Compra compra) {
        if (capacidad >= compra.getCantidad()) {
            compras.add(compra);
            capacidad -= compra.getCantidad();
            return true;
        }
        return false;
    }

    public boolean editarCompra(String nombreCliente, String nuevaPelicula, int nuevaCantidad) {
        for (Compra compra : compras) {
            if (compra.getNombreCliente().equalsIgnoreCase(nombreCliente)) {
                // Liberar capacidad antigua
                capacidad += compra.getCantidad();
                if (capacidad >= nuevaCantidad) {
                    compra.setNombrePelicula(nuevaPelicula);
                    compra.setCantidad(nuevaCantidad);
                    capacidad -= nuevaCantidad;
                    return true;
                } else {
                    // Revertir liberación
                    capacidad -= compra.getCantidad();
                    return false;
                }
            }
        }
        return false;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public int getCapacidadDisponible() {
        return capacidad;
    }

    public String getNombre() {
        return nombre;
    }
}
