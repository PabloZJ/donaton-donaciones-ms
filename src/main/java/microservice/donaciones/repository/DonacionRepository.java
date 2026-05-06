package microservice.donaciones.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.donaciones.model.Donacion;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Integer> {

    List<Donacion> findByEstadoId(Integer estadoId);

    List<Donacion> findByDonanteUid(String donanteUid);

    List<Donacion> findByCentroAcopioId(Integer centroAcopioId);

    List<Donacion> findByTipoRecursoId(Integer tipoRecursoId);

    List<Donacion> findByEstadoIdAndCentroAcopioId(Integer estadoId, Integer centroAcopioId);

    List<Donacion> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Donacion> findByDonanteUidOrderByFechaRegistroDesc(String donanteUid);
}