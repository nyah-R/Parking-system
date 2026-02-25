// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VentanaContrato extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaContrato(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
        sistema.addObserver(this);
        actualizarColor();
        actualizarCombosYlista();
        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());

    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());

    }

    public void actualizarCombosYlista() {

        cmbCliente.removeAllItems();
        for (Cliente cli : sistema.getListaClientes()) {
            cmbCliente.addItem(cli);
        }

        cmbVehiculo.removeAllItems();
        for (Vehiculo veh : sistema.mostrarVehiculosSinContrato()) {
            cmbVehiculo.addItem(veh);
        }

        cmbEmpleado.removeAllItems();
        for (Empleado emp : sistema.getListaEmpleados()) {
            cmbEmpleado.addItem(emp);
        }
        lstContratos.setListData(sistema.getListaContratos().toArray());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCliente = new javax.swing.JComboBox<>();
        cmbVehiculo = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel();
        lblVehiculo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        cmbEmpleado = new javax.swing.JComboBox<>();
        lblContrato = new javax.swing.JLabel();
        lblValorMensual = new javax.swing.JLabel();
        txtValorMensual = new javax.swing.JTextField();
        lblSignoPesos = new javax.swing.JLabel();
        btnCrearContrato = new javax.swing.JButton();
        lblSubtituloLista = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstContratos = new javax.swing.JList();
        btbVerDetalles = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Contratos");
        setResizable(false);
        getContentPane().setLayout(null);

        cmbCliente.setBorder(null);
        cmbCliente.setFocusable(false);
        cmbCliente.setKeySelectionManager(null);
        cmbCliente.setLightWeightPopupEnabled(false);
        cmbCliente.setOpaque(true);
        cmbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClienteActionPerformed(evt);
            }
        });
        getContentPane().add(cmbCliente);
        cmbCliente.setBounds(30, 80, 210, 20);

        getContentPane().add(cmbVehiculo);
        cmbVehiculo.setBounds(30, 130, 210, 22);

        lblCliente.setText("Cliente:");
        getContentPane().add(lblCliente);
        lblCliente.setBounds(30, 60, 150, 16);

        lblVehiculo.setText("Vehiculo: ");
        getContentPane().add(lblVehiculo);
        lblVehiculo.setBounds(30, 110, 160, 16);

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSubtitulo.setText("Registro de Contratos");
        getContentPane().add(lblSubtitulo);
        lblSubtitulo.setBounds(60, 30, 150, 16);

        cmbEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmpleadoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbEmpleado);
        cmbEmpleado.setBounds(30, 180, 210, 22);

        lblContrato.setText("Contrato realizado por: ");
        getContentPane().add(lblContrato);
        lblContrato.setBounds(30, 160, 150, 16);

        lblValorMensual.setText("Valor Mensual:");
        getContentPane().add(lblValorMensual);
        lblValorMensual.setBounds(30, 210, 140, 20);

        txtValorMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorMensualActionPerformed(evt);
            }
        });
        getContentPane().add(txtValorMensual);
        txtValorMensual.setBounds(30, 230, 120, 20);

        lblSignoPesos.setText("$");
        getContentPane().add(lblSignoPesos);
        lblSignoPesos.setBounds(160, 230, 20, 16);

        btnCrearContrato.setText("Crear contrato");
        btnCrearContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearContratoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrearContrato);
        btnCrearContrato.setBounds(70, 270, 140, 23);

        lblSubtituloLista.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSubtituloLista.setText("Lista de Contratos");
        getContentPane().add(lblSubtituloLista);
        lblSubtituloLista.setBounds(360, 30, 140, 16);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(30, 50, 210, 10);

        lstContratos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nro.Contrato - Cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));
        lstContratos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstContratos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(300, 60, 240, 190);

        btbVerDetalles.setText("Ver detalles");
        btbVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbVerDetallesActionPerformed(evt);
            }
        });
        getContentPane().add(btbVerDetalles);
        btbVerDetalles.setBounds(360, 270, 140, 23);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(300, 50, 240, 10);

        setSize(new java.awt.Dimension(595, 360));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbClienteActionPerformed

    private void cmbEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEmpleadoActionPerformed

    private void txtValorMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorMensualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorMensualActionPerformed

    private void btnCrearContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearContratoActionPerformed

        Cliente clienteSeleccionado = (Cliente) cmbCliente.getSelectedItem();
        Vehiculo vehiculoSeleccionado = (Vehiculo) cmbVehiculo.getSelectedItem();
        Empleado empleadoSeleccionado = (Empleado) cmbEmpleado.getSelectedItem();

        String valorMensualTexto = txtValorMensual.getText();

        if (clienteSeleccionado == null || vehiculoSeleccionado == null || empleadoSeleccionado == null || valorMensualTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int valorMensual;
        try {
            valorMensual = Integer.parseInt(valorMensualTexto);
            if (valorMensual < 0) {
                throw new NumberFormatException("negativo");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor mensual debe ser un número entero positivo.", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sistema.agregarContrato(clienteSeleccionado, vehiculoSeleccionado, empleadoSeleccionado, valorMensual);
        JOptionPane.showMessageDialog(this, "Contrato creado correctamente.");

        actualizarCombosYlista();
        txtValorMensual.setText("");

    }//GEN-LAST:event_btnCrearContratoActionPerformed

    private void btbVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbVerDetallesActionPerformed
        Contrato seleccionado = (Contrato) lstContratos.getSelectedValue();
        if (seleccionado != null) {
            VentanaConDetalles vdc = new VentanaConDetalles(sistema);
            vdc.mostrarContrato(seleccionado);
            vdc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un contrato de la lista.", "Contrato no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btbVerDetallesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbVerDetalles;
    private javax.swing.JButton btnCrearContrato;
    private javax.swing.JComboBox<Cliente> cmbCliente;
    private javax.swing.JComboBox<Empleado> cmbEmpleado;
    private javax.swing.JComboBox<Vehiculo> cmbVehiculo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblContrato;
    private javax.swing.JLabel lblSignoPesos;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblSubtituloLista;
    private javax.swing.JLabel lblValorMensual;
    private javax.swing.JLabel lblVehiculo;
    private javax.swing.JList lstContratos;
    private javax.swing.JTextField txtValorMensual;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        actualizarColor();
        actualizarCombosYlista();

    }

}
