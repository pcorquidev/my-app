package tecnical.test.paul.my_app.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PuntoDeAccesoWifiDTO {

    private Long id;

    @Size(max = 255)
    private String programa;

    private LocalDate fechaInstalacion;

    private Double latitud;

    private Double longitud;

    @Size(max = 255)
    private String colonia;

    @Size(max = 255)
    private String alcaldia;

}
