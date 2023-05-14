package cgm.ilyes.service;

import cgm.ilyes.exception.TechnicalInputViolationException;
import cgm.ilyes.exception.TechnicalNotFoundException;
import cgm.ilyes.mapper.PatientMapper;
import cgm.ilyes.mapper.to.patient.PatientGetAllTo;
import cgm.ilyes.mapper.to.patient.PatientGetTo;
import cgm.ilyes.repository.PatientRepository;
import cgm.ilyes.repository.entity.PatientEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PatientService {

    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;

    public PatientService(PatientMapper patientMapper, PatientRepository patientRepository) {
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
    }

    public void create(String name,
                       String surname,
                       String socialSecurityNumber,
                       LocalDate dateOfBirth) {
        this.validateDateOfBirth(dateOfBirth);
        this.validateSocialSecurityNumber(socialSecurityNumber);

        PatientEntity patientEntity = new PatientEntity(name, surname, socialSecurityNumber, dateOfBirth);
        patientRepository.persist(patientEntity);
    }

    public PatientGetAllTo getAll() {
        List<PatientEntity> patientEntities = patientRepository.listAll();
        return patientMapper.toGetAllTo(patientEntities);
    }

    public PatientGetTo getById(long id) {
        PatientEntity patientEntity = this.tryGetById(id);
        return patientMapper.toTO(patientEntity);
    }

    public PatientEntity tryGetById(long id) {
        return patientRepository.findByIdOptional(id).orElseThrow(() -> new TechnicalNotFoundException("Patient with id " + id + " was not found."));
    }

    public void validateSocialSecurityNumber(String socialSecurityNumber) {
        if (!socialSecurityNumber.matches("[0-9]{8}[A-Z][0-9]{3}")) {
            throw new TechnicalInputViolationException("socialSecurityNumber field format is uncorrected. Example of correct format '12345678A111'");
        }

        if (patientRepository.countPatientsBySocialSecurityNumber(socialSecurityNumber) != 0) {
            throw new TechnicalInputViolationException("socialSecurityNumber field value already exist in our system. It must be unique.");
        }
    }

    public void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth.isBefore(LocalDate.of(1910, 1, 1))) {
            throw new TechnicalInputViolationException("dateOfBirth field cannot be before 1910-01-01");
        }
    }

}
