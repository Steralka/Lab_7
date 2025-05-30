package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class ShowRequest extends Request {

    public ShowRequest() {
        super(CommandType.SHOW.name());
    }
}
