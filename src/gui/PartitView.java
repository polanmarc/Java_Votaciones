package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Partit;

import java.awt.BorderLayout;
import java.util.List;

/**
 * La clase PartitView representa una ventana que muestra una tabla de partidos políticos
 */
public class PartitView extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public PartitView() {
    	initialize();
    }

    /**
     * Inicializa el contenido de la ventana
     */
    void initialize() {
    	setTitle("Tabla de Partits");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setVisible(true);
    }
    
    /**
     * Método para mostrar una lista de todos los partidos políticos en una tabla
     *
     * @param partits La lista de partidos políticos
     */
    public void tableSelectAll(List<Partit> partits) {
        String[] columnNames = {"Nom", "Sigles"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Partit partit : partits) {
            Object[] rowData = {partit.getNom(), partit.getSigles()};
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
}
