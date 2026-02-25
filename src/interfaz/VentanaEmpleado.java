// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VentanaEmpleado extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaEmpleado(Sistema sistema) {
        sistema.addObserver(this);
        initComponents();
        this.sistema = sistema;
        actualizarColor();
        actualizarDatos();

        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
    }

    public void actualizarDatos() {
        lstEmpleados.setListData(sistema.getListaEmpleados().toArray());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstEmpleados = new javax.swing.JList();
        subtituloLista = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        subitutloRegistro = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtnroEmpleado = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnVerDetalle = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnVaciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empleados");
        getContentPane().setLayout(null);

        lstEmpleados.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre - Cédula", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        lstEmpleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstEmpleados);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(270, 80, 210, 190);

        subtituloLista.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        subtituloLista.setText("Empleados registrados");
        getContentPane().add(subtituloLista);
        subtituloLista.setBounds(300, 40, 145, 18);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(27, 61, 173, 10);

        subitutloRegistro.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        subitutloRegistro.setText("Registro de empleado");
        getContentPane().add(subitutloRegistro);
        subitutloRegistro.setBounds(38, 37, 145, 18);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(300, 60, 145, 10);

        jLabel1.setText("Nombre:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(27, 92, 64, 16);

        jLabel2.setText("Cédula:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(27, 132, 64, 16);

        jLabel3.setText("Nro Empleado:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(27, 172, 90, 16);

        jLabel4.setText("Dirección:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(27, 212, 62, 16);

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre);
        txtNombre.setBounds(90, 90, 120, 22);
        getContentPane().add(txtCedula);
        txtCedula.setBounds(90, 130, 120, 22);
        getContentPane().add(txtnroEmpleado);
        txtnroEmpleado.setBounds(116, 169, 90, 22);
        getContentPane().add(txtDireccion);
        txtDireccion.setBounds(95, 209, 111, 22);

        btnVerDetalle.setText("Ver detalles");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });
        getContentPane().add(btnVerDetalle);
        btnVerDetalle.setBounds(290, 290, 180, 23);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpleadoNuevo(evt);
            }
        });
        getContentPane().add(btnAgregar);
        btnAgregar.setBounds(115, 290, 90, 23);

        btnVaciar.setText("Vaciar");
        btnVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarCampos(evt);
            }
        });
        getContentPane().add(btnVaciar);
        btnVaciar.setBounds(30, 290, 72, 23);

        setSize(new java.awt.Dimension(535, 381));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarEmpleadoNuevo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpleadoNuevo

        boolean estaCompleto = true;
        if (txtNombre.getText().trim().isEmpty()
             || txtCedula.getText().trim().isEmpty()
             || txtDireccion.getText().trim().isEmpty()
             || txtnroEmpleado.getText().trim().isEmpty()) {
            estaCompleto = false;
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        }

        if (estaCompleto) {

            try {
                Integer.parseInt(txtnroEmpleado.getText().trim());
            } catch (NumberFormatException e) {
                estaCompleto = false;
                JOptionPane.showMessageDialog(this, "El campo 'Nro Empleado' debe ser un numero válido", "Formato inválido", JOptionPane.WARNING_MESSAGE);
            }
            if (!(sistema.validarExisteCedula(txtCedula.getText()))) {
                sistema.agregarEmpleado(txtNombre.getText(), txtCedula.getText(), txtDireccion.getText(), Integer.parseInt(txtnroEmpleado.getText()));
                txtNombre.setText("");
                txtCedula.setText("");
                txtDireccion.setText("");
                txtnroEmpleado.setText("");
                actualizarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "La cédula ingresada ya está registrada en el sistema.",
                     "Cédula duplicada",
                     JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarEmpleadoNuevo

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnVaciarCampos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarCampos
        txtNombre.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtnroEmpleado.setText("");

    }//GEN-LAST:event_btnVaciarCampos

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalleActionPerformed

        Empleado seleccionado = (Empleado) lstEmpleados.getSelectedValue();
        if (seleccionado != null) {
            VentanaConDetalles vcd = new VentanaConDetalles(sistema);
            vcd.mostrarEmpleado(seleccionado);
            vcd.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado de la lista.", "Empleado no seleccionado", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnVerDetalleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList lstEmpleados;
    private javax.swing.JLabel subitutloRegistro;
    private javax.swing.JLabel subtituloLista;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtnroEmpleado;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();
    }
}
