package com.sunat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunat.app.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>  {

	boolean existsByNombre(String nombre);

}
