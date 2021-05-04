 
package com.app;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExceptionTranslator {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		// create a JdbcTemplate and set data source
		this.jdbcTemplate = new JdbcTemplate();
		this.jdbcTemplate.setDataSource(dataSource);
		// create a custom translator and set the DataSource for the default
		// translation lookup
		CustomSQLErrorCodesTranslator tr = new CustomSQLErrorCodesTranslator();
		tr.setDataSource(dataSource);
		this.jdbcTemplate.setExceptionTranslator(tr);
	}

	public void updateRecords() {
		// use the prepared JdbcTemplate for this update
		  String queryData = "insert into mydb.UserAccounts values(110, 'Sunil',1230.35)";
		  
		this.jdbcTemplate.execute(queryData);
	}
}
