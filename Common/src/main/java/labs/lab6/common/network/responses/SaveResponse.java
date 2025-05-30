package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandType;

public class SaveResponse extends Response {

    public SaveResponse(String errorMessage) {
        super(CommandType.SAVE.name(), errorMessage);
    }
}
