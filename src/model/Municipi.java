package model;

import java.util.Objects;

/**
 * Representa un municipio con su comarca, provincia y nombre.
 */
public class Municipi {
    private String comarca;
    private String provincia;
    private String nom;

    /**
     * Constructor de la clase Municipi.
     *
     * @param comarca   La comarca del municipio.
     * @param provincia La provincia del municipio.
     * @param nom       El nombre del municipio.
     */
    public Municipi(String comarca, String provincia, String nom) {
        this.comarca = comarca;
        this.provincia = provincia;
        this.nom = nom;
    }

    /**
     * Obtiene la comarca del municipio.
     *
     * @return La comarca del municipio.
     */
    public String getComarca() {
        return comarca;
    }

    /**
     * Establece la comarca del municipio.
     *
     * @param comarca La comarca del municipio.
     */
    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    /**
     * Obtiene la provincia del municipio.
     *
     * @return La provincia del municipio.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia del municipio.
     *
     * @param provincia La provincia del municipio.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtiene el nombre del municipio.
     *
     * @return El nombre del municipio.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre del municipio.
     *
     * @param nom El nombre del municipio.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Municipi.
     *
     * @return Una cadena que representa el objeto Municipi.
     */
    @Override
    public String toString() {
        return "Municipi{" +
                "comarca='" + comarca + '\'' +
                ", provincia='" + provincia + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    /**
     * Indica si el objeto Municipi es igual a otro objeto dado.
     *
     * @param obj El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Municipi other = (Municipi) obj;
        return Objects.equals(this.nom, other.nom) && Objects.equals(this.provincia, other.provincia) && Objects.equals(this.comarca, other.comarca);
    }

    /**
     * Devuelve el valor del código hash para el objeto Municipi.
     *
     * @return El valor del código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(comarca, provincia, nom);
    }
}

