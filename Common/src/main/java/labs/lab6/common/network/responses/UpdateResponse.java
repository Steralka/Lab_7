package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandType;

public class UpdateResponse extends Response {

    public UpdateResponse(String errorMessage) {
        super(CommandType.UPDATE.name(), errorMessage);
    }
}
