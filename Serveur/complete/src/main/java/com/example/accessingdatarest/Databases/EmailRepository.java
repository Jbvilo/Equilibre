
package com.example.accessingdatarest.Databases;

import java.util.List;

import com.example.accessingdatarest.Models.Email;
import com.example.accessingdatarest.Models.Horse;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "emailrepo", path = "emailrepo")
public interface EmailRepository extends PagingAndSortingRepository<Email, Long> {
    List<String> findByfirstName(@Param("firstName") String firstName);
}













