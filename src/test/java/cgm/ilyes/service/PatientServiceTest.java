package cgm.ilyes.service;

import cgm.ilyes.exception.TechnicalInputViolationException;
import cgm.ilyes.exception.TechnicalNotFoundException;
import cgm.ilyes.mapper.PatientMapper;
import cgm.ilyes.mapper.to.patient.PatientGetAllTo;
import cgm.ilyes.mapper.to.patient.PatientGetTo;
import cgm.ilyes.repository.PatientRepository;
import cgm.ilyes.repository.entity.PatientEntity;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
public class PatientServiceTest {

    @Inject
    PatientMapper patientMapper;

    @InjectMock
    PatientRepository patientRepository;

    @Inject
    PatientService patientService;

    ArgumentCaptor<PatientEntity> patientEntityCaptor = ArgumentCaptor.forClass(PatientEntity.class);

    @Test
    public void shouldCreateSuccessfullyWhenValidData() {
        String name = "Omar";
        String surname = "Saleh";
        String socialSecurityNumber = "12345678A111";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);

        when(patientRepository.countPatientsBySocialSecurityNumber(socialSecurityNumber)).thenReturn(0L);

        patientService.create(name, surname, socialSecurityNumber, dateOfBirth);

        verify(patientRepository).persist(patientEntityCaptor.capture());

        PatientEntity capturedPatientEntity = patientEntityCaptor.getValue();

        assertEquals(name, capturedPatientEntity.getName());
        assertEquals(surname, capturedPatientEntity.getSurname());
        assertEquals(socialSecurityNumber, capturedPatientEntity.getSocialSecurityNumber());
        assertEquals(dateOfBirth, capturedPatientEntity.getDateOfBirth());
    }

    @Test
    public void shouldGetAllSuccessfullyWhenValidInput() {
        List<PatientEntity> patientEntities = new ArrayList<>();
        patientEntities.add(new PatientEntity(1L, "Will", "Jun", "12345678A111", LocalDate.of(1990, 1, 1)));
        patientEntities.add(new PatientEntity(2L, "Ali", "Smith", "87654321B222", LocalDate.of(1995, 5, 10)));

        List<PatientGetTo> patientGetToList = new ArrayList<>();
        patientGetToList.add(new PatientGetTo(1L, "Will", "Jun", "12345678A111", LocalDate.of(1990, 1, 1)));
        patientGetToList.add(new PatientGetTo(2L, "Ali", "Smith", "87654321B222", LocalDate.of(1995, 5, 10)));

        PatientGetAllTo patientGetAllTo = new PatientGetAllTo(patientGetToList);
        PatientService patientService = new PatientService(patientMapper, patientRepository);

        when(patientRepository.listAll()).thenReturn(patientEntities);

        PatientGetAllTo result = patientService.getAll();

        verify(patientRepository).listAll();

        assertThat(patientGetAllTo.getList()).usingFieldByFieldElementComparator().containsExactlyInAnyOrderElementsOf(result.getList());
    }


    @Test
    public void shouldGetByIdSuccessfullyWhenValidInput() {
        long id = 1L;
        PatientEntity patientEntity = new PatientEntity(id, "Ali", "Martin", "12345678A111", LocalDate.of(1990, 1, 1));
        PatientGetTo patientGetTo = new PatientGetTo(id, "Ali", "Martin", "12345678A111", LocalDate.of(1990, 1, 1));

        when(patientRepository.findByIdOptional(id)).thenReturn(Optional.of(patientEntity));

        PatientGetTo result = patientService.getById(id);

        verify(patientRepository).findByIdOptional(id);

        assertThat(patientGetTo).isEqualToComparingFieldByField(result);
    }

    @Test
    public void shouldThrowExceptionGetByIdWhenPatientNotFound() {
        long id = 1L;

        when(patientRepository.findByIdOptional(id)).thenReturn(Optional.empty());

        assertThrows(TechnicalNotFoundException.class, () -> patientService.getById(id));

        verify(patientRepository).findByIdOptional(id);
    }

    @Test
    public void shouldThrowExceptionValidateSocialSecurityNumberWhenInvalidFormat() {
        PatientService patientService = new PatientService(patientMapper, patientRepository);

        assertThrows(TechnicalInputViolationException.class,
                () -> patientService.validateSocialSecurityNumber("1234567890111"));

        verify(patientRepository, never()).countPatientsBySocialSecurityNumber("1234567890111");
    }

    @Test
    public void shouldValidateSocialSecurityNumberSuccessfullyWhenValidInput() {
        PatientService patientService = new PatientService(patientMapper, patientRepository);

        when(patientRepository.countPatientsBySocialSecurityNumber("12345678A111")).thenReturn(1L);

        assertThrows(TechnicalInputViolationException.class,
                () -> patientService.validateSocialSecurityNumber("12345678A111"));

        verify(patientRepository).countPatientsBySocialSecurityNumber("12345678A111");
    }

    @Test
    public void shouldValidateDateOfBirthSuccessfullyWhenValidDate() {
        PatientService patientService = new PatientService(patientMapper, patientRepository);
        patientService.validateDateOfBirth(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void shouldThrowExceptionValidateDateOfBirthWhenInvalidDate() {
        PatientService patientService = new PatientService(patientMapper, patientRepository);
        assertThrows(TechnicalInputViolationException.class,
                () -> patientService.validateDateOfBirth(LocalDate.of(1900, 1, 1)));
    }
}