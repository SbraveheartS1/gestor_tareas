package com.gestor_tareas.demo.controller;

import java.net.URI;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.WebExceptionHandler;

import com.gestor_tareas.demo.models.Tareas;
import com.gestor_tareas.demo.service.TareasService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {

	@Autowired
	private TareasService service;
	
	private static final Logger logger = LoggerFactory.getLogger(TareasController.class);
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Tareas>>> listarTodos(){
		return Mono.just(
				ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.findAll()
						.collectSortedList(Comparator.comparing(Tareas::getDescripcion))
						.flatMapMany( list -> Flux.fromIterable(list))	// .flatMapMany(Flux::fromIterable)
					)
				);
	}
	
	@PostMapping
	public Mono<ResponseEntity<HashMap<String, Object>>> crear(@Valid @RequestBody Mono<Tareas> mono){
		
		HashMap<String, Object> response = new HashMap<String,Object>();
		
		return mono.flatMap( tarea -> {
			if(tarea.getFechacreacion() == null) {
				tarea.setFechacreacion(new Date());
			}
			
			return service.save(tarea).map( p -> {   
					response.put("tareas", p);
					response.put("mensaje", "Creado con exito");
					response.put("timestamp", new Date());
					return ResponseEntity.created( URI.create("/api/tareas/".concat(p.getId())) )
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(response);
			});
		})
		.onErrorResume(t -> {
				 return Mono.just(t)
						 	.cast(WebExchangeBindException.class)
							.flatMap(e -> Mono.just(e.getFieldErrors()))
							.flatMapMany(Flux::fromIterable)
							.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
							.collectList()
							.flatMap(list -> { 
								response.put("errors", list);
								response.put("status", HttpStatus.BAD_REQUEST);
								response.put("timestamp", new Date());
								return Mono.just(ResponseEntity.badRequest().body(response));
							});
		})
		;
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Tareas>> obtenerPorId(@PathVariable String id){
		return service.findById(id).map(p -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
		return service.delete(id).then( 
				Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Tareas>> actualizar(@Valid @RequestBody Tareas obj, @PathVariable String id){
		return service.findById(id).flatMap( p -> {
			p.setCompletada(obj.getCompletada());
			p.setDescripcion(obj.getDescripcion());
			p.setFechavencimiento(obj.getFechavencimiento());
			p.setTitulo(obj.getTitulo());	
			return service.save(obj);
		}).map( p -> ResponseEntity
				.created( URI.create("api/tareas/".concat(id)))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p))
		  .defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
