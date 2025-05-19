package com.sunat.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunat.app.entities.Evento;
import com.sunat.app.service.IEventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {
	private final IEventoService eventoService;
	
	 @Autowired
     private Environment environment;
	
	public EventoController(IEventoService eventoService) {
		this.eventoService = eventoService;
	}
	
	 @GetMapping
     public Map<String, Object> listarTodos() {
         return Map.of(
             "POD_NAME", environment.getProperty("POD_NAME", "Unknown"),   
             "POD_ID", environment.getProperty("POD_ID", "Unkown"), 
             "SALUDO", environment.getProperty("config.saludo", "Unknown"),
             "eventos", eventoService.listarTodos());
     }
	
	/*@GetMapping
	public ResponseEntity<List<Evento>> listarTodos() {
		return ResponseEntity.ok(eventoService.listarTodos());
	}*/

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		return eventoService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Evento> crear(@RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.guardar(evento));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> actualizar(@PathVariable Long id, @RequestBody Evento evento) {
		return ResponseEntity.ok(eventoService.actualizar(id, evento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		eventoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
