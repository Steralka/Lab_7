package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandType;

public class ClearResponse extends Response {

    public ClearResponse(String errorMessage) {
        super(CommandType.CLEAR.name(), errorMessage);
    }
}
