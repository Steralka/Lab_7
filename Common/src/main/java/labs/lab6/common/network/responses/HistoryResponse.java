package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandType;

import java.util.List;

public class HistoryResponse extends Response {

    private final List<String> historyMessages;

    public HistoryResponse(List<String> history, String errorMessage) {
        super(CommandType.HISTORY.name(), errorMessage);
        this.historyMessages = history;
    }

    public List<String> getHistoryMessages() {
        return historyMessages;
    }
}
