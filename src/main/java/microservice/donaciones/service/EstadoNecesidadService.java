package microservice.donaciones.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import microservice.donaciones.model.EstadoNecesidad;
import microservice.donaciones.repository.EstadoNecesidadRepository;

@Service
@Transactional
public class EstadoNecesidadService {

    @Autowired
    private EstadoNecesidadRepository estadoNecesidadRepository;

    // Obtener todos
    public List<EstadoNecesidad> obtenerEstados() {
        return estadoNecesidadRepository.findAll();
    }

    // Obtener por ID
    public EstadoNecesidad obtenerEstadoPorId(Integer id) {
        return estadoNecesidadRepository.findById(id).orElse(null);
    }

    // Guardar
    public EstadoNecesidad guardarEstado(EstadoNecesidad estado) {
        return estadoNecesidadRepository.save(estado);
    }

    // Actualizar completa (PUT)
    public EstadoNecesidad actualizarEstado(Integer id, EstadoNecesidad estado) {
        EstadoNecesidad existente = estadoNecesidadRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(estado.getNombre());
            return estadoNecesidadRepository.save(existente);
        }

        return null;
    }

    // Actualizar parcial (PATCH)
    public EstadoNecesidad actualizarEstadoParcial(Integer id, EstadoNecesidad estado) {
        EstadoNecesidad existente = estadoNecesidadRepository.findById(id).orElse(null);

        if (existente != null) {
            if (estado.getNombre() != null)
                existente.setNombre(estado.getNombre());

            return estadoNecesidadRepository.save(existente);
        }

        return null;
    }

    // Eliminar
    public void eliminarEstado(Integer id) {
        EstadoNecesidad estado = estadoNecesidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        estadoNecesidadRepository.delete(estado);
    }

    // Filtro

    public EstadoNecesidad obtenerPorNombre(String nombre) {
        return estadoNecesidadRepository.findByNombre(nombre);
    }
}