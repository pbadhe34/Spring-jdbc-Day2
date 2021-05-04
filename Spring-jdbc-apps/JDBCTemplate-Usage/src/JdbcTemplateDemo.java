 
import org.springframework.jdbc.core.*; 
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.context.*;
import org.springframework.context.support.*;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import java.math.*;


public class JdbcTemplateDemo 
{
	private SingleConnectionDataSource ds;
	 

	/*NamedParameterJdbcTemplate is another Template class with a basic set of JDBC operations, 
	allowing the use of named parameters rather than traditional '?' placeholders.
	JDBC connections with support for named parameters
	This class delegates to a wrapped JdbcTemplate once the substitution from named parameters to JDBC style '?' placeholders is done at execution time. It also allows for expanding a List of values to the appropriate number of placeholders.
    This JDBC template class enables you to perform queries where values are bound to named parameters in SQL, rather than indexed parameters. 
	The underlying JdbcTemplate is exposed to allow for convenient access to the traditional JdbcTemplate methods.	 
	*/
	
	/*
	 * CREATE TABLE mydb.emp (empno int,ename varchar(255)); insert into mydb.emp
	 * values(1,'Baba'); insert into mydb.emp values(2,'Kishor'); insert into
	 * mydb.emp values(3,'Mohan'); insert into mydb.emp values(4,'Jota'); insert
	 * into mydb.emp values(5,'Pyare');
	 */
	
	protected void setUp() throws Exception
	{
		
		//Declarative configurations for dataSource loaded from beans.xml
		
		/*
		 * ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml"); 
		 * ds  = (SingleConnectionDataSource)ctx.getBean("MySource");
		 */
				
         
          
       //Programmetic congiguration
	    /*
        ds = new SingleConnectionDataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@Prakash:1521:oracle");
        ds.setUsername("scott");
        ds.setPassword("tiger"); 
		*/
		 
		        /* DS config for HSQL DB
				  ds = new SingleConnectionDataSource();
		          ds.setDriverClassName("org.hsqldb.jdbcDriver");
		          ds.setUrl("jdbc:hsqldb:hsql://localhost:9001");
		          ds.setUsername("sa");
		          ds.setPassword("");
				  */
		     }


    public static void main(String []args)throws Exception
	{

 	 JdbcTemplateDemo dt = new JdbcTemplateDemo();	 
	 //dt.executeQuery();
		/*
		 * dt.testIntQuery(); dt.testQueryWithParameter();
		 * dt.testQueryReturningString(); dt.testQueryForList();
		 * 
		 * dt.testQueryForMap(); dt.testUpdate(); dt.PreparedStatementCreatorQuery(6);
		 * dt.PreparedStatementSetterQuery(5); dt.testQueryForRowset();
		 */
	 dt.testTransaction();

	}	

   public void executeQuery()
	{
	    System.out.println("Running the simple execute query..");		

        //Programmetic congiguration
        //JdbcTemplate jt = new JdbcTemplate(ds);

		//Declarative configurations for JdbcTemplate loaded from beans.xml        
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");
		 
		 BeanFactory ctx = new XmlBeanFactory(new FileSystemResource("beans.xml")); 
        
         JdbcTemplate jt = (JdbcTemplate)ctx.getBean("MyTemplate");	
         
			

         jt.execute("insert into emp (empno, ename) values(18, 'JohnDcosta')");  	
         System.out.println("The records have been inserted..");		
    }

