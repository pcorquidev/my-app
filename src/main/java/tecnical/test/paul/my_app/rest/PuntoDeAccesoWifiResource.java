package tecnical.test.paul.my_app.rest;

import jakarta.validation.Valid;
import java.util.List;
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
import tecnical.test.paul.my_app.model.PuntoDeAccesoWifiDTO;
import tecnical.test.paul.my_app.service.PuntoDeAccesoWifiService;


@RestController
@RequestMapping(value = "/api/puntoDeAccesoWifis", produces = MediaType.APPLICATION_JSON_VALUE)
public class PuntoDeAccesoWifiResource {

    private final PuntoDeAccesoWifiService puntoDeAccesoWifiService;

    public PuntoDeAccesoWifiResource(final PuntoDeAccesoWifiService puntoDeAccesoWifiService) {
        this.puntoDeAccesoWifiService = puntoDeAccesoWifiService;
    }

    @GetMapping
    public ResponseEntity<List<PuntoDeAccesoWifiDTO>> getAllPuntoDeAccesoWifis() {
        return ResponseEntity.ok(puntoDeAccesoWifiService.findAll());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PuntoDeAccesoWifiDTO>> getPuntosDeAccessoWifiByPage(
            @RequestParam(name = "page", defaultValue = "0") int page
    ){
        Pageable pageRequest = PageRequest.of(page,10);
        Page<PuntoDeAccesoWifiDTO> puntosDeAccesoWifiService = puntoDeAccesoWifiService.findAll(pageRequest);
        return ResponseEntity.ok(puntosDeAccesoWifiService);
    }

    @GetMapping("/colonia/{name}")
    public ResponseEntity<Page<PuntoDeAccesoWifiDTO>> getPuntosDeAccesoWifiByColonia(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @PathVariable(name = "name") final String name
    ){
        Pageable pageable = PageRequest.of(page,10);
        Page<PuntoDeAccesoWifiDTO> puntoDeAccesoWifiDTOS = puntoDeAccesoWifiService.findAllByColonia(name,pageable);
        return ResponseEntity.ok(puntoDeAccesoWifiDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeAccesoWifiDTO> getPuntoDeAccesoWifi(
            @PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(puntoDeAccesoWifiService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createPuntoDeAccesoWifi(
            @RequestBody @Valid final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        final String createdId = puntoDeAccesoWifiService.create(puntoDeAccesoWifiDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePuntoDeAccesoWifi(@PathVariable(name = "id") final String id,
            @RequestBody @Valid final PuntoDeAccesoWifiDTO puntoDeAccesoWifiDTO) {
        puntoDeAccesoWifiService.update(id, puntoDeAccesoWifiDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntoDeAccesoWifi(@PathVariable(name = "id") final Long id) {
        puntoDeAccesoWifiService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
