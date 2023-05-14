package cgm.ilyes.mapper;

import cgm.ilyes.mapper.to.visit.VisitCreateTo;
import cgm.ilyes.mapper.to.visit.VisitGetAllTo;
import cgm.ilyes.mapper.to.visit.VisitGetTo;
import cgm.ilyes.repository.entity.PatientEntity;
import cgm.ilyes.repository.entity.VisitEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VisitMapper {

    @Mappings(
            {@Mapping(target = "patient", source = "patientEntity"),
                    @Mapping(target = "id", ignore = true),
                    @Mapping(target = "createdAt", ignore = true),
                    @Mapping(target = "updatedAt", ignore = true)
            }
    )
    VisitEntity toEntity(VisitCreateTo visitCreateTo, PatientEntity patientEntity);

    @Mapping(target = "patientId", source = "visitEntity.patient.id")
    VisitGetTo toTo(VisitEntity visitEntity);

    List<VisitGetTo> toToList(List<VisitEntity> patientEntityList);

    default VisitGetAllTo toGetAllTo(List<VisitEntity> patientEntityList) {
        return new VisitGetAllTo(toToList(patientEntityList));
    }
}
