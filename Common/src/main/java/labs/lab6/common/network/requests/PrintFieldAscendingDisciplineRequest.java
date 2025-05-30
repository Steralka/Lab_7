package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class PrintFieldAscendingDisciplineRequest extends Request {

    public PrintFieldAscendingDisciplineRequest() {
        super(CommandType.PRINT_FIELD_ASCENDING_DISCIPLINE.name());
    }
}
