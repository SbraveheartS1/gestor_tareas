package com.gestor_tareas.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestor_tareas.demo.dao.TareasDao;
import com.gestor_tareas.demo.daoimpl.TareasDaoImpl;
import com.gestor_tareas.demo.models.Tareas;
import com.gestor_tareas.demo.service.TareasService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TareasServiceImpl implements TareasService{

	@Autowired
	private TareasDao dao;
	
	@Override
	public Mono<Tareas> save(Tareas obj) {
		return dao.save(obj);
	}

	@Override
	public Flux<Tareas> findAll() {
		return dao.findAll();
	}

	@Override
	public Mono<Tareas> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public Mono<Void> delete(String id) {
		return dao.deleteById(id);
	}

}
