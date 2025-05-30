package labs.lab6.common.network.responses;

import labs.lab6.common.models.LabWork;
import labs.lab6.common.utility.CommandType;

import java.util.List;

public class ShowResponse extends Response {

    private final List<LabWork> labWorks;

    public ShowResponse(List<LabWork> labWorks, String errorMessage) {
        super(CommandType.SHOW.name(), errorMessage);
        this.labWorks = labWorks;
    }

    public List<LabWork> getLabWorks() {
        return labWorks;
    }
}
