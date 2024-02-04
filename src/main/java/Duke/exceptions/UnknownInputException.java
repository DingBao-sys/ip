package Duke.exceptions;
public class UnknownInputException extends DukeException {
    public UnknownInputException() {
        super();
    }

    @Override
    public String toString() {
        return "If you want to type gibberish you can craft your own Duke,\nhowever if you wish to continue talking to me " +
                "here are the available commands:\nlist\ntodo\nevent\ndeadline\n";
    }
}
