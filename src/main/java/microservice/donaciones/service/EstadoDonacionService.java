package microservice.donaciones.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import microservice.donaciones.model.EstadoDonacion;
import microservice.donaciones.repository.EstadoDonacionRepository;

@Service
@Transactional
public class EstadoDonacionService {

    @Autowired
    private EstadoDonacionRepository estadoDonacionRepository;

    // Obtener todos
    public List<EstadoDonacion> obtenerEstados() {
        return estadoDonacionRepository.findAll();
    }

    // Obtener por ID
    public EstadoDonacion obtenerEstadoPorId(Integer id) {
        return estadoDonacionRepository.findById(id).orElse(null);
    }

    // Guardar
    public EstadoDonacion guardarEstado(EstadoDonacion estado) {
        return estadoDonacionRepository.save(estado);
    }

    // Actualizar completa (PUT)
    public EstadoDonacion actualizarEstado(Integer id, EstadoDonacion estado) {
        EstadoDonacion existente = estadoDonacionRepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(estado.getNombre());
            return estadoDonacionRepository.save(existente);
        }

        return null;
    }

    // Actualizar parcial (PATCH)
    public EstadoDonacion actualizarEstadoParcial(Integer id, EstadoDonacion estado) {
        EstadoDonacion existente = estadoDonacionRepository.findById(id).orElse(null);

        if (existente != null) {
            if (estado.getNombre() != null)
                existente.setNombre(estado.getNombre());

            return estadoDonacionRepository.save(existente);
        }

        return null;
    }

    // Eliminar
    public void eliminarEstado(Integer id) {
        EstadoDonacion estado = estadoDonacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        estadoDonacionRepository.delete(estado);
    }

    // Filtro

    public EstadoDonacion obtenerPorNombre(String nombre) {
        return estadoDonacionRepository.findByNombre(nombre);
    }
}