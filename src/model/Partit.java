package model;

import java.util.Objects;

/**
 * Representa un partido político con su nombre y siglas.
 */
public class Partit {
    private String nom;
    private String sigles;

    /**
     * Constructor de la clase Partit.
     *
     * @param nom    El nombre del partido político.
     * @param sigles Las siglas del partido político.
     */
    public Partit(String nom, String sigles) {
        this.nom = nom;
        this.sigles = sigles;
    }

    /**
     * Obtiene el nombre del partido político.
     *
     * @return El nombre del partido político.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre del partido político.
     *
     * @param nom El nombre del partido político.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtiene las siglas del partido político.
     *
     * @return Las siglas del partido político.
     */
    public String getSigles() {
        return sigles;
    }

    /**
     * Establece las siglas del partido político.
     *
     * @param sigles Las siglas del partido político.
     */
    public void setSigles(String sigles) {
        this.sigles = sigles;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Partit.
     *
     * @return Una cadena que representa el objeto Partit.
     */
    @Override
    public String toString() {
        return "Partit{" +
                "nom='" + nom + '\'' +
                ", sigles='" + sigles + '\'' +
                '}';
    }

    /**
     * Indica si el objeto Partit es igual a otro objeto dado.
     *
     * @param obj El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Partit other = (Partit) obj;
        return Objects.equals(sigles, other.sigles);
    }

    /**
     * Devuelve el valor del código hash para el objeto Partit.
     *
     * @return El valor del código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom, sigles);
    }
}

