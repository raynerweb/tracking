package br.com.raynerweb.tracking.repository;

import br.com.raynerweb.tracking.entity.TrackingSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingSensorRepository extends JpaRepository<TrackingSensorEntity, Long> {
}
