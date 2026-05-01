package microservice.donaciones.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import microservice.donaciones.model.Necesidad;
import microservice.donaciones.repository.NecesidadRepository;

@Service
@Transactional
public class NecesidadService {

    @Autowired
    private NecesidadRepository necesidadRepository;

    // Obtener todas
    public List<Necesidad> obtenerNecesidades() {
        return necesidadRepository.findAll();
    }

    // Obtener por ID
    public Necesidad obtenerNecesidadPorId(Integer id) {
        return necesidadRepository.findById(id).orElse(null);
    }

    // Guardar
    public Necesidad guardarNecesidad(Necesidad necesidad) {
        necesidad.setFechaReporte(LocalDateTime.now());
        return necesidadRepository.save(necesidad);
    }

    // Actualizar completa (PUT)
    public Necesidad actualizarNecesidad(Integer id, Necesidad necesidad) {
        Necesidad existente = necesidadRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setTipoRecursoId(necesidad.getTipoRecursoId());
            existente.setCantidad(necesidad.getCantidad());
            existente.setDescripcion(necesidad.getDescripcion());
            existente.setLatitud(necesidad.getLatitud());
            existente.setLongitud(necesidad.getLongitud());
            existente.setEstado(necesidad.getEstado());
            existente.setReportadoPorUid(necesidad.getReportadoPorUid());
            existente.setFechaReporte(necesidad.getFechaReporte());

            return necesidadRepository.save(existente);
        }

        return null;
    }

    // Actualizar parcial (PATCH)
    public Necesidad actualizarNecesidadParcial(Integer id, Necesidad necesidad) {
        Necesidad existente = necesidadRepository.findById(id).orElse(null);

        if (existente != null) {
            if (necesidad.getTipoRecursoId() != null)
                existente.setTipoRecursoId(necesidad.getTipoRecursoId());

            if (necesidad.getCantidad() != null)
                existente.setCantidad(necesidad.getCantidad());

            if (necesidad.getDescripcion() != null)
                existente.setDescripcion(necesidad.getDescripcion());

            if (necesidad.getLatitud() != null)
                existente.setLatitud(necesidad.getLatitud());

            if (necesidad.getLongitud() != null)
                existente.setLongitud(necesidad.getLongitud());

            if (necesidad.getEstado() != null)
                existente.setEstado(necesidad.getEstado());

            if (necesidad.getReportadoPorUid() != null)
                existente.setReportadoPorUid(necesidad.getReportadoPorUid());

            if (necesidad.getFechaReporte() != null)
                existente.setFechaReporte(necesidad.getFechaReporte());

            return necesidadRepository.save(existente);
        }

        return null;
    }

    // Eliminar
    public void eliminarNecesidad(Integer id) {
        Necesidad necesidad = necesidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Necesidad no encontrada"));

        necesidadRepository.delete(necesidad);
    }

    // Filtros

    public List<Necesidad> obtenerPorEstado(Integer estadoId) {
        return necesidadRepository.findByEstadoId(estadoId);
    }

    public List<Necesidad> obtenerPorTipoRecurso(Integer tipoId) {
        return necesidadRepository.findByTipoRecursoId(tipoId);
    }

    public List<Necesidad> obtenerPorReportante(String uid) {
        return necesidadRepository.findByReportadoPorUid(uid);
    }
}