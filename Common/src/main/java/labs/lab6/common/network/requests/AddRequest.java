package labs.lab6.common.network.requests;

import labs.lab6.common.models.LabWork;
import labs.lab6.common.utility.CommandType;

public class AddRequest extends Request {

    private final LabWork labWork;

    public AddRequest(LabWork labWork) {
        super(CommandType.ADD.name());
        this.labWork = labWork;
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
