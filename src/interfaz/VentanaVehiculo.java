// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VentanaVehiculo extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaVehiculo(Sistema sistema) {

        initComponents();
        this.sistema = sistema;
        sistema.addObserver(this);
        listaVehiculos.setListData(sistema.getListaVehiculos().toArray());
        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());
        actualizarColor();
        actualizarDatos();
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());

    }

    public void actualizarDatos() {
        listaVehiculos.setListData(sistema.getListaVehiculos().toArray());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaVehiculos = new javax.swing.JList();
        btnVerMas = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAgregarVehiculo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vehiculos"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Vehiculos registrados");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(320, 40, 140, 18);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(310, 60, 160, 10);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Registro de vehiculos");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 40, 140, 18);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(50, 60, 210, 10);

        jLabel3.setText("Marca:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 150, 70, 16);

        jLabel4.setText("Matricula: ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 100, 70, 16);

        jLabel5.setText("Modelo:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 200, 70, 16);

        jLabel6.setText("Estado:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 250, 70, 16);

        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });
        getContentPane().add(txtEstado);
        txtEstado.setBounds(110, 240, 150, 30);

        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });
        getContentPane().add(txtMatricula);
        txtMatricula.setBounds(110, 90, 150, 30);
        getContentPane().add(txtMarca);
        txtMarca.setBounds(110, 140, 150, 30);

        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });
        getContentPane().add(txtModelo);
        txtModelo.setBounds(110, 190, 150, 30);

        listaVehiculos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matricula  - Marca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        listaVehiculos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaVehiculos);
        listaVehiculos.getAccessibleContext().setAccessibleName("Matricula - Marca ");

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(310, 80, 170, 210);

        btnVerMas.setText("Ver detalles");
        btnVerMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMasVehiculo(evt);
            }
        });
        getContentPane().add(btnVerMas);
        btnVerMas.setBounds(330, 310, 130, 30);

        btnLimpiar.setText("Vaciar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarlimpiarCasillasTxtVehiculo(evt);
            }
        });
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(40, 310, 70, 30);

        btnAgregarVehiculo.setText("Agregar Vehiculo");
        btnAgregarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVehiculo(evt);
            }
        });
        getContentPane().add(btnAgregarVehiculo);
        btnAgregarVehiculo.setBounds(120, 310, 140, 30);

        setSize(new java.awt.Dimension(527, 413));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoActionPerformed

    private void btnVerMasVehiculo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMasVehiculo

        Vehiculo seleccionado = (Vehiculo) listaVehiculos.getSelectedValue();
        if (seleccionado != null) {
            VentanaConDetalles vdc = new VentanaConDetalles(sistema);
            vdc.mostrarVehiculo(seleccionado);
            vdc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vehiculo de la lista.", "Vehiculo no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnVerMasVehiculo

    private void btnLimpiarlimpiarCasillasTxtVehiculo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarlimpiarCasillasTxtVehiculo
        txtMarca.setText("");
        txtModelo.setText("");
        txtEstado.setText("");
        txtMatricula.setText("");

    }//GEN-LAST:event_btnLimpiarlimpiarCasillasTxtVehiculo

    private void btnAgregarVehiculo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVehiculo

        boolean estaCompleto = true;
        if (txtMarca.getText().trim().isEmpty()
             || txtMatricula.getText().trim().isEmpty()
             || txtEstado.getText().trim().isEmpty()
             || txtModelo.getText().trim().isEmpty()) {
            estaCompleto = false;
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        } else {
            if (!(sistema.validarExisteMatricula(txtMatricula.getText()))) {
                sistema.agregarVehiculo(txtMatricula.getText(), txtMarca.getText(), txtModelo.getText(), txtEstado.getText());
                txtMatricula.setText("");
                txtMarca.setText("");
                txtModelo.setText("");
                txtEstado.setText("");
                actualizarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "La matricula ya esta registrada en el sistema.", "Matricula duplicada", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarVehiculo

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarVehiculo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVerMas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList listaVehiculos;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
    }
}
