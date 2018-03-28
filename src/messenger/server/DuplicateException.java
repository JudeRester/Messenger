package messenger.server;

public class DuplicateException extends Exception {
	public DuplicateException (String message) {
		super(message);
	}
	public DuplicateException () {
		this ("중복 오류!");
	}
}
