package Exam;

import javax.swing.JOptionPane;

public class TestTanque {

    public static void main(String[] args) {
        Tanque t;
        float capacidad = 0;
        float combustibleActual = 0;
        int opcion;
        boolean continuar = true;

        JOptionPane.showMessageDialog(null, "================ Test Tanque ===============");

        // Entrada y validación de Capacidad Máxima
        capacidad = leerFloatPositivo("Ingresa la capacidad máxima del tanque:");
        if (capacidad <= 0) return; // Si cancela o cierra la ventana, termina el programa

        // Entrada y validación de Combustible Actual
        do {
            combustibleActual = leerFloatPositivo("Ingresa el combustible actual del tanque:");
            if (combustibleActual < 0) return; // Cancela o cierra
            
            if (combustibleActual > capacidad) {
                JOptionPane.showMessageDialog(null, 
                    "El combustible actual no puede exceder la capacidad máxima (" + capacidad + " L).", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (combustibleActual > capacidad);

        // Instancia del Tanque
        t = new Tanque(capacidad, combustibleActual);

        // Menú principal
        do {
            String inputOp = JOptionPane.showInputDialog(
                "================ MENÚ ================\n"
                + "1. Llenar Tanque\n"
                + "2. Vaciar Tanque\n"
                + "3. Agregar combustible\n"
                + "4. Disminuir combustible\n"
                + "5. Consultar combustible\n"
                + "6. Salir"
            );

            // Si se presiona Cancelar o se cierra la ventana
            if (inputOp == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(inputOp);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcion) {
                case 1:
                    t.llenarTanque();
                    JOptionPane.showMessageDialog(null, "Tanque lleno exitosamente.");
                    break;

                case 2:
                    t.vaciarTanque();
                    JOptionPane.showMessageDialog(null, "Tanque vaciado exitosamente.");
                    break;

                case 3:
                    float aAgregar = leerFloatPositivo("Ingresa la cantidad de combustible a agregar:");
                    if (aAgregar > 0) {
                        t.llenarTanque(aAgregar);
                    }
                    break;

                case 4:
                    float aDisminuir = leerFloatPositivo("Ingresa la cantidad de combustible a disminuir:");
                    if (aDisminuir > 0) {
                        t.vaciarTanque(aDisminuir);
                    }
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, 
                        "El tanque tiene: " + t.consultarCombustible() + " litros de combustible (" 
                        + String.format("%.2f", t.consultarPorcentaje()) + "% de capacidad)", 
                        "Estado del Tanque", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 6:
                    continuar = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intenta nuevamente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } while (continuar);

        JOptionPane.showMessageDialog(null, "¡Programa finalizado!");
    }

    /**
     * Método auxiliar para leer números flotantes de forma segura mediante JOptionPane.
     */
    private static float leerFloatPositivo(String mensaje) {
        float valor = -1;
        while (true) {
            String input = JOptionPane.showInputDialog(mensaje);
            
            // Si el usuario presiona Cancelar o cierra la ventana
            if (input == null) {
                return -1;
            }

            try {
                valor = Float.parseFloat(input);
                if (valor < 0) {
                    JOptionPane.showMessageDialog(null, "El valor no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Ingresa un número decimal o entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
