package microservice.donaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.donaciones.model.EstadoDonacion;

@Repository
public interface EstadoDonacionRepository extends JpaRepository<EstadoDonacion, Integer> {
    EstadoDonacion findByNombre(String nombre);
}