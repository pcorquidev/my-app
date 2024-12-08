package tecnical.test.paul.my_app.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tecnical.test.paul.my_app.domain.PuntoDeAccesoWifi;
import tecnical.test.paul.my_app.model.PuntoDeAccesoWifiDTO;
import tecnical.test.paul.my_app.repos.PuntoDeAccesoWifiRepository;
import tecnical.test.paul.my_app.util.NotFoundException;


@Service
public class PuntoDeAccesoWifiService {

    private final PuntoDeAccesoWifiRepository puntoDeAccesoWifiRepository;

    public PuntoDeAccesoWifiService(final PuntoDeAccesoWifiRepository puntoDeAccesoWifiRepository) {
        this.puntoDeAccesoWifiRepository = puntoDeAccesoWifiRepository;
    }

    public List<PuntoDeAccesoWifiDTO> findAll() {
        final List<PuntoDeAccesoWifi> puntoDeAccesoWifis = puntoDeAccesoWifiRepository.findAll(Sort.by("id"));
        return puntoDeAccesoWifis.stream()
                .map(puntoDeAccesoWifi -> mapToDTO(puntoDeAccesoWifi, new PuntoDeAccesoWifiDTO()))
                .toList();
    }

    public Page<PuntoDeAccesoWifiDTO> findAll(Pageable pageable){
        final Page<PuntoDeAccesoWifi> puntoDeAccesoWifis = puntoDeAccesoWifiRepository.findAll(pageable);
        return puntoDeAccesoWifis
                .map(puntoDeAccesoWifi -> mapToDTO(puntoDeAccesoWifi, new PuntoDeAccesoWifiDTO()));
    }

    public Page<PuntoDeAccesoWifiDTO> findAllByColonia(String colonia, Pageable pageable){
        final Page<PuntoDeAccesoWifi> puntoDeAccesoWifis = puntoDeAccesoWifiRepository.findAllByColonia(colonia, pageable);
        return puntoDeAccesoWifis
                .map(puntoDeAccesoWifi -> mapToDTO(puntoDeAccesoWifi, new PuntoDeAccesoWifiDTO()));
    }

    public Page<PuntoDeAccesoWifiDTO> findAllByProximity(Double latitude, Double longitude, Pageable pageable){
        Double earthRadius = 6371.0;
        double distanceInKm = 10;
        double latDelta = distanceInKm / earthRadius;
        double lonDelta = distanceInKm / (earthRadius * Math.cos(Math.toRadians(latitude)));

        // Rango de latitud y longitud
        double minLatitude = latitude - Math.toDegrees(latDelta);
        double maxLatitude = latitude + Math.toDegrees(latDelta);
        double minLongitude = longitude - Math.toDegrees(lonDelta);
        double maxLongitude = longitude + Math.toDegrees(lonDelta);

        final Page<PuntoDeAccesoWifi> puntoDeAccesoWifis = puntoDeAccesoWifiRepository.findAllByLatitudBetweenAndLongitudBetween(minLatitude, maxLatitude, minLongitude, maxLongitude,pageable);
        return puntoDeAccesoWifis
                .map(puntoDeAccesoWifi -> mapToDTO(puntoDeAccesoWifi, new PuntoDeAccesoWifiDTO()));
    }



    public PuntoDeAccesoWifiDTO get(final String id) {
        return puntoDeAccesoWifiRepository.findById(id)
                .map(puntoDeAccesoWifi -> mapToDTO(puntoDeAccesoWifi, new PuntoDeAccesoWifiDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        final PuntoDeAccesoWifi puntoDeAccesoWifi = new PuntoDeAccesoWifi();
        mapToEntity(puntoDeAccesoWifiDTO, puntoDeAccesoWifi);
        return puntoDeAccesoWifiRepository.save(puntoDeAccesoWifi).getId();
    }

    public void update(final String id, final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        final PuntoDeAccesoWifi puntoDeAccesoWifi = puntoDeAccesoWifiRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(puntoDeAccesoWifiDTO, puntoDeAccesoWifi);
        puntoDeAccesoWifiRepository.save(puntoDeAccesoWifi);
    }

    public void delete(final Long id) {
        puntoDeAccesoWifiRepository.deleteById(id);
    }

    private PuntoDeAccesoWifiDTO mapToDTO(final PuntoDeAccesoWifi puntoDeAccesoWifi,
            final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        puntoDeAccesoWifiDTO.setId(puntoDeAccesoWifi.getId());
        puntoDeAccesoWifiDTO.setPrograma(puntoDeAccesoWifi.getPrograma());
        puntoDeAccesoWifiDTO.setFechaInstalacion(puntoDeAccesoWifi.getFechaInstalacion());
        puntoDeAccesoWifiDTO.setLatitud(puntoDeAccesoWifi.getLatitud());
        puntoDeAccesoWifiDTO.setLongitud(puntoDeAccesoWifi.getLongitud());
        puntoDeAccesoWifiDTO.setColonia(puntoDeAccesoWifi.getColonia());
        puntoDeAccesoWifiDTO.setAlcaldia(puntoDeAccesoWifi.getAlcaldia());
        return puntoDeAccesoWifiDTO;
    }

    private PuntoDeAccesoWifi mapToEntity(final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO,
            final PuntoDeAccesoWifi puntoDeAccesoWifi) {
        puntoDeAccesoWifi.setId(puntoDeAccesoWifiDTO.getId());
        puntoDeAccesoWifi.setPrograma(puntoDeAccesoWifiDTO.getPrograma());
        puntoDeAccesoWifi.setFechaInstalacion(puntoDeAccesoWifiDTO.getFechaInstalacion());
        puntoDeAccesoWifi.setLatitud(puntoDeAccesoWifiDTO.getLatitud());
        puntoDeAccesoWifi.setLongitud(puntoDeAccesoWifiDTO.getLongitud());
        puntoDeAccesoWifi.setColonia(puntoDeAccesoWifiDTO.getColonia());
        puntoDeAccesoWifi.setAlcaldia(puntoDeAccesoWifiDTO.getAlcaldia());
        return puntoDeAccesoWifi;
    }

}
