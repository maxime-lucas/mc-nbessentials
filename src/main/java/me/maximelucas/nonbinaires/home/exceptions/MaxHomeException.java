package me.maximelucas.nonbinaires.home.exceptions;

public class MaxHomeException extends Exception {

	private static final long serialVersionUID = 108327177394299296L;
	private Integer maxHome;

	public MaxHomeException(Integer pMaxHome) {
		this.setMaxHome(pMaxHome);
	}

	public Integer getMaxHome() {
		return maxHome;
	}

	public void setMaxHome(Integer maxHome) {
		this.maxHome = maxHome;
	}

}
