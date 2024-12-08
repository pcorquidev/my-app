package tecnical.test.paul.my_app.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tecnical.test.paul.my_app.domain.PuntoDeAccesoWifi;

import java.util.Optional;


public interface PuntoDeAccesoWifiRepository extends JpaRepository<PuntoDeAccesoWifi, Long> {

    Page<PuntoDeAccesoWifi> findAllByColonia(String colonia, Pageable pegeable);
    Optional<PuntoDeAccesoWifi> findById(String id);
    Page<PuntoDeAccesoWifi> findAllByLatitudBetweenAndLongitudBetween(Double minLatitude, Double maxLatitude,
                                                               Double minLongitude,Double maxLongitud,
                                                               Pageable pageable);
}
