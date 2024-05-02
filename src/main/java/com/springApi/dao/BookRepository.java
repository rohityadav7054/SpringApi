package com.springApi.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springApi.entities.Books;

public interface BookRepository extends CrudRepository<Books, Integer>{
public  Optional<Books> findById(Integer Id);
}
