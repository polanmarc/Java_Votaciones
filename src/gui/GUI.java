package gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.formdev.flatlaf.FlatDarkLaf;

import excepcion.EmptyTable;
import excepcion.WrongComarcaProvincia;
import model.Municipi;
import model.MunicipiModel;
import model.Resultat;
import model.ResultatModel;
import model.Partit;
import model.PartitModel;

import java.awt.Font;

import java.lang.reflect.Field;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;

/* 
 * Justificación ArrayList
 * 
 * He utilizado ArrayList ya que no nos interesa el contains y el remove, solamente utilizaremos el get y el add.
 * ArrayList nos permite tener una lectura y añadir elementos de manera constante. Además, es indiferente el orden 
 * de iteración al añadir los elementos. Es una colección muy fácil de utilizar y se adapta perfectamente a las 
 * necesidades del programa.
 * 
 */

public class GUI implements ActionListener {

    private ObjectContainer db;
    
    private MunicipiModel mMunicipi;
    private ResultatModel mResultat;
    private PartitModel mPartit;

    private JFrame frame;
    private JFrame aboutFrame;
    private JFrame estadisticasFrame;
    
    private JComboBox<String> comboProvincies;
    private JComboBox<String> comboBoxSimple;
    private JComboBox<String> comboBoxResultsFor;
    private JComboBox<String> comboComarcas;
    private JComboBox<String> comboMunicipis;
    private JComboBox<String> comboPartit;

    private JTabbedPane tabbedPane;
    private JPanel pnlResultSimple;
    private JPanel pnlResultsFor;
    private JPanel pnlImportCSV;
    private JPanel pnlTitleContent;
    private JPanel pnlEstadistica;
    
    private JLabel lblResultSimple;
    private JLabel lblResultsFor;
    private JLabel lblTitle;
    private JLabel lblImportTitle;
    private JLabel lblImage;
    private JLabel lblSeparator;
    
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem aboutItemMenu;
    private JMenuItem estadisticasItemMenu;
    private JMenuItem exitItemMenu;
    
