package excepcion;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	protected String codi;

	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(Throwable cause) {
		super(cause);
	}
	
	public CustomException(String codi, String message) {
		super(message);
		this.codi = codi;
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public String getCodi() {
		return codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}
	
}
