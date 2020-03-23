package me.maximelucas.nonbinaires.home.exceptions;

public class HomeAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 5189841201955446336L;
	private String homeName;

	public HomeAlreadyExistsException(String pHomeName) {
		this.homeName = pHomeName;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

}
