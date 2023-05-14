package cgm.ilyes.resource;

import cgm.ilyes.mapper.to.visit.*;
import cgm.ilyes.repository.PatientRepository;
import cgm.ilyes.repository.entity.PatientEntity;
import cgm.ilyes.service.VisitService;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@QuarkusTest
public class VisitResourceTest {

    @Inject
    PatientRepository patientRepository;

    @Test
    public void shouldSuccessfullyCreateVisitThenGetVisitThenUpdateVisitThenGetVisitWhenValidInput() {

        PatientEntity patientEntity =new PatientEntity("Omar","Saleh","12345678A111",LocalDate.of(1990, 1, 1));
        patientRepository.persist(patientEntity);

        VisitCreateTo visitCreateTo = new VisitCreateTo();
        visitCreateTo.setPatientId(2L);
        visitCreateTo.setVisitDate(ZonedDateTime.now().plus(5, ChronoUnit.DAYS));
        visitCreateTo.setVisitType(VisitTypeEnum.AT_THE_DOCTOR_OFFICE);
        visitCreateTo.setVisitReason(VisitReasonEnum.FIRST_VISIT);
        visitCreateTo.setFamilyHistory("Some family history");

        // Perform the request
        given()
                .contentType(ContentType.JSON)
                .body(visitCreateTo)
                .when()
                .post("/api/public/v1/visits/")
                .then()
                .statusCode(201);

        // Perform the request
       VisitGetTo visitGetTo = given()
                .when()
                .get("/api/public/v1/visits/{id}", 1L)
                .then()
                .statusCode(200)
                .extract().body().as(VisitGetTo.class);

        Assertions.assertThat(visitGetTo.getVisitDate()).isEqualToIgnoringSeconds(visitCreateTo.getVisitDate());
        Assertions.assertThat(visitGetTo.getFamilyHistory()).isEqualTo(visitCreateTo.getFamilyHistory());
        Assertions.assertThat(visitGetTo.getVisitReason()).isEqualTo(visitCreateTo.getVisitReason());
        Assertions.assertThat(visitGetTo.getVisitType()).isEqualTo(visitCreateTo.getVisitType());

        VisitUpdateTo visitUpdateTo = new VisitUpdateTo();
        visitUpdateTo.setVisitDate(ZonedDateTime.now().plus(6, ChronoUnit.DAYS));
        visitUpdateTo.setVisitType(VisitTypeEnum.AT_HOME);
        visitUpdateTo.setVisitReason(VisitReasonEnum.RECURRING_VISIT);
        visitUpdateTo.setFamilyHistory("Updated family history 1");

        // Perform the request
        given()
                .contentType(ContentType.JSON)
                .body(visitUpdateTo)
                .when()
                .put("/api/public/v1/visits/{id}", 1L)
                .then()
                .statusCode(204);

        // Perform the request
        visitGetTo = given()
                .when()
                .get("/api/public/v1/visits/{id}", 1L)
                .then()
                .statusCode(200)
                .extract().body().as(VisitGetTo.class);

        Assertions.assertThat(visitGetTo.getVisitDate()).isEqualToIgnoringSeconds(visitUpdateTo.getVisitDate());
        Assertions.assertThat(visitGetTo.getFamilyHistory()).isEqualTo(visitUpdateTo.getFamilyHistory());
        Assertions.assertThat(visitGetTo.getVisitReason()).isEqualTo(visitUpdateTo.getVisitReason());
        Assertions.assertThat(visitGetTo.getVisitType()).isEqualTo(visitUpdateTo.getVisitType());


    }

    @Test
    public void shouldThrowBadRequestUpdateVisitWhenInvalidInput() {

        // Prepare the request body
        VisitUpdateTo visitUpdateTo = new VisitUpdateTo();
        visitUpdateTo.setVisitDate(ZonedDateTime.now());
        visitUpdateTo.setVisitType(null);
        visitUpdateTo.setVisitReason(VisitReasonEnum.FIRST_VISIT);
        visitUpdateTo.setFamilyHistory("Updated family history");

        // Perform the request
        given()
                .contentType(ContentType.JSON)
                .body(visitUpdateTo)
                .when()
                .put("/api/public/v1/visits/{id}", 1L)
                .then()
                .statusCode(400);
    }
}