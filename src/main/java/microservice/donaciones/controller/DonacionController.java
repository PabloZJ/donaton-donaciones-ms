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

import microservice.donaciones.model.Donacion;
import microservice.donaciones.service.DonacionService;

@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    @Autowired
    private DonacionService service;

    @GetMapping
    public ResponseEntity<List<Donacion>> obtenerTodas() {
        List<Donacion> lista = service.obtenerDonaciones();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> obtenerPorId(@PathVariable Integer id) {
        Donacion d = service.obtenerDonacionPorId(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @PostMapping
    public ResponseEntity<Donacion> crear(@RequestBody Donacion donacion) {
        Donacion creada = service.guardarDonacion(donacion);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donacion> actualizar(@PathVariable Integer id, @RequestBody Donacion donacion) {
        Donacion actualizada = service.actualizarDonacion(id, donacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Donacion> actualizarParcial(@PathVariable Integer id, @RequestBody Donacion donacion) {
        Donacion actualizada = service.actualizarDonacionParcial(id, donacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminarDonacion(id);
        return ResponseEntity.noContent().build();
    }

    //FILTROS

    @GetMapping("/estado/{estadoId}")
    public ResponseEntity<List<Donacion>> porEstado(@PathVariable Integer estadoId) {
        List<Donacion> lista = service.obtenerPorEstado(estadoId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/donante/{uid}")
    public ResponseEntity<List<Donacion>> porDonante(@PathVariable String uid) {
        List<Donacion> lista = service.obtenerPorDonante(uid);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/centro/{centroId}")
    public ResponseEntity<List<Donacion>> porCentro(@PathVariable Integer centroId) {
        List<Donacion> lista = service.obtenerPorCentro(centroId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/recurso/{tipoId}")
    public ResponseEntity<List<Donacion>> porTipoRecurso(@PathVariable Integer tipoId) {
        List<Donacion> lista = service.obtenerPorTipoRecurso(tipoId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estado/{estadoId}/centro/{centroId}")
    public ResponseEntity<List<Donacion>> porEstadoYCentro(
            @PathVariable Integer estadoId,
            @PathVariable Integer centroId) {

        List<Donacion> lista = service.obtenerPorEstadoYCentro(estadoId, centroId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
}