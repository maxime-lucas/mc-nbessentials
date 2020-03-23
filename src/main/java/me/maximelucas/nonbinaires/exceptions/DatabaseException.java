package me.maximelucas.nonbinaires.exceptions;

import java.sql.SQLException;

public class DatabaseException extends Exception {
	private static final long serialVersionUID = 2320062203231436213L;
	public DatabaseException(SQLException e) {
		super(e);
	}
}
