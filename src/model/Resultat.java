package model;

import java.util.Objects;

/**
 * Representa un resultado de votación que contiene información sobre el municipio, el partido,
 * el porcentaje de votos y el número de votos.
 */
public class Resultat {
    private Municipi municipi;
    private Partit partit;
    private double percentatge;
    private long vots;

    /**
     * Constructor de la clase Resultat.
     *
     * @param municipi    El municipio al que se refiere el resultado.
     * @param partit      El partido al que se refiere el resultado.
     * @param percentatge El porcentaje de votos del partido en el municipio.
     * @param vots        El número de votos del partido en el municipio.
     */
    public Resultat(Municipi municipi, Partit partit, double percentatge, long vots) {
        this.municipi = municipi;
        this.partit = partit;
        this.percentatge = percentatge;
        this.vots = vots;
    }

    /**
     * Obtiene el municipio asociado al resultado.
     *
     * @return El municipio asociado al resultado.
     */
    public Municipi getMunicipi() {
        return municipi;
    }

    /**
     * Establece el municipio asociado al resultado.
     *
     * @param municipi El municipio asociado al resultado.
     */
    public void setMunicipi(Municipi municipi) {
        this.municipi = municipi;
    }

    /**
     * Obtiene el partido asociado al resultado.
     *
     * @return El partido asociado al resultado.
     */
    public Partit getPartit() {
        return partit;
    }

    /**
     * Establece el partido asociado al resultado.
     *
     * @param partit El partido asociado al resultado.
     */
    public void setPartit(Partit partit) {
        this.partit = partit;
    }

    /**
     * Obtiene el porcentaje de votos del partido en el municipio.
     *
     * @return El porcentaje de votos del partido en el municipio.
     */
    public double getPercentatge() {
        return percentatge;
    }

    /**
     * Establece el porcentaje de votos del partido en el municipio.
     *
     * @param percentatge El porcentaje de votos del partido en el municipio.
     */
    public void setPercentatge(double percentatge) {
        this.percentatge = percentatge;
    }

    /**
     * Obtiene el número de votos del partido en el municipio.
     *
     * @return El número de votos del partido en el municipio.
     */
    public long getVots() {
        return vots;
    }

    /**
     * Establece el número de votos del partido en el municipio.
     *
     * @param vots El número de votos del partido en el municipio.
     */
    public void setVots(long vots) {
        this.vots = vots;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Resultat.
     *
     * @return Una cadena que representa el objeto Resultat.
     */
    @Override
    public String toString() {
        return "Resultats{" +
                "refMunicipi='" + municipi + '\'' +
                ", refPartit='" + partit + '\'' +
                ", percentatge=" + percentatge +
                ", vots=" + vots +
                '}';
    }

    /**
     * Indica si el objeto Resultat es igual a otro objeto dado.
     *
     * @param obj El objeto con el que se compara.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Resultat other = (Resultat) obj;
        return Objects.equals(municipi, other.municipi) &&
               Objects.equals(partit, other.partit);
    }

    /**
     * Devuelve el valor del código hash para el objeto Resultat.
     *
     * @return El valor del código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(municipi, partit, percentatge, vots);
    }
}

