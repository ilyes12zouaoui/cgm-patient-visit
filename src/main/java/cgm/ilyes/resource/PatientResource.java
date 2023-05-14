package cgm.ilyes.resource;

import cgm.ilyes.mapper.to.patient.PatientCreateTo;
import cgm.ilyes.mapper.to.patient.PatientGetAllTo;
import cgm.ilyes.mapper.to.patient.PatientGetTo;
import cgm.ilyes.service.PatientService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/api/public/v1/patients")
@Transactional
public class PatientResource {

    private static final Logger LOGGER = Logger.getLogger(PatientResource.class);
    @Inject
    PatientService patientService;

    @POST
    @Path("/")
    @ResponseStatus(201)
    public void create(@Valid PatientCreateTo patientCreateTo) {
        LOGGER.infof("Start processing create patient request with request body {}", patientCreateTo);
        patientService.create(patientCreateTo.getName(), patientCreateTo.getSurname(), patientCreateTo.getSocialSecurityNumber(), patientCreateTo.getDateOfBirth());
        LOGGER.infof("Finished successfully processing create patient.");
    }

    @GET
    @Path("/")
    public PatientGetAllTo getAll() {
        LOGGER.infof("Start processing get all patients.");
        PatientGetAllTo patientGetAllTo = patientService.getAll();
        LOGGER.infof("Finished successfully processing get all patients.");
        return patientGetAllTo;
    }

    @GET
    @Path("/{id}")
    public PatientGetTo getById(@PathParam("id") long id) {
        LOGGER.infof("Start processing get patient by id with value {}", id);
        PatientGetTo patientGetAllTo = patientService.getById(id);
        LOGGER.infof("Finished successfully processing get patient by id.");
        return patientGetAllTo;
    }
}
