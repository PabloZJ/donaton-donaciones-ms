package microservice.donaciones.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import microservice.donaciones.model.EstadoNecesidad;
import microservice.donaciones.service.EstadoNecesidadService;

@RestController
@RequestMapping("/api/estados-necesidad")
public class EstadoNecesidadController {

    @Autowired
    private EstadoNecesidadService estadoNecesidadService;

    @GetMapping
    public ResponseEntity<List<EstadoNecesidad>> obtenerTodos() {
        List<EstadoNecesidad> lista = estadoNecesidadService.obtenerEstados();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoNecesidad> obtenerPorId(@PathVariable Integer id) {
        EstadoNecesidad estado = estadoNecesidadService.obtenerEstadoPorId(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoNecesidad> crear(@RequestBody EstadoNecesidad estado) {
        EstadoNecesidad creado = estadoNecesidadService.guardarEstado(estado);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoNecesidad> actualizar(@PathVariable Integer id, @RequestBody EstadoNecesidad estado) {
        EstadoNecesidad actualizado = estadoNecesidadService.actualizarEstado(id, estado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstadoNecesidad> actualizarParcial(@PathVariable Integer id, @RequestBody EstadoNecesidad estado) {
        EstadoNecesidad actualizado = estadoNecesidadService.actualizarEstadoParcial(id, estado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estadoNecesidadService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }

    // Filtro por nombre

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstadoNecesidad> obtenerPorNombre(@PathVariable String nombre) {
        EstadoNecesidad estado = estadoNecesidadService.obtenerPorNombre(nombre);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }
}