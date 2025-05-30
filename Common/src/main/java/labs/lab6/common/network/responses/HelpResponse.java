package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandInfo;
import labs.lab6.common.utility.CommandType;

import java.util.List;

public class HelpResponse extends Response {

    private final List<CommandInfo> helps;

    public HelpResponse(List<CommandInfo> helps, String errorMessage) {
        super(CommandType.HELP.name(), errorMessage);
        this.helps = helps;
    }

    public List<CommandInfo> getHelps() {
        return helps;
    }
}
