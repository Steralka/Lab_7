package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class CountByMinimalPointRequest extends Request {

    private final Double minimalPoint;

    public CountByMinimalPointRequest(Double minimalPoint) {
        super(CommandType.COUNT_BY_MINIMAL_POINT.name());
        this.minimalPoint = minimalPoint;
    }

    public double getMinimalPoint() {
        return minimalPoint;
    }
}
