package tecnical.test.paul.my_app.rest;

import jakarta.validation.Valid;
import java.util.List;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import tecnical.test.paul.my_app.constants.Constants;
import tecnical.test.paul.my_app.model.PuntoDeAccesoWifiDTO;
import tecnical.test.paul.my_app.service.PuntoDeAccesoWifiService;


@RestController
@RequestMapping(value = Constants.WIFI_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PuntoDeAccesoWifiResource {

    private final PuntoDeAccesoWifiService puntoDeAccesoWifiService;

    public PuntoDeAccesoWifiResource(final PuntoDeAccesoWifiService puntoDeAccesoWifiService) {
        this.puntoDeAccesoWifiService = puntoDeAccesoWifiService;
    }

    @GetMapping
    public ResponseEntity<List<PuntoDeAccesoWifiDTO>> getAllPuntoDeAccesoWifis() {
        return ResponseEntity.ok(puntoDeAccesoWifiService.findAll());
    }

    @GetMapping(Constants.GET_LIST)
    public ResponseEntity<Page<PuntoDeAccesoWifiDTO>> getPuntosDeAccessoWifiByPage(
            @RequestParam(name = Constants.REQUEST_PARAM_PAGE, defaultValue = "0") int page
    ){
        Pageable pageRequest = PageRequest.of(page,Constants.PAGE_SIZE);
        Page<PuntoDeAccesoWifiDTO> puntosDeAccesoWifiService = puntoDeAccesoWifiService.findAll(pageRequest);
        return ResponseEntity.ok(puntosDeAccesoWifiService);
    }

    @GetMapping(Constants.GET_LIST_BY_COLONIA)
    public ResponseEntity<Page<PuntoDeAccesoWifiDTO>> getPuntosDeAccesoWifiByColonia(
            @RequestParam(name = Constants.REQUEST_PARAM_PAGE, defaultValue = "0") int page,
            @PathVariable(name = Constants.REQUEST_PARAM_NAME) final String name
    ){
        Pageable pageable = PageRequest.of(page,Constants.PAGE_SIZE);
        Page<PuntoDeAccesoWifiDTO> puntoDeAccesoWifiDTOS = puntoDeAccesoWifiService.findAllByColonia(name,pageable);
        return ResponseEntity.ok(puntoDeAccesoWifiDTOS);
    }

    @GetMapping(Constants.GET_LIST_BY_COORDENADAS)
    public ResponseEntity<Page<PuntoDeAccesoWifiDTO>> getPuntosDeAccesoWifiByProximity(
            @RequestParam(name = Constants.REQUEST_PARAM_PAGE, defaultValue = "0") int page,
            @RequestParam(name = Constants.REQUEST_PARAM_LONGITUD, defaultValue = "0.0") double longitud,
            @RequestParam(name = Constants.REQUEST_PARAM_LATITUD, defaultValue = "0.0") double latitud
    ){
        Pageable pageable = PageRequest.of(page,Constants.PAGE_SIZE);
        Page<PuntoDeAccesoWifiDTO> puntoDeAccesoWifiDTOS = puntoDeAccesoWifiService.findAllByProximity(latitud, longitud, pageable);
        return ResponseEntity.ok(puntoDeAccesoWifiDTOS);
    }

    @GetMapping(Constants.ID)
    public ResponseEntity<PuntoDeAccesoWifiDTO> getPuntoDeAccesoWifi(
            @PathVariable(name = Constants.PATH_VARIABLE_ID) final String id) {
        return ResponseEntity.ok(puntoDeAccesoWifiService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createPuntoDeAccesoWifi(
            @RequestBody @Valid final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        final String createdId = puntoDeAccesoWifiService.create(puntoDeAccesoWifiDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping(Constants.ID)
    public ResponseEntity<String> updatePuntoDeAccesoWifi(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        puntoDeAccesoWifiService.update(id, puntoDeAccesoWifiDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping(Constants.ID)
    public ResponseEntity<Void> deletePuntoDeAccesoWifi(@PathVariable(name = "id") final Long id) {
        puntoDeAccesoWifiService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
