package com.gestor_tareas.demo.daoimpl;

import java.util.function.Function;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.ReactiveFluentQuery;
import org.springframework.stereotype.Repository;

import com.gestor_tareas.demo.dao.TareasDao;
import com.gestor_tareas.demo.models.Tareas;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TareasDaoImpl{
}
