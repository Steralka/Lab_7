package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class RemoveByIdRequest extends Request {

    private final long id;

    public RemoveByIdRequest(long id) {
        super(CommandType.REMOVE_BY_ID.name());
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
