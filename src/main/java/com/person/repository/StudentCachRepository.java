package com.person.repository;


import com.person.entity.StudentCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCachRepository extends CrudRepository<StudentCache,Integer> {
}
