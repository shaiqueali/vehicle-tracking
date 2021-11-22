package test.java.vehicle.tracking.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface ErrorPrinter extends Serializable {

    HttpStatus getHttpStatus();
}
