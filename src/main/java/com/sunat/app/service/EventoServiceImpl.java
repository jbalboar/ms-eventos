package com.sunat.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunat.app.entities.Evento;
import com.sunat.app.repository.EventoRepository;

@Service
public class EventoServiceImpl implements IEventoService {

	private final EventoRepository eventoRepository;
	
	public EventoServiceImpl(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}
	
	@Override
	public List<Evento> listarTodos() {
		return eventoRepository.findAll();
	}

	@Override
	public Optional<Evento> obtenerPorId(Long id) {		
		return eventoRepository.findById(id);
	}

	@Override
	public Evento guardar(Evento evento) {
		if (eventoRepository.existsByNombre(evento.getNombre())) {
			throw new IllegalArgumentException("El evento ya existe con ese nombre.");
		}
		return eventoRepository.save(evento);
	}

	@Override
	public Evento actualizar(Long id, Evento eventoActualizado) {
		return eventoRepository.findById(id).map(evento -> {
			evento.setNombre(eventoActualizado.getNombre());
			evento.setDescripcion(eventoActualizado.getDescripcion());
			evento.setUbicacion(eventoActualizado.getUbicacion());
			evento.setFecha(eventoActualizado.getFecha());
			evento.setCapacidad(eventoActualizado.getCapacidad());
			return eventoRepository.save(evento);
		}).orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
	}

	@Override
	public void eliminar(Long id) {
		eventoRepository.deleteById(id);
	}

}