    public void testIntQuery()
	{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        
        String queryData = "select count(*) from emp";        
        
        ResultSetExtractor<Integer> out = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				int result =  rs.getInt(1);	
				System.out.println("The number of emp records are "+result);
				return result;
			}
        	
        };
        
        Object []args= new Object [0];//empty parameters        
        jt.query(queryData, args, out );   
        
        
    }
	

    public void testQueryWithParameter()
	{
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Object[] parameters = new Object[] {"M"};
        
        String queryData = "select count(*) from emp where ename > ?";   //place holder in query  
        
		/*
		 * ResultSetExtractor<Integer> out = new ResultSetExtractor<Integer>() {
		 * 
		 * @Override public Integer extractData(ResultSet rs) throws SQLException,
		 * DataAccessException { rs.next(); int result = rs.getInt(1);
		 * System.out.println("The number of emp records matching with M are "+result);
		 * return result; }
		 * 
		 * }; //Query parameters passed to query jt.query(queryData, parameters, out );
		 */
        
        Object result = jt.queryForObject(queryData, parameters, Integer.class);
        System.out.println("The number of emp records matching  are "+result);
		          
    }

    public void testQueryReturningString ()
	{
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Object[] parameters = new Object[] {new Integer(4)};
        Object oname = jt.queryForObject("select ename from emp where empno = ?", parameters, String.class);
		System.out.println("The string return value is  "+(String)oname);
         
    }

    public void testQueryForList() 
	{
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Object[] parameters = new Object[] {new Integer(3)};
		 
        List list = jt.queryForList("select ename from emp where empno > ?", parameters,String.class);
		int sz = list.size();
		System.out.println("The number of high emp persons are "+sz);

		for(int i = 0; i <sz; i++)
		{
           System.out.println("The ename with high number  is  "+(String)list.get(i));
   		}
		 
        
		list = jt.queryForList("select empno,ename from emp where empno > ?", parameters);
		//Uese queryForList for multiple records containing multiple columns
		sz = list.size();
		System.out.println("The high num records are here..");
		for(int i = 0; i <sz; i++)
		{
           //System.out.println(list.get(i));
		  Map record =(Map)list.get(i);//Each row is represented by a map object having coulmn names as keys
		  //System.out.println("The map returned for single record is  "+record);
		  //System.out.println("The map columns size is  "+record.size());		
		 // System.out.println("The map key is  "+(record.keySet()));
		  System.out.println("The Emp No  : "
		  + record.get("empno") + " and EmpName   :  "
		  + record.get("ename"));

   		}	 
       

        List l = jt.queryForList("select empno, ename from emp");
        System.out.println(l);
		
	}

    public void testQueryForMap()
	 {
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
		System.out.println("Starting the query for map..\n" );
		Object[] parameters = new Object[] {new Integer(2)};
	
        // Map map = jt.queryForMap("select count(*) from emp");
		//Use queryForMap when the query result is a single row query and the resulting row will be mapped to a Map 
		//(one entry for each column, using the column name as the key) 

		  //Eted single record here

	    Map map = jt.queryForMap("select empno,ename from emp where empno = ?",parameters);
		 
        System.out.println("The map returned is  "+map);
		System.out.println("The map returned size is  "+map.size());		
		System.out.println("The map key is  "+(map.keySet()));
		System.out.println("The Record is  : "+ map.get("empno") + " and EmpName   :  "+ map.get("ename"));

     }
    public void testUpdate() 
	{
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
         
        int x = jt.update("insert into emp (empno, ename) values(134, 'Jadoo')");
         
        x = jt.update("update emp set ename = 'Raju' where empno = ?",new Object[] {new Integer(2)});
         
        x = jt.update("delete from emp where empno = 1");
        System.out.println(x + " row(s) deleted.");       
         
    }

    public void PreparedStatementCreatorQuery(final int no) 
	{
		System.out.println("Starting Prepared Satement Creator.....\n");	
		ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
         
        JdbcTemplate jt = new JdbcTemplate(ds);
	    final String sql = "select empno, ename from emp where empno < ?";
		
        List results = jt.query(
					new PreparedStatementCreator() //implement PreparedStatementCreator locally
					{
						public PreparedStatement createPreparedStatement(Connection con)
								throws SQLException 
						{
							PreparedStatement ps = con.prepareStatement(sql);
							System.out.println("Creating the  PreparedStatement   ");
					
							ps.setInt(1, no);
							return ps;
						}
					},
					new RowMapper() 
					{
						 /*
						 The framework will create a list object
						 and call this method for each record/row and add 
						 the return value to the list and return the list
						 */
						public Object mapRow(ResultSet rs,int rowNum)throws SQLException
						{
						 System.out.println("Current Row number is  "+rowNum);
						 String str = rs.getString("ename");
						 System.out.println("Current EName is  "+str);						
						 System.out.println("Current ResultSet is  "+rs);
						 return str;
						}                     
					}
        );
			 System.out.println("The result returned is  "+results.size());	
			 System.out.println("The result first record returned is  "+results.get(0));			
         
         
    }

     

    public void PreparedStatementSetterQuery(final int id)
	{
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        final String sql = "select empno, ename from emp where empno < ?";
	     
        List results = (List)jt.query(sql,
                new PreparedStatementSetter()//Implement PreparedStatementSetter
		       {
                    public void setValues(PreparedStatement ps)
                            throws SQLException 
					{
                        ps.setInt(1, id);
                    }
                },
                new ResultSetExtractor() 
				{
					/*
						 The framework will call this method once 
						 and return this list object when it retrieves the 
						 entire resultset					 
						 */
                    List names = new ArrayList();

                    public Object extractData(ResultSet rs)
                    throws SQLException,DataAccessException 
					{
						System.out.println("Extracting ....");
						while(rs.next())
                          names.add(rs.getString("ename"));
						return names;
                    }                    
                }
        ); 
       System.out.println("The results size returned is  "+results.size());	
	   System.out.println("The result records returned are  "+results);			
         
    }     

    public void testQueryForRowset()
	{
    	ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");        
        ds = (SingleConnectionDataSource)ctx.getBean("MySource");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Object[] parameters = new Object[] {new Integer(1)};
        SqlRowSet srs = jt.queryForRowSet("select empno, ename from emp where empno > ?", parameters);
        System.out.println("The sqlRowset object is  "+srs);
        int rowCount = 0;//Used like an Iterator
        while (srs.next()) 
		{
             System.out.println(srs.getString("empno") + " - " + srs.getString("ename"));
             rowCount++;
        }
         
    }


