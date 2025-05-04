package com.sunat.app.service;

import java.util.List;
import java.util.Optional;

import com.sunat.app.entities.Evento;

public interface IEventoService {
	public List<Evento> listarTodos() ;
	public Optional<Evento> obtenerPorId(Long id);
	public Evento guardar(Evento producto);
	public Evento actualizar(Long id, Evento eventoActualizado);
	public void eliminar(Long id);
}
