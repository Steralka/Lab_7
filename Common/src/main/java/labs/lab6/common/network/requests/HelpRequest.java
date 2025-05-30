package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class HelpRequest extends Request {

    public HelpRequest() {
        super(CommandType.HELP.name());
    }
}