/*
SqlRowSet is Mirror interface for javax.sql.RowSet,
representing disconnected java.sql.ResultSet data.

A RowSet object may make a connection with a data source 
and maintain that connection throughout its life cycle,
in which case it is called a connected rowset.
A rowset may also make a connection with a data source,
get data from it, and then close the connection. 
Such a rowset is called a disconnected rowset.
A disconnected rowset may make changes to its data while 
it is disconnected and then send the changes back to the 
original source of the data, but it must reestablish
a connection to do so. 
*/

public void testTransaction() throws SQLException
	{
		/*
	    create table mydb.UserAccounts (Userid int not null primary key,Name varchar(20) ,Balance float);

		insert into mydb.UserAccounts values(110, 'Sunil',1230.35);
            
        select * from mydb.UserAccounts where Userid=110;

		delete from  mydb.UserAccounts where Userid=110;    


		insert into mydb.emp (empno, ename) values(110, 'Sunil');
		
		insert into mydb.UserAccounts values(111, 'Sunil',1230.35);
		insert into mydb.emp (empno, ename) values(112, 'Sunil');
		select * from mydb.UserAccounts where Userid=112;
		
		insert into mydb.emp (empno, ename) values(113, 'Viju');
		insert into mydb.UserAccounts values(113, 'Sunil',1230.35);
		 select * from mydb.emp where empno=113;
		*/

	   BeanFactory ctx = new XmlBeanFactory(new FileSystemResource("beans.xml"));
	   SingleConnectionDataSource ds = (SingleConnectionDataSource)ctx.getBean("MySource");
	   ds.getConnection().setAutoCommit(false);
	   JdbcTemplate jt = new JdbcTemplate(ds);
	   //JdbcTemplate jt = (JdbcTemplate)ctx.getBean("MyTemplate");	
	   
        try {
       

		int id = 113;

		Object[] parameters = new Object[] {new Integer(id)};	
        
	    Map map = jt.queryForMap("select empno,ename from mydb.emp where empno = ?",parameters);
		 
		String name = (String)map.get("ename");	
		System.out.println("The emp record found with "+name);  
		System.out.println("Now delete the emp record and insert the same into UserAccounts table\n");
		int rows = jt.update("delete from mydb.emp where empno = "+id);
        System.out.println(rows + " row(s) deleted from emp table.");  

		int rowsUpdated = jt.update("update mydb.UserAccounts set Balance = " + 234.0 +", Name ='"+name+"' where Userid = ?",new Object[] {new Integer(id)});
	 

	   // int rowsUpdated = jt.update("insert into UserAccounts values("+id+",'"+name+"',"+2345+")");
		System.out.println(rowsUpdated + " row(s) inserted."); 
		ds.getConnection().commit();
		System.out.println("The transaction is commited here");
        }
        catch(Exception e) {
        	System.out.println("Errpr in gtrsaction is "+e.getMessage());
        	System.out.println("The transaction is rolled back");
        	ds.getConnection().rollback();
        }
		 
         
         
    }
}

