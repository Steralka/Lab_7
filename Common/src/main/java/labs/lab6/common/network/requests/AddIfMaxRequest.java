package labs.lab6.common.network.requests;

import labs.lab6.common.models.LabWork;
import labs.lab6.common.utility.CommandType;

public class AddIfMaxRequest extends Request {

    private final LabWork labWork;

    public AddIfMaxRequest(LabWork labWork) {
        super(CommandType.ADD.name());
        this.labWork = labWork;
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
