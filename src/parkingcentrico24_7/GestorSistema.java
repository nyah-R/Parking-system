// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package parkingcentrico24_7;

import dominio.Sistema;
import interfaz.VentanaPrincipal;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import static parkingcentrico24_7.Main.iniciarVentanas;

public class GestorSistema {

    private static final String ARCHIVO = "DATOS.ser";

    // Carga el sistema desde archivo o crea uno nuevo si falla
    public static void cargar(VentanaPrincipal vp) {
        Sistema sistemaNuevo = null;
        try (ObjectInputStream in = new ObjectInputStream(
             Files.newInputStream(Paths.get(ARCHIVO)))) {
            sistemaNuevo = (Sistema) in.readObject();
            vp.dispose();
        } catch (Exception e) {
            sistemaNuevo = new Sistema();
        }
        iniciarVentanas(sistemaNuevo);
    }

    // Guarda el sistema en disco
    public static void guardarSistema(Sistema sistema) {
        try (ObjectOutputStream out = new ObjectOutputStream(
             Files.newOutputStream(Paths.get(ARCHIVO)))) {
            out.writeObject(sistema);
        } catch (Exception e) {
            System.out.println("Error al guardar el sistema: " + e.getMessage());
        }
    }
}
