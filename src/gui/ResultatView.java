package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Resultat;

import java.awt.BorderLayout;
import java.util.List;

/**
 * La clase ResultatView representa una ventana que muestra tablas de resultados de elecciones
 */
public class ResultatView extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public ResultatView() {
    	initialize();
    }

    /**
     * Inicializa el contenido de la ventana
     */
    void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setVisible(true);
    }
    
    /**
     * Método para mostrar una lista de todos los resultados en una tabla
     *
     * @param resultats La lista de resultados
     */
    public void tableSelectAll(List<Resultat> resultats) {
        setTitle("Tabla de Resultados Generales");

        String[] columnNames = {"Municipi", "Partit", "Percentatge", "Vots"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Resultat resultat : resultats) {
            Object[] rowData = {
                    resultat.getMunicipi().getNom(),
                    resultat.getPartit().getSigles(),
                    resultat.getPercentatge(),
                    resultat.getVots()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
    /**
     * Método para mostrar una lista de resultados de partidos por municipio en una tabla
     *
     * @param resultats La lista de resultados de partidos por municipio
     */
    public void tablePartitForMunicipi(List<Resultat> resultats) {
        setTitle("Tabla de Resultados de Partidos Por Municipio");

        String[] columnNames = {"Municipi", "Partit", "Percentatge", "Vots"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Resultat resultat : resultats) {
            Object[] rowData = {
                    resultat.getMunicipi().getNom(),
                    resultat.getPartit().getSigles(),
                    resultat.getPercentatge(),
                    resultat.getVots()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
    /**
     * Método para mostrar una lista de resultados de municipios por partido en una tabla
     *
     * @param resultats La lista de resultados de municipios por partido
     */
    public void tableMunicipiForPartit(List<Resultat> resultats) {
        setTitle("Tabla de Resultados de Municipios Por Partido");

        String[] columnNames = {"Municipi", "Partit", "Percentatge", "Vots"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Resultat resultat : resultats) {
            Object[] rowData = {
                    resultat.getMunicipi().getNom(),
                    resultat.getPartit().getSigles(),
                    resultat.getPercentatge(),
                    resultat.getVots()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
}
