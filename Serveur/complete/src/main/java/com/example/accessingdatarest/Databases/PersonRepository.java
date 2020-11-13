package com.example.accessingdatarest.Databases;

import java.util.List;

import com.example.accessingdatarest.Models.Person;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;


@CrossOrigin(origins = "http://localhost:4200", methods={RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST}) //methods=
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
	Person findByfirstName(@Param("firstName") String firstName);
	List<Person> findByphonenumber(@Param("phonenumber") String phonenumber);
	List<Person> findByLastName(@Param("name") String name);
	List<Person> findByEmail(@Param("email") String email);

}





