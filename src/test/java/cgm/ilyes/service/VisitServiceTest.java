package cgm.ilyes.service;

import cgm.ilyes.exception.TechnicalInputViolationException;
import cgm.ilyes.mapper.VisitMapper;
import cgm.ilyes.mapper.to.visit.VisitGetAllTo;
import cgm.ilyes.mapper.to.visit.VisitGetTo;
import cgm.ilyes.mapper.to.visit.VisitReasonEnum;
import cgm.ilyes.mapper.to.visit.VisitTypeEnum;
import cgm.ilyes.repository.VisitRepository;
import cgm.ilyes.repository.entity.PatientEntity;
import cgm.ilyes.repository.entity.VisitEntity;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
public class VisitServiceTest {

    @Inject
    VisitMapper visitMapper;

    @InjectMock
    VisitRepository visitRepository;

    @InjectMock
    PatientService patientService;

    @Inject
    VisitService visitService;
    ArgumentCaptor<VisitEntity> visitEntityCaptor = ArgumentCaptor.forClass(VisitEntity.class);

    @Test
    public void shouldCreateSuccessfullyWhenValidData() {
        long patientId = 1L;
        ZonedDateTime visitDate = ZonedDateTime.now();
        VisitTypeEnum visitType = VisitTypeEnum.AT_THE_DOCTOR_OFFICE;
        VisitReasonEnum visitReason = VisitReasonEnum.FIRST_VISIT;
        String familyHistory = "Some family history";

        PatientEntity patientEntity = new PatientEntity();
        when(patientService.tryGetById(patientId)).thenReturn(patientEntity);

        visitService.create(patientId, visitDate, visitType, visitReason, familyHistory);

        verify(patientService).tryGetById(patientId);

        verify(visitRepository).persist(visitEntityCaptor.capture());

        VisitEntity capturedVisitEntity = visitEntityCaptor.getValue();
        assertEquals(visitDate, capturedVisitEntity.getVisitDate());
        assertEquals(visitType, capturedVisitEntity.getVisitType());
        assertEquals(visitReason, capturedVisitEntity.getVisitReason());
        assertEquals(familyHistory, capturedVisitEntity.getFamilyHistory());
        assertEquals(patientEntity, capturedVisitEntity.getPatient());
    }

    @Test
    public void shouldUpdateSuccessfullyWhenValidData() {
        long visitId = 1L;
        ZonedDateTime visitDate = ZonedDateTime.now();
        VisitTypeEnum visitType = VisitTypeEnum.AT_THE_DOCTOR_OFFICE;
        VisitReasonEnum visitReason = VisitReasonEnum.FIRST_VISIT;
        String familyHistory = "Updated family history";

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(2L);

        VisitEntity existingVisitEntity = new VisitEntity();
        existingVisitEntity.setId(visitId);
        existingVisitEntity.setVisitDate(visitDate.minusDays(1)); // Existing visit date
        existingVisitEntity.setVisitType(VisitTypeEnum.AT_THE_DOCTOR_OFFICE);
        existingVisitEntity.setVisitReason(VisitReasonEnum.FIRST_VISIT);
        existingVisitEntity.setFamilyHistory("Existing family history");
        existingVisitEntity.setPatient(patientEntity);


        when(visitRepository.findByIdOptional(anyLong())).thenReturn(Optional.of(existingVisitEntity));

        visitService.update(visitId, visitDate, visitType, visitReason, familyHistory);

        verify(visitRepository).persist(visitEntityCaptor.capture());
        VisitEntity capturedVisitEntity = visitEntityCaptor.getValue();

        assertEquals(visitId, capturedVisitEntity.getId());
        assertEquals(visitDate, capturedVisitEntity.getVisitDate());
        assertEquals(visitType, capturedVisitEntity.getVisitType());
        assertEquals(visitReason, capturedVisitEntity.getVisitReason());
        assertEquals(familyHistory, capturedVisitEntity.getFamilyHistory());
        assertEquals(existingVisitEntity.getPatient(), capturedVisitEntity.getPatient());

    }

    @Test
    public void shouldGetAllSuccessfullyWhenValidData() {

        long patientId = 1L;

        VisitEntity visit1 = new VisitEntity();
        VisitEntity visit2 = new VisitEntity();
        List<VisitEntity> visitEntities = Arrays.asList(visit1, visit2);

        VisitGetTo visitGetTo1 = new VisitGetTo();
        VisitGetTo visitGetTo2 = new VisitGetTo();
        List<VisitGetTo> visitGetToList = Arrays.asList(visitGetTo1, visitGetTo2);

        when(visitRepository.findAllByPatientId(patientId)).thenReturn(visitEntities);

        VisitGetAllTo result = visitService.getAll(patientId);

        verify(visitRepository).findAllByPatientId(patientId);

        Assertions.assertThat(visitGetToList).usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(result.getList());
    }

    @Test
    public void shouldValidateVisitDateSuccessfullyWhenValidDate() {
        long patientId = 1L;
        ZonedDateTime visitDate = ZonedDateTime.now();

        when(visitRepository.countVisitsBetween15MinutesBeforeAnd15MinutesAfterProvidedDateAndForAPatient(visitDate, patientId)).thenReturn(0L);

        visitService.validateVisitDate(patientId, visitDate);

        verify(visitRepository).countVisitsBetween15MinutesBeforeAnd15MinutesAfterProvidedDateAndForAPatient(visitDate, patientId);

    }

    @Test
    public void shouldThrowExceptionValidateVisitDateWhenInvalidDate() {
        long patientId = 1L;
        ZonedDateTime visitDate = ZonedDateTime.now();

        when(visitRepository.countVisitsBetween15MinutesBeforeAnd15MinutesAfterProvidedDateAndForAPatient(visitDate, patientId)).thenReturn(1L);

        assertThrows(TechnicalInputViolationException.class, () -> visitService.validateVisitDate(patientId, visitDate));

        verify(visitRepository).countVisitsBetween15MinutesBeforeAnd15MinutesAfterProvidedDateAndForAPatient(visitDate, patientId);

    }
}