package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandType;

public class InfoResponse extends Response {

    private final String infoMessage;

    public InfoResponse(String infoMessage, String errorMessage) {
        super(CommandType.INFO.name(), errorMessage);
        this.infoMessage = infoMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }
}
