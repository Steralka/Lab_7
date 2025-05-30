package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class ClearRequest extends Request {

    public ClearRequest() {
        super(CommandType.CLEAR.name());
    }
}
