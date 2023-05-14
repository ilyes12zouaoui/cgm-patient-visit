package cgm.ilyes.mapper;

import cgm.ilyes.mapper.to.patient.PatientCreateTo;
import cgm.ilyes.mapper.to.patient.PatientGetAllTo;
import cgm.ilyes.mapper.to.patient.PatientGetTo;
import cgm.ilyes.repository.entity.PatientEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PatientMapper {

    @Mappings(
            {@Mapping(target = "id", ignore = true),
                    @Mapping(target = "createdAt", ignore = true),
                    @Mapping(target = "updatedAt", ignore = true)}
    )
    PatientEntity toEntity(PatientCreateTo patientCreateTo);

    PatientGetTo toTO(PatientEntity patientGetTo);

    List<PatientGetTo> toToList(List<PatientEntity> patientEntityList);

    default PatientGetAllTo toGetAllTo(List<PatientEntity> patientEntityList) {
        return new PatientGetAllTo(toToList(patientEntityList));
    }
}
