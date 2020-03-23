package me.maximelucas.nonbinaires.home.exceptions;

public class NoSuchHomeException extends Exception {

	private static final long serialVersionUID = 1L;
	private String homeName;

	public NoSuchHomeException(String pHomeName) {
		this.homeName = pHomeName;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

}
