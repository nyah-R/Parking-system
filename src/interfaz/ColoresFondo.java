// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.jdatepicker.impl.*;

public class ColoresFondo {

    private static boolean temaOscuro;

    public static void aplicarTema(Container contenedor, boolean oscuro) {
        temaOscuro = oscuro;
        Color fondo;
        if (oscuro) {
            fondo = Color.BLACK;
        } else {
            fondo = Color.WHITE;
        }
        Color texto;
        if (oscuro) {
            texto = Color.white;
        } else {
            texto = Color.black;
        }
        aplicarTemaRecursivo(contenedor, fondo, texto);
    }

    public static void aplicarTemaDatePicker(JDatePickerImpl datePicker, boolean oscuro) {
        aplicarTema(datePicker, oscuro);
        Component editor = datePicker.getJFormattedTextField();
        if (editor instanceof JComponent) {
            aplicarTema((JComponent) editor, oscuro);
        }
    }

    private static void aplicarTemaRecursivo(Component comp, Color fondo, Color texto) {
        if (comp instanceof JPanel || comp instanceof JScrollPane || comp instanceof JViewport) {
            comp.setBackground(fondo);
        }

        if (comp instanceof JButton || comp instanceof JToggleButton) {
            AbstractButton boton = (AbstractButton) comp;
            boton.setForeground(new Color(0, 0, 0));

            if (!temaOscuro) {
                boton.setBackground(new Color(255, 184, 77)); // Naranjita claro
            } else {
                boton.setBackground(new Color(172, 226, 152));
            }

            boton.setContentAreaFilled(false);
            boton.setOpaque(true);
            boton.setBorderPainted(false);
            boton.setFocusPainted(false);
        }

        if (comp instanceof JLabel || comp instanceof JCheckBox || comp instanceof JMenu || comp instanceof JMenuItem) {
            comp.setForeground(texto);
        }

        if (comp instanceof JTextField || comp instanceof JTextArea
             || comp instanceof JComboBox
             || comp instanceof JList || comp instanceof JTable) {
            comp.setForeground(texto);
            comp.setBackground(fondo);
        }

        if (comp instanceof JComponent) {
            Border border = ((JComponent) comp).getBorder();
            if (border instanceof TitledBorder) {
                ((TitledBorder) border).setTitleColor(texto);
            }
        }

        if (comp instanceof Container) {
            for (Component hijo : ((Container) comp).getComponents()) {
                aplicarTemaRecursivo(hijo, fondo, texto);
            }
        }
    }
}
