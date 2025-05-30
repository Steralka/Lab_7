package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class HistoryRequest extends Request {

    private final int commandCount;

    public HistoryRequest(int commandCount) {
        super(CommandType.HISTORY.name());
        this.commandCount = commandCount;
    }

    public int getCommandCount() {
        return commandCount;
    }
}
