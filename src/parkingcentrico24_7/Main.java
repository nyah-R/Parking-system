// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package parkingcentrico24_7;

import dominio.*;
import interfaz.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();
        iniciarVentanas(sistema);
    }

    public static void iniciarVentanas(Sistema unSistema) {
        VentanaPrincipal vp = new VentanaPrincipal(unSistema);
        vp.setVisible(true);
    }

}
