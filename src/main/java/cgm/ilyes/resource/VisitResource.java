package cgm.ilyes.resource;

import cgm.ilyes.mapper.to.visit.VisitCreateTo;
import cgm.ilyes.mapper.to.visit.VisitGetAllTo;
import cgm.ilyes.mapper.to.visit.VisitGetTo;
import cgm.ilyes.mapper.to.visit.VisitUpdateTo;
import cgm.ilyes.service.VisitService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/api/public/v1/visits")
@Transactional
public class VisitResource {
    private static final Logger LOGGER = Logger.getLogger(VisitResource.class);
    @Inject
    VisitService visitService;

    @POST
    @Path("/")
    @ResponseStatus(201)
    public void create(@Valid VisitCreateTo visitCreateTo) {
        LOGGER.infof("Start processing create visit with request body {}", visitCreateTo);
        visitService.create(
                visitCreateTo.getPatientId(),
                visitCreateTo.getVisitDate(),
                visitCreateTo.getVisitType(),
                visitCreateTo.getVisitReason(),
                visitCreateTo.getFamilyHistory()
        );
        LOGGER.infof("Finished successfully processing create visit.");

    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") long id, @Valid VisitUpdateTo visitUpdateTo) {
        LOGGER.infof("Start processing create visit with id {} and request body {}", id, visitUpdateTo);
        visitService.update(
                id,
                visitUpdateTo.getVisitDate(),
                visitUpdateTo.getVisitType(),
                visitUpdateTo.getVisitReason(),
                visitUpdateTo.getFamilyHistory()
        );
        LOGGER.infof("Finished successfully processing update visit.");
    }

    @GET
    @Path("/")
    public VisitGetAllTo getAll(@QueryParam("patientId") Long patientId) {

        LOGGER.infof("Start processing get all visits with patientId {}", patientId);
        VisitGetAllTo visitGetAllTo = visitService.getAll(patientId);
        LOGGER.infof("Finished successfully processing get all visits.");
        return visitGetAllTo;
    }

    @GET
    @Path("/{id}")
    public VisitGetTo GetById(@PathParam("id") long id) {
        LOGGER.infof("Start processing get visit by id {}", id);
        VisitGetTo visitGetTo = visitService.getById(id);
        LOGGER.infof("Finished successfully processing get visit by id.");
        return visitGetTo;
    }

}
