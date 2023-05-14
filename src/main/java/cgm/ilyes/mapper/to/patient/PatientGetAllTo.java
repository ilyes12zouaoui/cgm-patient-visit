package cgm.ilyes.mapper.to.patient;

import java.util.List;

public class PatientGetAllTo {

    private List<PatientGetTo> list;

    public PatientGetAllTo(List<PatientGetTo> list) {
        this.list = list;
    }

    public PatientGetAllTo() {
    }

    public List<PatientGetTo> getList() {
        return list;
    }

    public void setList(List<PatientGetTo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PatientGetAllTo{" +
                "list=" + list +
                '}';
    }
}
