package cgm.ilyes.repository;

import cgm.ilyes.repository.entity.VisitEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
public class VisitRepository implements PanacheRepositoryBase<VisitEntity, Long> {


    public List<VisitEntity> findAllByPatientId(long id) {
        return list("patient.id", id);
    }

    public long countVisitsBetween15MinutesBeforeAnd15MinutesAfterProvidedDateAndForAPatient(ZonedDateTime visitDate, long patientId) {
        return count("patient.id = ?1 and visitDate >= ?2 and visitDate <= ?3", patientId, visitDate.minus(15, ChronoUnit.MINUTES), visitDate.plus(15, ChronoUnit.MINUTES));
    }
}