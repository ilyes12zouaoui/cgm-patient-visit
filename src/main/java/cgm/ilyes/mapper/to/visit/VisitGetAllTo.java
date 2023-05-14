package cgm.ilyes.mapper.to.visit;

import java.util.List;

public class VisitGetAllTo {

    private List<VisitGetTo> list;

    public VisitGetAllTo(List<VisitGetTo> list) {
        this.list = list;
    }

    public VisitGetAllTo() {
    }

    public List<VisitGetTo> getList() {
        return list;
    }

    public void setList(List<VisitGetTo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "VisitGetAllTo{" +
                "list=" + list +
                '}';
    }
}
