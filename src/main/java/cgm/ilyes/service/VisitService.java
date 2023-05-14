package cgm.ilyes.service;

import cgm.ilyes.exception.TechnicalInputViolationException;
import cgm.ilyes.exception.TechnicalNotFoundException;
import cgm.ilyes.mapper.VisitMapper;
import cgm.ilyes.mapper.to.visit.VisitGetAllTo;
import cgm.ilyes.mapper.to.visit.VisitGetTo;
import cgm.ilyes.mapper.to.visit.VisitReasonEnum;
import cgm.ilyes.mapper.to.visit.VisitTypeEnum;
import cgm.ilyes.repository.VisitRepository;
import cgm.ilyes.repository.entity.PatientEntity;
import cgm.ilyes.repository.entity.VisitEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class VisitService {

    private final VisitMapper visitMapper;
    private final VisitRepository visitRepository;
    private final PatientService patientService;

    public VisitService(VisitMapper visitMapper, VisitRepository visitRepository, PatientService patientService) {
        this.visitMapper = visitMapper;
        this.visitRepository = visitRepository;
        this.patientService = patientService;
    }

    public void create(long patientId, ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory) {
        PatientEntity patientEntity = patientService.tryGetById(patientId);
        this.validateVisitDate(patientId, visitDate);

        VisitEntity visitEntity = new VisitEntity(visitDate, visitType, visitReason, familyHistory, patientEntity);

        visitRepository.persist(visitEntity);
    }

    public void update(long visitId, ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory) {
        VisitEntity visitEntity = this.tryGetById(visitId);
        this.validateVisitDate(visitEntity.getPatient().getId(), visitDate);

        visitEntity.setVisitDate(visitDate);
        visitEntity.setVisitType(visitType);
        visitEntity.setVisitReason(visitReason);
        visitEntity.setFamilyHistory(familyHistory);

        visitRepository.persist(visitEntity);
    }

    public VisitGetAllTo getAll(Long patientId) {
        List<VisitEntity> visitEntities;

        if (patientId == null) {
            visitEntities = visitRepository.listAll();
        } else {
            visitEntities = visitRepository.findAllByPatientId(patientId);
        }

        return visitMapper.toGetAllTo(visitEntities);
    }

    public VisitGetTo getById(long id) {
        VisitEntity visitEntity = this.tryGetById(id);
        return visitMapper.toTo(visitEntity);
    }

    public VisitEntity tryGetById(long id) {
        return visitRepository.findByIdOptional(id).orElseThrow(() -> new TechnicalNotFoundException("Visit with id " + id + " was not found."));
    }

    public void validateVisitDate(long patientId, ZonedDateTime visitDate) {
        if (visitRepository.countVisitsBetween15MinutesBeforeAnd15MinutesAfterProvidedDateAndForAPatient(visitDate, patientId) != 0) {
            throw new TechnicalInputViolationException("visitDate field value is invalid. There is another appointment for the patient with id " + patientId + " at the same time or in the range of 15 minutes before or after it.");
        }
    }
}
