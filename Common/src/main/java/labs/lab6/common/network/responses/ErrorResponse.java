package labs.lab6.common.network.responses;

public class ErrorResponse extends Response {

    public ErrorResponse(String error) {
        super("Error", error);
    }
}
