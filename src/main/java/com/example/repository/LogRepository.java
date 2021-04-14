package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LogModel;

@Repository
public interface LogRepository extends CrudRepository<LogModel, String>{

	List<LogModel> findByDate(String date);

}
