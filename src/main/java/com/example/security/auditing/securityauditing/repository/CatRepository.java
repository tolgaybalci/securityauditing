package com.example.security.auditing.securityauditing.repository;

import com.example.security.auditing.securityauditing.domain.Cat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
}
