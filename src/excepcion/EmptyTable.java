package excepcion;

/**
 * La clase EmptyTable representa una excepción que se lanza cuando una tabla está vacia
 */
public class EmptyTable extends CustomException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con el codigo y el mensaje 
     *
     * @param pCodi   El código asociado al error
     * @param message El mensaje de error
     */
    public EmptyTable(String pCodi, String message) {
        super(pCodi, message);
    }

}
