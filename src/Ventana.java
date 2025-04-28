import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Ventana {
    private JPanel principal;
    private JTextField textField1; // Nombre
    private JTextField textField2; // Cantidad de entradas
    private JComboBox comboBox1;   // Películas
    private JButton comprarButton; // Realiza la compra de entradas
    private JTextArea textArea1; //Muesta las compras
    private JButton editarButton;

    private Map<String, Sala> salas = new HashMap<>();

    public Ventana() {
        // Crear salas
        salas.put("XMEN", new Sala("XMEN"));
        salas.put("MARIO", new Sala("MARIO"));
        salas.put("BATMAN", new Sala("BATMAN"));

        // Llenar ComboBox
        comboBox1.addItem("XMEN");
        comboBox1.addItem("MARIO");
        comboBox1.addItem("BATMAN");

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprar();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
    }

    private void comprar() {
        String nombre = textField1.getText().trim();
        String cantidadStr = textField2.getText().trim();
        String pelicula = (String) comboBox1.getSelectedItem();

        if (nombre.isEmpty() || cantidadStr.isEmpty() || pelicula == null) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido en entradas.");
            return;
        }

        if (cantidad < 1 || cantidad > 3) {
            JOptionPane.showMessageDialog(null, "Puede comprar entre 1 y 3 entradas.");
            return;
        }

        Sala sala = salas.get(pelicula);
        if (sala.agregarCompra(new Compra(nombre, pelicula, cantidad))) {
            actualizarTextArea();
        } else {
            JOptionPane.showMessageDialog(null, "No hay suficientes asientos disponibles para esta película.");
        }
    }

    private void editar() {
        String nombre = textField1.getText().trim();
        String cantidadStr = textField2.getText().trim();
        String nuevaPelicula = (String) comboBox1.getSelectedItem();

        if (nombre.isEmpty() || cantidadStr.isEmpty() || nuevaPelicula == null) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }

        int nuevaCantidad;
        try {
            nuevaCantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido en entradas.");
            return;
        }

        if (nuevaCantidad < 1 || nuevaCantidad > 3) {
            JOptionPane.showMessageDialog(null, "Puede comprar entre 1 y 3 entradas.");
            return;
        }

        boolean encontrado = false;
        for (Sala sala : salas.values()) {
            if (sala.editarCompra(nombre, nuevaPelicula, nuevaCantidad)) {
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            actualizarTextArea();
        } else {
            JOptionPane.showMessageDialog(null, "Compra no encontrada o capacidad insuficiente.");
        }
    }

    private void actualizarTextArea() {
        textArea1.setText(""); // Limpiar
        for (Sala sala : salas.values()) {
            for (Compra compra : sala.getCompras()) {
                textArea1.append("Película: " + compra.getNombrePelicula() + ", Cliente: " +
                        compra.getNombreCliente() + ", Entradas: " + compra.getCantidad() + "\n");
            }
        }
    }

    public JPanel getPrincipal() {
        return principal;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
