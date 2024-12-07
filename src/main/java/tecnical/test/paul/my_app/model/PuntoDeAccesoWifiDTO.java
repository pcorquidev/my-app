package tecnical.test.paul.my_app.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PuntoDeAccesoWifiDTO {

    private String id;

    @Size(max = 255)
    private String programa;

    private String fechaInstalacion;

    private Double latitud;

    private Double longitud;

    @Size(max = 255)
    private String colonia;

    @Size(max = 255)
    private String alcaldia;

}
