// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VentanaCliente extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaCliente(Sistema sistema) {

        this.sistema = sistema;
        initComponents();
        setResizable(false);
        sistema.addObserver(this);
        actualizarListaClientes();
        actualizarColor();

        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());
    }

    private void actualizarListaClientes() {
        listaClientes.setListData(sistema.getListaClientes().toArray());
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(panelCliente, sistema.esTemaOscuro());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCliente = new javax.swing.JPanel();
        subtituloRegistro = new javax.swing.JLabel();
        linea = new javax.swing.JSeparator();
        lblNombre = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblAnio = new javax.swing.JLabel();
        btnVerDetalles = new javax.swing.JButton();
        txtAnio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnVaciar = new javax.swing.JButton();
        subtituloRegistrados = new javax.swing.JLabel();
        linea2 = new javax.swing.JSeparator();
        btnEliminarCliente = new javax.swing.JButton();
        btnRegistrarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes"); // NOI18N
        setName("JFrameClientes"); // NOI18N

        panelCliente.setLayout(null);

        subtituloRegistro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        subtituloRegistro.setText("Registrar nuevo cliente");
        panelCliente.add(subtituloRegistro);
        subtituloRegistro.setBounds(90, 40, 200, 16);
        panelCliente.add(linea);
        linea.setBounds(340, 60, 160, 10);

        lblNombre.setText("Nombre:");
        panelCliente.add(lblNombre);
        lblNombre.setBounds(40, 90, 60, 16);

        lblCelular.setText("Celular:");
        panelCliente.add(lblCelular);
        lblCelular.setBounds(40, 240, 80, 16);

        lblCedula.setText("Cédula: ");
        panelCliente.add(lblCedula);
        lblCedula.setBounds(40, 140, 80, 16);

        lblDireccion.setText("Dirección:");
        panelCliente.add(lblDireccion);
        lblDireccion.setBounds(40, 190, 80, 16);

        lblAnio.setText("Cliente desde (año):");
        panelCliente.add(lblAnio);
        lblAnio.setBounds(40, 290, 120, 16);

        btnVerDetalles.setText("Ver detalles");
        btnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verDetallesCliente(evt);
            }
        });
        panelCliente.add(btnVerDetalles);
        btnVerDetalles.setBounds(410, 360, 130, 30);

        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });
        panelCliente.add(txtAnio);
        txtAnio.setBounds(160, 280, 100, 30);

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        panelCliente.add(txtNombre);
        txtNombre.setBounds(110, 80, 150, 30);

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        panelCliente.add(txtCedula);
        txtCedula.setBounds(110, 130, 150, 30);

        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        panelCliente.add(txtCelular);
        txtCelular.setBounds(110, 230, 150, 30);

        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        panelCliente.add(txtDireccion);
        txtDireccion.setBounds(110, 180, 150, 30);

        btnVaciar.setText("Vaciar");
        btnVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCasillasTxtCliente(evt);
            }
        });
        panelCliente.add(btnVaciar);
        btnVaciar.setBounds(20, 360, 80, 30);

        subtituloRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        subtituloRegistrados.setText("Clientes registrados");
        panelCliente.add(subtituloRegistrados);
        subtituloRegistrados.setBounds(360, 40, 170, 16);
        panelCliente.add(linea2);
        linea2.setBounds(40, 60, 220, 10);

        btnEliminarCliente.setText("Eliminar cliente");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        panelCliente.add(btnEliminarCliente);
        btnEliminarCliente.setBounds(260, 360, 140, 30);

        btnRegistrarCliente.setText("Agregar cliente");
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarClienteNuevo(evt);
            }
        });
        panelCliente.add(btnRegistrarCliente);
        btnRegistrarCliente.setBounds(110, 360, 140, 30);

        listaClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Nombre  -  Cédula", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Segoe UI", 0, 10))); // NOI18N
        listaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaClientes);

        panelCliente.add(jScrollPane1);
        jScrollPane1.setBounds(320, 80, 180, 240);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(570, 444));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void verDetallesCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verDetallesCliente

        Cliente seleccionado = (Cliente) listaClientes.getSelectedValue();
        if (seleccionado != null) {
            VentanaConDetalles vdc = new VentanaConDetalles(sistema);
            vdc.mostrarCliente(seleccionado);
            vdc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la lista.", "Cliente no seleccionado", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_verDetallesCliente

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void limpiarCasillasTxtCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCasillasTxtCliente

        txtNombre.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtAnio.setText("");
        txtCelular.setText("");

    }//GEN-LAST:event_limpiarCasillasTxtCliente

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed

        Cliente seleccionado = (Cliente) listaClientes.getSelectedValue();
        sistema.eliminarCliente(seleccionado);
        actualizarListaClientes();

    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void agregarClienteNuevo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarClienteNuevo

        boolean estaCompleto = true;
        if (txtNombre.getText().trim().isEmpty()
             || txtCedula.getText().trim().isEmpty()
             || txtDireccion.getText().trim().isEmpty()
             || txtCelular.getText().trim().isEmpty()
             || txtAnio.getText().trim().isEmpty()) {
            estaCompleto = false;
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        }

        if (estaCompleto) {
            try {
                Integer.parseInt(txtAnio.getText().trim());
            } catch (NumberFormatException e) {
                estaCompleto = false;
                JOptionPane.showMessageDialog(this, "El campo 'Cliente desde (año):' debe ser un numero válido", "Formato inválido", JOptionPane.WARNING_MESSAGE);
            }

            if (!(sistema.validarExisteCedula(txtCedula.getText()))) {
                sistema.agregarCliente(txtNombre.getText(), txtCedula.getText(), txtDireccion.getText(), txtCelular.getText(), Integer.parseInt(txtAnio.getText()));
                txtNombre.setText("");
                txtCedula.setText("");
                txtDireccion.setText("");
                txtAnio.setText("");
                txtCelular.setText("");
                actualizarListaClientes();

            } else {
                JOptionPane.showMessageDialog(this, "La cédula ingresada ya está registrada en el sistema.",
                     "Cédula duplicada",
                     JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_agregarClienteNuevo

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JButton btnVerDetalles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JSeparator linea;
    private javax.swing.JSeparator linea2;
    private javax.swing.JList listaClientes;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JLabel subtituloRegistrados;
    private javax.swing.JLabel subtituloRegistro;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarListaClientes();
        actualizarColor();
    }

}
