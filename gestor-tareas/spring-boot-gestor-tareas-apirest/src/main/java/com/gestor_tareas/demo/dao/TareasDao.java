package com.gestor_tareas.demo.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.gestor_tareas.demo.models.Tareas;

public interface TareasDao extends ReactiveMongoRepository<Tareas,String>{
}
