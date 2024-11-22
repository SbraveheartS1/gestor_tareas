package com.gestor_tareas.demo.service;

import com.gestor_tareas.demo.models.Tareas;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TareasService {

	public Mono<Tareas> save(Tareas obj);
	
	public Flux<Tareas> findAll();
	
	public Mono<Tareas> findById(String id);
	
	public Mono<Void> delete(String id);
	
}
