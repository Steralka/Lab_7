package labs.lab6.common.network.requests;

import labs.lab6.common.models.LabWork;
import labs.lab6.common.utility.CommandType;

public class UpdateRequest extends Request {

    private final LabWork labWork;

    public UpdateRequest(LabWork labWork) {
        super(CommandType.UPDATE.name());
        this.labWork = labWork;
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
