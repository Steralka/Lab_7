package labs.lab6.common.network.requests;

import labs.lab6.common.utility.CommandType;

public class PrintFieldDescendingDifficultyRequest extends Request {

    public PrintFieldDescendingDifficultyRequest() {
        super(CommandType.PRINT_FIELD_DESCENDING_DIFFICULTY.name());
    }
}
