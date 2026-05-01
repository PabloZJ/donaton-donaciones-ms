package microservice.donaciones.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import microservice.donaciones.model.Donacion;
import microservice.donaciones.repository.DonacionRepository;

@Service
@Transactional
public class DonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    //Obtener todas
    public List<Donacion> obtenerDonaciones() {
        return donacionRepository.findAll();
    }

    //Obtener por ID
    public Donacion obtenerDonacionPorId(Integer id) {
        return donacionRepository.findById(id).orElse(null);
    }

    //Guardar
    public Donacion guardarDonacion(Donacion donacion) {
        donacion.setFechaRegistro(LocalDateTime.now());
        return donacionRepository.save(donacion);
    }

    //Actualizar completa (PUT)
    public Donacion actualizarDonacion(Integer id, Donacion donacion) {
        Donacion existente = donacionRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setTipoRecursoId(donacion.getTipoRecursoId());
            existente.setCantidad(donacion.getCantidad());
            existente.setDonanteUid(donacion.getDonanteUid());
            existente.setCentroAcopioId(donacion.getCentroAcopioId());
            existente.setEstado(donacion.getEstado());
            existente.setFechaRegistro(donacion.getFechaRegistro());
            existente.setObservacion(donacion.getObservacion());

            return donacionRepository.save(existente);
        }

        return null;
    }

    //Actualizar parcial (PATCH)
    public Donacion actualizarDonacionParcial(Integer id, Donacion donacion) {
        Donacion existente = donacionRepository.findById(id).orElse(null);

        if (existente != null) {
            if (donacion.getTipoRecursoId() != null)
                existente.setTipoRecursoId(donacion.getTipoRecursoId());

            if (donacion.getCantidad() != null)
                existente.setCantidad(donacion.getCantidad());

            if (donacion.getDonanteUid() != null)
                existente.setDonanteUid(donacion.getDonanteUid());

            if (donacion.getCentroAcopioId() != null)
                existente.setCentroAcopioId(donacion.getCentroAcopioId());

            if (donacion.getEstado() != null)
                existente.setEstado(donacion.getEstado());

            if (donacion.getFechaRegistro() != null)
                existente.setFechaRegistro(donacion.getFechaRegistro());

            if (donacion.getObservacion() != null)
                existente.setObservacion(donacion.getObservacion());

            return donacionRepository.save(existente);
        }

        return null;
    }

    //Eliminar
    public void eliminarDonacion(Integer id) {
        Donacion donacion = donacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donación no encontrada"));

        donacionRepository.delete(donacion);
    }

    //Filtros

    public List<Donacion> obtenerPorEstado(Integer estadoId) {
        return donacionRepository.findByEstadoId(estadoId);
    }

    public List<Donacion> obtenerPorDonante(String uid) {
        return donacionRepository.findByDonanteUid(uid);
    }

    public List<Donacion> obtenerPorCentro(Integer centroId) {
        return donacionRepository.findByCentroAcopioId(centroId);
    }

    public List<Donacion> obtenerPorTipoRecurso(Integer tipoId) {
        return donacionRepository.findByTipoRecursoId(tipoId);
    }
}