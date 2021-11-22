package test.java.vehicle.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableEurekaClient
//@ServletComponentScan
@SpringBootApplication
public class VehicleTrackingApplication {

    public static void main(final String... args) {
        SpringApplication.run(VehicleTrackingApplication.class, args);
    }

}
