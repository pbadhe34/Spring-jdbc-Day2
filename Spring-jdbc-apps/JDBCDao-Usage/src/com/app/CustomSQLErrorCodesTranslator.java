package com.app;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

public class CustomSQLErrorCodesTranslator extends
		SQLErrorCodeSQLExceptionTranslator {
	protected DataAccessException customTranslate(String task, String sql,
			SQLException sqlex) {
		if (sqlex.getErrorCode() == -12345) {
			return new DeadlockLoserDataAccessException(task, sqlex);
		}
		return null;
	}

}