package microservice.donaciones.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.donaciones.model.Necesidad;

@Repository
public interface NecesidadRepository extends JpaRepository<Necesidad, Integer> {

    List<Necesidad> findByEstadoId(Integer estadoId);

    List<Necesidad> findByTipoRecursoId(Integer tipoRecursoId);

    List<Necesidad> findByReportadoPorUid(String uid);
}