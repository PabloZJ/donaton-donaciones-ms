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
import microservice.donaciones.model.EstadoDonacion;
import microservice.donaciones.service.EstadoDonacionService;

@RestController
@RequestMapping("/api/estados-donacion")
public class EstadoDonacionController {

    @Autowired
    private EstadoDonacionService estadoDonacionService;

    @GetMapping
    public ResponseEntity<List<EstadoDonacion>> obtenerTodos() {
        List<EstadoDonacion> lista = estadoDonacionService.obtenerEstados();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDonacion> obtenerPorId(@PathVariable Integer id) {
        EstadoDonacion estado = estadoDonacionService.obtenerEstadoPorId(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoDonacion> crear(@RequestBody EstadoDonacion estado) {
        EstadoDonacion creado = estadoDonacionService.guardarEstado(estado);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDonacion> actualizar(@PathVariable Integer id, @RequestBody EstadoDonacion estado) {
        EstadoDonacion actualizado = estadoDonacionService.actualizarEstado(id, estado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstadoDonacion> actualizarParcial(@PathVariable Integer id, @RequestBody EstadoDonacion estado) {
        EstadoDonacion actualizado = estadoDonacionService.actualizarEstadoParcial(id, estado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estadoDonacionService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }

    // Filtro por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EstadoDonacion> obtenerPorNombre(@PathVariable String nombre) {
        EstadoDonacion estado = estadoDonacionService.obtenerPorNombre(nombre);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }
}