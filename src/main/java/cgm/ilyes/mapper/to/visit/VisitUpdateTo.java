package cgm.ilyes.mapper.to.visit;

import cgm.ilyes.mapper.to.DateFormatPatternConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public class VisitUpdateTo {
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatPatternConstant.ZONED_DATE_TIME_FORMAT)
    private ZonedDateTime visitDate;
    @NotNull
    private VisitTypeEnum visitType;
    @NotNull
    private VisitReasonEnum visitReason;
    @NotBlank
    private String familyHistory;

    public VisitUpdateTo() {
    }

    public VisitUpdateTo(ZonedDateTime visitDate, VisitTypeEnum visitType, VisitReasonEnum visitReason, String familyHistory) {
        this.visitDate = visitDate;
        this.visitType = visitType;
        this.visitReason = visitReason;
        this.familyHistory = familyHistory;
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

    @Override
    public String toString() {
        return "VisitCreateOrUpdateTo{" +
                "visitDate=" + visitDate +
                ", visitType=" + visitType +
                ", visitReason=" + visitReason +
                ", familyHistory='" + familyHistory + '\'' +
                '}';
    }
}
