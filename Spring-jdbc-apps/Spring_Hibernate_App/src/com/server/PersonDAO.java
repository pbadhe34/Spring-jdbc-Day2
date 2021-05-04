package com.server;

import java.util.List;

import com.server.Person;

public interface PersonDAO {

	public void save(Person p);
	
	public List<Person> list();
	
}
