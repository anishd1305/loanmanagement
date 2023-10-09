//$Id$
package com.graychain.loanmanagement.repository.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class LoanIdGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		 String prefix = "L";
		 Integer id = null;
		 try {
		   Connection connection = session.connection();
		   Statement statement = connection.createStatement();
		   ResultSet resultSet = statement.executeQuery("select count(loan_id) from loan_dao");
		   if(resultSet.next()) {
			   id = resultSet.getInt(1) + 1;
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return prefix + id;
	}

}
