 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.app.CustomSQLErrorCodesTranslator;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import org.springframework.jdbc.core.support.JdbcDaoSupport; 
import java.sql.*;
import javax.sql.*;


public class TestJdbcDao extends JdbcDaoSupport 
{
	public static void main(String []args)throws Exception
	{
	  

		TestJdbcDao tdo = new TestJdbcDao();
		tdo.getEmpCount();
 
      
	  
	  
	}	

   public int getEmpCount()
	{
	    System.out.println("Running the  query..");

	    //Built-in JdbcTemplate object
		JdbcTemplate jt = getJdbcTemplate();//called from Base class

		String queryData = "select count(*) from emp";   
	      
		//int count = jt.queryForInt("select count(*) from emp");
		Integer result = jt.queryForObject(queryData, null, Integer.class);
		     
		return result;      
    }    

   public void testDao() {
	   String queryData = "insert into mydb.UserAccounts values(110, 'Sunil',1230.35)";
		
   
	   ApplicationContext ctx = new FileSystemXmlApplicationContext(
		        "mysql-dataAccess.xml");        
		         
			  TestJdbcDao dao = (TestJdbcDao)ctx.getBean("dao"); 
			  
			  CustomSQLErrorCodesTranslator ctr = new CustomSQLErrorCodesTranslator();
				ctr.setDataSource(dao.getDataSource());
			 
			  
			  JdbcTemplate jt = dao.getJdbcTemplate();//called from Base class
			  jt.setExceptionTranslator(ctr);
			  
			  jt.execute(queryData);
			   
			  
			 
			  
		   	//MySQLIntegrityConstraintViolationException
			 
			/*
			 * try { jt.execute(queryData); }
			 * 
			 * catch(Exception e1) {
			 * System.out.println("The DataAccessException   is  "+e1.getClass().getName());
			 * // DataAccessException dae= ctr.translate("insert", queryData, e1);
			 * System.out.println("The DataAccessException   is  "+dae);
			 * 
			 * 
			 * }
			 */
}
}
