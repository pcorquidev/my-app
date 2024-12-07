package tecnical.test.paul.my_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "PuntoDeAccesoWifis")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class PuntoDeAccesoWifi {

    @Id
    @Column
    private String id;

    @Column
    private String programa;

    @Column
    private String fechaInstalacion;

    @Column
    private Double latitud;

    @Column
    private Double longitud;

    @Column
    private String colonia;

    @Column
    private String alcaldia;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
