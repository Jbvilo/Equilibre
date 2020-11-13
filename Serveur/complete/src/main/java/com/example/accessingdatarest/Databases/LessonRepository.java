package com.example.accessingdatarest.Databases;

import com.example.accessingdatarest.Models.Lesson;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "lesson", path = "lesson")
public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {
    Lesson findByMoniteur(@Param("moniteur") String moniteur);

    
}