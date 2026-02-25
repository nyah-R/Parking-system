// Trabajo desarollado por: Facundo Esquivel (306504) y Nyah Rüting (270931).
package interfaz;

import dominio.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import parkingcentrico24_7.GestorSistema;
import parkingcentrico24_7.MiniJuego;

public class VentanaPrincipal extends javax.swing.JFrame implements Observer {

    private Sistema sistema;

    public VentanaPrincipal(Sistema unSistema) {
        this.sistema = unSistema;
        initComponents();
        setResizable(false);
        sistema.addObserver(this);
        actualizarColor();

        ImageIcon icono = new ImageIcon(getClass().getResource("logo.jpg"));
        this.setIconImage(icono.getImage());
    }

    public void actualizarColor() {
        ColoresFondo.aplicarTema(this.getContentPane(), sistema.esTemaOscuro());
        btnTema.setText(sistema.esTemaOscuro() ? "Tema Claro" : "Tema Oscuro");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        btnTema = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        gestion = new javax.swing.JMenu();
        gClientes = new javax.swing.JMenuItem();
        gVehiculos = new javax.swing.JMenuItem();
        gEmpleados = new javax.swing.JMenuItem();
        gContratos = new javax.swing.JMenuItem();
        movimientos = new javax.swing.JMenu();
        mEntradas = new javax.swing.JMenuItem();
        mSalidas = new javax.swing.JMenuItem();
        mServAdicionales = new javax.swing.JMenuItem();
        varios = new javax.swing.JMenu();
        vReportes = new javax.swing.JMenuItem();
        vRecDeDatos = new javax.swing.JMenuItem();
        vGrabacionDeDatos = new javax.swing.JMenuItem();
        vMiniJuego = new javax.swing.JMenuItem();
        vInfoAutores = new javax.swing.JMenuItem();
        terminar = new javax.swing.JMenu();
        tSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Obligatorio Prog 2 - Autores: Nyah Rüting (270931) y Facundo Esquivel (306504) "); // NOI18N
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 0, 0));
        setIconImages(null);
        setResizable(false);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(null);

        btnTema.setBackground(new java.awt.Color(255, 204, 51));
        btnTema.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTema.setText("Tema Oscuro");
        btnTema.setBorder(null);
        btnTema.setDisplayedMnemonicIndex(1);
        btnTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemaActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnTema);
        btnTema.setBounds(270, 230, 140, 30);

        jMenuBar1.setBorder(null);
        jMenuBar1.setToolTipText("");
        jMenuBar1.setOpaque(true);

        gestion.setText("Gestión");

        gClientes.setText("Gestión de Clientes");
        gClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarVentanaCliente(evt);
            }
        });
        gestion.add(gClientes);

        gVehiculos.setText("Gestión de Vehiculos");
        gVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarVentanaVehiculos(evt);
            }
        });
        gestion.add(gVehiculos);

        gEmpleados.setText("Gestión de Empleados");
        gEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gEmpleadosActionPerformed(evt);
            }
        });
        gestion.add(gEmpleados);

        gContratos.setText("Gestión de Contratos");
        gContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirVentanaGestionContratos(evt);
            }
        });
        gestion.add(gContratos);

        jMenuBar1.add(gestion);

        movimientos.setText("Movimientos");

        mEntradas.setText("Entradas");
        mEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEntradasActionPerformed(evt);
            }
        });
        movimientos.add(mEntradas);

        mSalidas.setText("Salidas");
        mSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mSalidasActionPerformed(evt);
            }
        });
        movimientos.add(mSalidas);

        mServAdicionales.setText("Servicios Adicionales");
        mServAdicionales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mServAdicionalesActionPerformed(evt);
            }
        });
        movimientos.add(mServAdicionales);

        jMenuBar1.add(movimientos);

        varios.setText("Varios");

        vReportes.setText("Reportes");
        vReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vReportesActionPerformed(evt);
            }
        });
        varios.add(vReportes);

        vRecDeDatos.setText("Recuperación de datos");
        vRecDeDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarDatos(evt);
            }
        });
        varios.add(vRecDeDatos);

        vGrabacionDeDatos.setText("Grabación de datos");
        vGrabacionDeDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarDatos(evt);
            }
        });
        varios.add(vGrabacionDeDatos);

        vMiniJuego.setText("MiniJuego");
        vMiniJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarJuego(evt);
            }
        });
        varios.add(vMiniJuego);

        vInfoAutores.setText("Información de Autores");
        vInfoAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vInfoAutoresMostrarVentana(evt);
            }
        });
        varios.add(vInfoAutores);

        jMenuBar1.add(varios);

        terminar.setBackground(new java.awt.Color(0, 204, 204));
        terminar.setText("Terminar");

        tSalir.setText("Salir");
        tSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirDelSistema(evt);
            }
        });
        terminar.add(tSalir);

        jMenuBar1.add(terminar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(663, 356));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void vReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vReportesActionPerformed
        VentanaReportes vr = new VentanaReportes(sistema);
        vr.setVisible(true);
    }//GEN-LAST:event_vReportesActionPerformed

    private void mostrarVentanaCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarVentanaCliente

        VentanaCliente vc = new VentanaCliente(this.sistema);
        vc.setVisible(true);
    }//GEN-LAST:event_mostrarVentanaCliente

    private void mostrarVentanaVehiculos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarVentanaVehiculos

        VentanaVehiculo vv = new VentanaVehiculo(this.sistema);
        vv.setVisible(true);

    }//GEN-LAST:event_mostrarVentanaVehiculos

    private void vInfoAutoresMostrarVentana(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vInfoAutoresMostrarVentana

        VentanaInfoAutores via = new VentanaInfoAutores(sistema);
        via.setVisible(true);

    }//GEN-LAST:event_vInfoAutoresMostrarVentana

    private void abrirVentanaGestionContratos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirVentanaGestionContratos

        VentanaContrato vcc = new VentanaContrato(sistema);
        vcc.setVisible(true);

    }//GEN-LAST:event_abrirVentanaGestionContratos

    private void gEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gEmpleadosActionPerformed
        VentanaEmpleado vem = new VentanaEmpleado(sistema);
        vem.setVisible(true);
    }//GEN-LAST:event_gEmpleadosActionPerformed

    private void salirDelSistema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirDelSistema

        int opcion = JOptionPane.showConfirmDialog(
             this,
             "¿Está segura de que desea salir del sistema?",
             "Confirmar salida",
             JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_salirDelSistema

    private void cargarDatos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarDatos

        int opcion = JOptionPane.showConfirmDialog(
             this,
             "¿Desea cargar los datos del sistema?\nSe sobrescribirán los datos actuales.",
             "Confirmar carga",
             JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            GestorSistema.cargar(this);
            JOptionPane.showMessageDialog(
                 this,
                 "¡Los datos fueron cargados correctamente!",
                 "Carga exitosa",
                 JOptionPane.INFORMATION_MESSAGE
            );
        }

    }//GEN-LAST:event_cargarDatos

    private void guardarDatos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarDatos
        int respuesta = JOptionPane.showConfirmDialog(
             this,
             "¿Desea guardar los datos actuales del sistema?",
             "Confirmar guardado",
             JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            GestorSistema.guardarSistema(sistema);
            JOptionPane.showMessageDialog(
                 this,
                 "¡Los datos fueron guardados correctamente!",
                 "Guardado exitoso",
                 JOptionPane.INFORMATION_MESSAGE
            );
    }    }//GEN-LAST:event_guardarDatos

    private void jugarJuego(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarJuego
        MiniJuego miniJuego = new MiniJuego();
        miniJuego.iniciar();

    }//GEN-LAST:event_jugarJuego

    private void mServAdicionalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mServAdicionalesActionPerformed

        VentanaServiciosAdicionales vsa = new VentanaServiciosAdicionales(sistema);
        vsa.setVisible(true);
    }//GEN-LAST:event_mServAdicionalesActionPerformed

    private void btnTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemaActionPerformed
        sistema.cambiarTema();

    }//GEN-LAST:event_btnTemaActionPerformed

    private void mEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEntradasActionPerformed
        VentanaEntrada ve = new VentanaEntrada(sistema);
        ve.setVisible(true);
    }//GEN-LAST:event_mEntradasActionPerformed

    private void mSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mSalidasActionPerformed
        VentanaSalidas vs = new VentanaSalidas(sistema);
        vs.setVisible(true);
    }//GEN-LAST:event_mSalidasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnTema;
    private javax.swing.JMenuItem gClientes;
    private javax.swing.JMenuItem gContratos;
    private javax.swing.JMenuItem gEmpleados;
    private javax.swing.JMenuItem gVehiculos;
    private javax.swing.JMenu gestion;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mEntradas;
    private javax.swing.JMenuItem mSalidas;
    private javax.swing.JMenuItem mServAdicionales;
    private javax.swing.JMenu movimientos;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JMenuItem tSalir;
    private javax.swing.JMenu terminar;
    private javax.swing.JMenuItem vGrabacionDeDatos;
    private javax.swing.JMenuItem vInfoAutores;
    private javax.swing.JMenuItem vMiniJuego;
    private javax.swing.JMenuItem vRecDeDatos;
    private javax.swing.JMenuItem vReportes;
    private javax.swing.JMenu varios;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object ob) {
        actualizarColor();

    }

}
