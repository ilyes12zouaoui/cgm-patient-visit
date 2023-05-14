package cgm.ilyes.resource;

import cgm.ilyes.mapper.to.patient.PatientGetTo;
import cgm.ilyes.mapper.to.visit.VisitGetTo;
import cgm.ilyes.repository.PatientRepository;
import cgm.ilyes.repository.entity.PatientEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@QuarkusTest
class PatientResourceTest {

    @Inject
    PatientRepository patientRepository;

    @Test
    public void shouldGetPatientByIdSuccessfullyWhenValidInput() {
        PatientEntity patientEntity =new PatientEntity("Omar","Saleh","12345678A111", LocalDate.of(1990, 1, 1));
        patientRepository.persist(patientEntity);

        PatientGetTo expectedPatientGetTo = new PatientGetTo(1L,"Omar","Saleh","12345678A111", LocalDate.of(1990, 1, 1));

           // Perform the request
        PatientGetTo patientGetTo = given()
                .when()
                .get("/api/public/v1/patients/{id}", 1L)
                .then()
                .statusCode(200)
                .extract().body().as(PatientGetTo.class);

        Assertions.assertThat(patientGetTo.getDateOfBirth()).isEqualTo(expectedPatientGetTo.getDateOfBirth());
        Assertions.assertThat(patientGetTo.getName()).isEqualTo(expectedPatientGetTo.getName());
        Assertions.assertThat(patientGetTo.getSurname()).isEqualTo(expectedPatientGetTo.getSurname());
        Assertions.assertThat(patientGetTo.getSocialSecurityNumber()).isEqualTo(expectedPatientGetTo.getSocialSecurityNumber());
        patientRepository.delete(patientEntity);

    }
}