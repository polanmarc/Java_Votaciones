package controller;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import model.*;


public class LeerCSV {
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "database.db4o");
        try {
        	
        	// Municipi
        	// Resultat
        	// Partit
        	
            ObjectSet<Resultat> resultados = db.queryByExample(Resultat.class);

            int cantidadResult = 0;
            for (Resultat select : resultados) {
            	cantidadResult++;
                System.out.println(select);
            }

            System.out.println("Total elementos: " + cantidadResult);
        } finally {
            db.close();
        }
    }
}