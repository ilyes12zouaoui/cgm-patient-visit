package cgm.ilyes.repository.entity;

import cgm.ilyes.mapper.to.visit.VisitReasonEnum;
import cgm.ilyes.mapper.to.visit.VisitTypeEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "visits")
public class VisitEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private ZonedDateTime visitDate;
    @NotNull
    private VisitTypeEnum visitType;
    @NotNull
    private VisitReasonEnum visitReason;
    @NotNull
    private String familyHistory;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    public VisitEntity() {
    }

    public VisitEntity(ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory) {
        this.visitDate = visitDate;
        this.visitType = visitType;
        this.visitReason = visitReason;
        this.familyHistory = familyHistory;
    }

    public VisitEntity(ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory, PatientEntity patient) {
        this.visitDate = visitDate;
        this.visitType = visitType;
        this.visitReason = visitReason;
        this.familyHistory = familyHistory;
        this.patient = patient;
    }

    public VisitEntity(long id, ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory, PatientEntity patient) {
        this.id = id;
        this.visitDate = visitDate;
        this.visitType = visitType;
        this.visitReason = visitReason;
        this.familyHistory = familyHistory;
        this.patient = patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(ZonedDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public VisitTypeEnum getVisitType() {
        return visitType;
    }

    public void setVisitType(VisitTypeEnum visitType) {
        this.visitType = visitType;
    }

    public VisitReasonEnum getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(VisitReasonEnum visitReason) {
        this.visitReason = visitReason;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "VisitEntity{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", visitType=" + visitType +
                ", visitReason=" + visitReason +
                ", familyHistory='" + familyHistory + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", patient=" + patient +
                '}';
    }
}
