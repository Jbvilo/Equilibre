package com.example.accessingdatarest.Databases;

import java.util.List;

import com.example.accessingdatarest.Models.Horse;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "horse", path = "horse")
public interface HorseRepository extends PagingAndSortingRepository<Horse, Long> {
    List<Horse> findByfirstName(@Param("firstName") String firstName);
}





