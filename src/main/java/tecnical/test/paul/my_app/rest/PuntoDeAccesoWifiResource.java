package tecnical.test.paul.my_app.rest;

import jakarta.validation.Valid;
import java.util.List;
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
    public ResponseEntity<Void> deletePuntoDeAccesoWifi(@PathVariable(name = "id") final String id) {
        puntoDeAccesoWifiService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