    private JPanel pnlTitle;
    private JButton btnPartit;
    private JButton btnSearchDB;
    private JTextField txtPartit;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * 
     * @wbp.parser.entryPoint
     */
    public GUI() {
    	this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "database.db4o");
        this.mMunicipi = new MunicipiModel(db);
        this.mPartit = new PartitModel(db);
        this.mResultat = new ResultatModel(db);
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {    	
        frame = new JFrame();
        frame.setTitle("Votacions");
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("Archivo");
		menuBar.add(fileMenu);

		aboutItemMenu = new JMenuItem("About");
		aboutItemMenu.setAccelerator(KeyStroke.getKeyStroke("control A"));
		estadisticasItemMenu = new JMenuItem("Estadisticas");
		estadisticasItemMenu.setAccelerator(KeyStroke.getKeyStroke("control S"));
		exitItemMenu = new JMenuItem("Salir");
		exitItemMenu.setAccelerator(KeyStroke.getKeyStroke("control E"));

		fileMenu.add(estadisticasItemMenu);
		fileMenu.add(aboutItemMenu);
		fileMenu.addSeparator();
		fileMenu.add(exitItemMenu);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        pnlTitle = new JPanel();
        frame.getContentPane().add(pnlTitle, BorderLayout.NORTH);
        
        pnlTitleContent = new JPanel(new BorderLayout());
        pnlTitleContent.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        lblImage = new JLabel(new ImageIcon("img/thosi_logo.png"));
        pnlTitleContent.add(lblImage, BorderLayout.WEST);

        lblSeparator = new JLabel("   ");
        pnlTitleContent.add(lblSeparator, BorderLayout.CENTER);
        
        lblTitle = new JLabel("BD4O Votaciones");
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        lblTitle.setFont(new Font("Nimbus Sans", Font.BOLD, 30));
        pnlTitleContent.add(lblTitle, BorderLayout.EAST);
        pnlTitle.add(pnlTitleContent);

        pnlResultSimple = new JPanel(new GridLayout(8, 6));
        tabbedPane.addTab("Listado Rapido", null, pnlResultSimple, null);
        
        pnlResultsFor = new JPanel();
        tabbedPane.addTab("Resultado por...", null, pnlResultsFor, null);
        
        pnlImportCSV = new JPanel(new GridLayout(8, 6));
        tabbedPane.addTab("Importar CSV", null, pnlImportCSV, null);
        
        lblImportTitle = new JLabel("Importar CSV");
        lblImportTitle.setFont(new Font("FreeSans", Font.BOLD, 18));
        pnlImportCSV.add(lblImportTitle);

        lblResultSimple = new JLabel("Resultados Simples");
        lblResultSimple.setFont(new Font("FreeSans", Font.BOLD, 18));
        pnlResultSimple.add(lblResultSimple);

        comboBoxSimple = new JComboBox<>();
        comboBoxSimple.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        comboBoxSimple.addItem("Municipi");
        comboBoxSimple.addItem("Partit");
        comboBoxSimple.addItem("Resultat");
        pnlResultSimple.add(comboBoxSimple);
        pnlResultsFor.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblResultsFor = new JLabel("Resultados Por Tablas");
        lblResultsFor.setFont(new Font("FreeSans", Font.BOLD, 18));
        pnlResultsFor.add(lblResultsFor);
        
        comboBoxResultsFor = new JComboBox<>();
        comboBoxResultsFor.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        comboBoxResultsFor.addItem("Resultats per partit, en un municipi");
        comboBoxResultsFor.addItem("Resultats per municipi, d'un partit");
        comboBoxResultsFor.addItem("Resultats per partit en una província o comarca donada");
        pnlResultsFor.add(comboBoxResultsFor);
        
        comboProvincies = new JComboBox<>();
        comboProvincies.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        comboProvincies.setVisible(false);
        pnlResultsFor.add(comboProvincies);
        
        comboComarcas = new JComboBox<>();
        comboComarcas.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        comboComarcas.setVisible(false);
        pnlResultsFor.add(comboComarcas);
        
        comboMunicipis = new JComboBox<>();
        comboMunicipis.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        comboMunicipis.setVisible(false);
        pnlResultsFor.add(comboMunicipis);
        
        comboPartit = new JComboBox<>();
        comboPartit.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        comboPartit.setVisible(false);
        pnlResultsFor.add(comboPartit);
        
        txtPartit = new JTextField();
        txtPartit.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        btnPartit = new JButton("Buscar");
        txtPartit.setColumns(10);
        
        btnSearchDB = new JButton("Buscar Archivo");
        btnSearchDB.setFont(new Font("Lohit Devanagari", Font.PLAIN, 16));
        pnlImportCSV.add(btnSearchDB);
        
        /* addActionListener */
        
        comboBoxSimple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	buttonsActions((String) comboBoxSimple.getSelectedItem());
            }
        });
        
        comboBoxResultsFor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	buttonsActions((String) comboBoxResultsFor.getSelectedItem());
            }
        });
        
        comboProvincies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProvince = (String) comboProvincies.getSelectedItem();
                if (selectedProvince != null && !selectedProvince.equals("Default")) {
                	showComarcasForProvincia(selectedProvince);
                    comboComarcas.setVisible(true);
        		}
            }
        });
        
        comboComarcas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedComarca = (String) comboComarcas.getSelectedItem();
                if (selectedComarca != null && !selectedComarca.equals("Default")) {
                	showMunicipisForComarca(selectedComarca);
                	comboMunicipis.setVisible(true);
        		}
            }
        });

        comboMunicipis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedMunicipi = (String) comboMunicipis.getSelectedItem();
        		if (selectedMunicipi != null && !selectedMunicipi.equals("Default")) {
        			System.out.println(selectedMunicipi);
        			showPartitForMunicipi(selectedMunicipi);
        		}
            }
        });
        
        comboPartit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPartit = (String) comboPartit.getSelectedItem();
                if (selectedPartit != null && !selectedPartit.equals("Default")) {
                	showMunicipiForPartit(selectedPartit);
                }
            }
        });
        
        btnPartit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = txtPartit.getText().trim();
                if (texto != null && texto != "") {
                	searchProvinciaOrComarca(texto);
                }
            }
        });
        
        btnSearchDB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	searchFileReader();
            }
        });
        
        aboutItemMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	buttonsActions((String) aboutItemMenu.getText());
            }
        });
        
        estadisticasItemMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	buttonsActions((String) estadisticasItemMenu.getText());
            }
        });
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Cerrando base de datos");
                db.close();
                db.ext();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    private void buttonsActions(String texto) {     	
        try {
            this.mResultat = new ResultatModel(db);
            this.mPartit = new PartitModel(db);
            switch (texto) {
                case "Municipi":
                    List<Municipi> listaMunicipis = this.mMunicipi.selectAll();
                    if (listaMunicipis.isEmpty()) {
                        throw new EmptyTable("A002", "No se ha encontrado ningun resultado en esta consulta \"Municipi\".");
                    }
                    new MunicipiView().tableSelectAll(listaMunicipis);
                    break;
                case "Partit":
                    List<Partit> listaPartit = this.mPartit.selectAll();
                    if (listaPartit.isEmpty()) {
                        throw new EmptyTable("A003", "No se ha encontrado ningun resultado en esta consulta \"Partit\".");
                    }
                    new PartitView().tableSelectAll(listaPartit);
                    break;
                case "Resultat":
                    List<Resultat> listaResultat = this.mResultat.selectAll();
                    if (listaResultat.isEmpty()) {
                        throw new EmptyTable("A004", "No se ha encontrado ningun resultado en esta consulta \"Resultat\".");
                    } 
                    new ResultatView().tableSelectAll(listaResultat);
                    break;
                case "Resultats per partit, en un municipi":
                    List<String> listResultsProvincies = mResultat.selectAllProvincias();
                    if (listResultsProvincies.isEmpty()) {
                        throw new EmptyTable("A005", "No se ha encontrado ningun resultado en esta consulta \"Resultats per partit, en un municipi\".");
                    } else {
                    	showComponentVisibility(true, false, false, false, false, false);
                        comboProvincies.setVisible(true);
                        comboProvincies.removeAllItems();
                        comboProvincies.addItem("Default");
                        comboProvincies.setSelectedItem("Default");
                        
                        for (String elemento : listResultsProvincies) {
                            comboProvincies.addItem(elemento);
                        }
                    }
                    break;
                case "Resultats per municipi, d'un partit":
                    List<Partit> listResultsPartits = mPartit.selectAll();
                    if (listResultsPartits.isEmpty()) {
                        throw new EmptyTable("A006", "No se ha encontrado ningun resultado en esta consulta \"Resultats per municipi, d'un partit\".");
                    } else {
                    	showComponentVisibility(false, false, false, true, false, false);
                        comboPartit.removeAllItems();
                        comboPartit.addItem("Default");
                        comboPartit.setSelectedItem("Default");
                        comboPartit.setVisible(true);
                        
                        for (Partit elemento : listResultsPartits) {
                            comboPartit.addItem((String) elemento.getSigles());
                        }
                    }
                    break;
                case "Resultats per partit en una província o comarca donada":
                	pnlResultsFor.add(txtPartit);
                	pnlResultsFor.add(btnPartit);
                    showComponentVisibility(false, false, false, false, true, true);
                    break;
                case "About":
                	showAboutWindow();
                    break;
                case "Estadisticas":
                	showEstadisticasWindow();
                    break;
            }
        } catch (EmptyTable et) {
            JOptionPane.showMessageDialog(null, et.getMessage(), et.getCodi(), JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /* Resultats per partit, en un municipi */
    
    private void showComarcasForProvincia(String provincia) {
        comboComarcas.removeAllItems();
        comboComarcas.addItem("Default");
        comboComarcas.setSelectedItem("Default");
        ResultatModel model = new ResultatModel(db);
        System.out.println(model);
        List<String> comarcas = model.selectAllComarcasForProvincia(provincia);
        System.out.println("Comarcas" + comarcas);
        for (String comarca : comarcas) {
            comboComarcas.addItem(comarca);
        }
        comboComarcas.setVisible(true);
    }

    private void showMunicipisForComarca(String comarca) {
        comboMunicipis.removeAllItems();
        comboMunicipis.addItem("Default");
        comboMunicipis.setSelectedItem("Default");
        List<String> municipis = mResultat.selectAllMunicipisForComarca(comarca);
        for (String municipi : municipis) {
            comboMunicipis.addItem(municipi);
        }
    }
    
    private void showPartitForMunicipi(String municipi) {
    	Municipi objMunicipi = new Municipi(null, null, municipi);
    	List<Resultat> listaPartidos = mResultat.reedPartitsForMunicipi(objMunicipi);
    	ResultatView vResultat = new ResultatView();
    	vResultat.tablePartitForMunicipi(listaPartidos);
    }
    
    /* Resultats per municipi, d'un partit */
    
    private void showMunicipiForPartit(String siglasPartit) {
    	Partit objPartit = new Partit(null, siglasPartit);
    	List<Resultat> listaPartidos = mResultat.reedMunicipiForPartits(objPartit);
    	ResultatView vResultat = new ResultatView();
    	vResultat.tableMunicipiForPartit(listaPartidos);
    }
    
    /* Resultats per partit en una província o comarca donada */
    
    private void searchProvinciaOrComarca(String buscar) {
    	try {
    		Municipi buscarElemento = mMunicipi.searchComarcaOrProvincia(buscar);
        	if (buscarElemento == null) {
        		throw new WrongComarcaProvincia("E001", "No existe esta comarca o provincia");
        	} else {
        		List<Resultat> listaPartidos = mResultat.reedPartitForProvinciaOrComarca(buscarElemento);
        		if (listaPartidos.isEmpty()) {
                	throw new EmptyTable("A007", "No se ha encontrado ningun resultado en esta consulta \"Resultats per partit en una província o comarca donada\".");
        		}
        		ResultatView vResultat = new ResultatView();
            	vResultat.tableMunicipiForPartit(listaPartidos);
        	}
    	} catch (WrongComarcaProvincia e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getCodi(), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyTable et) {
    		JOptionPane.showMessageDialog(null, et.getMessage(), et.getCodi(), JOptionPane.ERROR_MESSAGE);
    	} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error search comarca or provincia", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void searchFileReader() {
    	System.out.println("Leiendo Archvio");
    	ImportCSVView vImportDB = new ImportCSVView(db);
    }
    
    private void showComponentVisibility(boolean comboProvinciesVisible, boolean comboComarcasVisible, 
    		boolean comboMunicipisVisible, boolean comboPartitVisibl, boolean txtPartitVisible, boolean btnPartitVisible) {
        comboProvincies.setVisible(comboProvinciesVisible);
        comboComarcas.setVisible(comboComarcasVisible);
        comboMunicipis.setVisible(comboMunicipisVisible);
        comboPartit.setVisible(comboPartitVisibl);
        txtPartit.setVisible(txtPartitVisible);
        btnPartit.setVisible(btnPartitVisible);
    }
    
    private void showAboutWindow() {
		aboutFrame = new JFrame("Acerca de");
		aboutFrame.setSize(300, 150);

		JLabel label = new JLabel("(c) - Votacions Marc v1.0");
		label.setHorizontalAlignment(JLabel.CENTER);

		aboutFrame.getContentPane().add(label);
		aboutFrame.setVisible(true);
	}
    
    private void showEstadisticasWindow() {
        estadisticasFrame = new JFrame("Estadísticas");
        estadisticasFrame.setSize(300, 200);
        estadisticasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estadisticasFrame.setLocationRelativeTo(null);

        pnlEstadistica = new JPanel();
        pnlEstadistica.setLayout(new BoxLayout(pnlEstadistica, BoxLayout.Y_AXIS));

        int quantityMunicipi = mMunicipi.selectAll().size();
        int quantityPartit = mPartit.selectAll().size();
        int quantityResultat = mResultat.selectAll().size();

        JLabel labelMunicipi = new JLabel("Municipios: " + quantityMunicipi);
        labelMunicipi.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelMunicipi.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel labelPartit = new JLabel("Partidos: " + quantityPartit);
        labelPartit.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPartit.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel labelResultat = new JLabel("Resultados: " + quantityResultat);
        labelResultat.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelResultat.setFont(new Font("Arial", Font.BOLD, 18));

        pnlEstadistica.add(Box.createVerticalGlue());
        pnlEstadistica.add(labelMunicipi);
        pnlEstadistica.add(labelPartit);
        pnlEstadistica.add(labelResultat);
        pnlEstadistica.add(Box.createVerticalGlue());

        estadisticasFrame.getContentPane().add(pnlEstadistica);
        estadisticasFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {}

}

//private boolean isDatabaseClosed() {
//if (db == null) {
//  return true;
//} else {
//  try {
//      ObjectSet<Object> result = db.query(Object.class);
//      return false;
//  } catch (com.db4o.ext.DatabaseClosedException e) {
//      return true;
//  }
//}
//}

//private <T> void mostrarTabla(List<T> objetos, String[] columnes) {
//JFrame frame = new JFrame("Tabla de " + objetos.get(0).getClass().getSimpleName() + "s");
//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//DefaultTableModel tableModel = new DefaultTableModel(columnes, 0);
//
//for (T objeto : objetos) {
//  Field[] fields = objeto.getClass().getDeclaredFields();
//  Object[] rowData = new Object[fields.length];
//  for (int i = 0; i < fields.length; i++) {
//      try {
//          Field field = objeto.getClass().getDeclaredField(fields[i].getName());
//          field.setAccessible(true);
//          Object value = field.get(objeto);
//          rowData[i] = value;
//      } catch (NoSuchFieldException | IllegalAccessException e) {
//          e.printStackTrace();
//      }
//  }
//  tableModel.addRow(rowData);
//}
//
//JTable table = new JTable(tableModel);
//
//JScrollPane scrollPane = new JScrollPane(table);
//frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//
//frame.setSize(600, 400);
//frame.setLocationRelativeTo(null);
//frame.setVisible(true);
//}