package ${packageName}.util;

/**
 * Service层公用的Exception.
 * @author ${author}
 * @date ${.now?string("yyyy-MM-dd zzzz")}
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}