// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.Sistema;
import java.util.Observable;
import java.util.Observer;

public class VentanaInfoAutores extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaInfoAutores(Sistema sistema) {
        initComponents();
        this.sistema = sistema;
        sistema.addObserver(this);

        actualizarColor();

    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        imgNyah = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información de Autores");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("INFORMACIÓN DE AUTORES");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 30, 230, 31);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(40, 60, 430, 10);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Nyah Rüting");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(310, 80, 137, 20);

        jLabel3.setText("Nro. Estudiante: 270931");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(290, 100, 137, 20);

        jLabel5.setText("Programación 2 - N2A - Univerisidad ORT");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(150, 360, 260, 16);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel4.setText("Universidad ORT Uruguay");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(220, 883, 170, 14);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setText("Facundo Esquivel");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 80, 110, 20);

        jLabel7.setText("Nro. Estudiante: 306504");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(70, 100, 160, 20);

        imgNyah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/nyahP2.jpg"))); // NOI18N
        getContentPane().add(imgNyah);
        imgNyah.setBounds(270, 130, 190, 200);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/facundoP2.jpg"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(40, 130, 190, 200);

        setSize(new java.awt.Dimension(509, 439));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgNyah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
    }

}
