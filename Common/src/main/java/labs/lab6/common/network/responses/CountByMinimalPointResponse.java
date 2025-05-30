package labs.lab6.common.network.responses;

import labs.lab6.common.utility.CommandType;

public class CountByMinimalPointResponse extends Response {

    private final int count;

    public CountByMinimalPointResponse(int count, String errorMessage) {
        super(CommandType.COUNT_BY_MINIMAL_POINT.name(), errorMessage);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
