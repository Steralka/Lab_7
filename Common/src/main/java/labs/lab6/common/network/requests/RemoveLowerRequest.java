package labs.lab6.common.network.requests;

import labs.lab6.common.models.LabWork;
import labs.lab6.common.utility.CommandType;

public class RemoveLowerRequest extends Request {

    private final LabWork labWork;

    public RemoveLowerRequest(LabWork labWork) {
        super(CommandType.REMOVE_LOWER.name());
        this.labWork = labWork;
    }

    public LabWork getLabWork() {
        return labWork;
    }
}
