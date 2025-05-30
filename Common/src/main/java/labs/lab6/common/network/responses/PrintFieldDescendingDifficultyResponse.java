package labs.lab6.common.network.responses;

import labs.lab6.common.models.Difficulty;
import labs.lab6.common.utility.CommandType;

import java.util.List;

public class PrintFieldDescendingDifficultyResponse extends Response {

    private final List<Difficulty> difficulties;

    public PrintFieldDescendingDifficultyResponse(List<Difficulty> difficulties, String errorMessage) {
        super(CommandType.PRINT_FIELD_DESCENDING_DIFFICULTY.name(), errorMessage);
        this.difficulties = difficulties;
    }

    public List<Difficulty> getDifficulties() {
        return difficulties;
    }
}
