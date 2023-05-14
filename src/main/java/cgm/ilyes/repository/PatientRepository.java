package cgm.ilyes.repository;

import cgm.ilyes.repository.entity.PatientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PatientRepository implements PanacheRepositoryBase<PatientEntity, Long> {

    public long countPatientsBySocialSecurityNumber(String socialSecurityNumber) {
        return count("socialSecurityNumber", socialSecurityNumber);
    }
}