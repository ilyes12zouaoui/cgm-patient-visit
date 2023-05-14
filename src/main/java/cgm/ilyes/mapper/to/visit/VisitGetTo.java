package cgm.ilyes.mapper.to.visit;


import cgm.ilyes.mapper.to.DateFormatPatternConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class VisitGetTo {

    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatPatternConstant.ZONED_DATE_TIME_FORMAT)
    private ZonedDateTime visitDate;
    private VisitTypeEnum visitType;
    private VisitReasonEnum visitReason;
    private String familyHistory;
    private long patientId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public VisitGetTo() {
    }

    public VisitGetTo(long id, ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory, long patientId, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.visitDate = visitDate;
        this.visitType = visitType;
        this.visitReason = visitReason;
        this.familyHistory = familyHistory;
        this.patientId = patientId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
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

    @Override
    public String toString() {
        return "VisitGetTo{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", visitType=" + visitType +
                ", visitReason=" + visitReason +
                ", familyHistory='" + familyHistory + '\'' +
                ", patientId=" + patientId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
