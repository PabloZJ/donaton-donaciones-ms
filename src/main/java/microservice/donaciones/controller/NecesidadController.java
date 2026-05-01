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
import microservice.donaciones.model.Necesidad;
import microservice.donaciones.service.NecesidadService;

@RestController
@RequestMapping("/api/necesidades")
public class NecesidadController {

    @Autowired
    private NecesidadService necesidadService;

    @GetMapping
    public ResponseEntity<List<Necesidad>> obtenerTodas() {
        List<Necesidad> lista = necesidadService.obtenerNecesidades();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Necesidad> obtenerPorId(@PathVariable Integer id) {
        Necesidad necesidad = necesidadService.obtenerNecesidadPorId(id);
        if (necesidad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(necesidad);
    }

    @PostMapping
    public ResponseEntity<Necesidad> crear(@RequestBody Necesidad necesidad) {
        Necesidad creada = necesidadService.guardarNecesidad(necesidad);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Necesidad> actualizar(@PathVariable Integer id, @RequestBody Necesidad necesidad) {
        Necesidad actualizada = necesidadService.actualizarNecesidad(id, necesidad);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Necesidad> actualizarParcial(@PathVariable Integer id, @RequestBody Necesidad necesidad) {
        Necesidad actualizada = necesidadService.actualizarNecesidadParcial(id, necesidad);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        necesidadService.eliminarNecesidad(id);
        return ResponseEntity.noContent().build();
    }

    // Filtros

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<Necesidad>> porEstado(@PathVariable Integer estadoId) {
        List<Necesidad> lista = necesidadService.obtenerPorEstado(estadoId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/recurso/{tipoId}")
    public ResponseEntity<List<Necesidad>> porTipo(@PathVariable Integer tipoId) {
        List<Necesidad> lista = necesidadService.obtenerPorTipoRecurso(tipoId);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/reportante/{uid}")
    public ResponseEntity<List<Necesidad>> porReportante(@PathVariable String uid) {
        List<Necesidad> lista = necesidadService.obtenerPorReportante(uid);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }
}