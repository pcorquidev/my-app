package tecnical.test.paul.my_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import tecnical.test.paul.my_app.domain.PuntoDeAccesoWifi;
import tecnical.test.paul.my_app.repos.PuntoDeAccesoWifiRepository;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PuntoDeAccesoWifiRepository puntoDeAccesoWifiRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:data.csv");
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setHeader("id", "programa", "fecha_instalacion","latitud","longitud","colonia","alcaldia")
                    .setSkipHeaderRecord(true)
                    .setNullString("NA")
                    .build().parse(reader);

            for (CSVRecord row : records) {

                PuntoDeAccesoWifi wifi = new PuntoDeAccesoWifi();
                wifi.setId(row.get("id"));
                wifi.setPrograma(row.get("programa"));
                wifi.setFechaInstalacion(row.get("fecha_instalacion"));
                wifi.setLatitud(Double.parseDouble(row.get("latitud")));
                wifi.setLongitud((row.get("longitud")) == null ? 0.0: Double.parseDouble(row.get("longitud")));
                wifi.setColonia(row.get("colonia"));
                wifi.setAlcaldia(row.get("alcaldia"));
                puntoDeAccesoWifiRepository.save(wifi);
            }
        }
    }
}

