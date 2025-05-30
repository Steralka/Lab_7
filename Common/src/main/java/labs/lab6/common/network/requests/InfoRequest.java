package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class InfoRequest extends Request {

    public InfoRequest() {
        super(CommandType.INFO.name());
    }
}
