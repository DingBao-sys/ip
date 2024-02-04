package Duke.commands;

import java.time.LocalDateTime;
import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.exceptions.DukeException;
import Duke.exceptions.EmptyDescriptionException;
import Duke.exceptions.InvalidDateFormatException;

public class DateCommand extends Commands {
    private String[] words;
    private static boolean isValidDateFormat(String deadline) {
        if (deadline.length() <= 12 || deadline.length() >= 16) {
            return false;
        }
        String[] dateNumbers = deadline.split("[/ ]");
        if (dateNumbers.length != 4) {
            return false;
        }
        try {
            for (String i : dateNumbers) {
                Integer.parseInt(i);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (time >= 2400 || time < 0) {
            return false;
        }
        return true;
    }
    public DateCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("date command");
        }
        words[1] = words[1].trim();
        if (!isValidDateFormat(words[1])) {
            throw new InvalidDateFormatException();
        }
        String[] dateNumbers = words[1].split("[/ ]");
        LocalDateTime toFind = LocalDateTime.of(
                Integer.parseInt(dateNumbers[2]),
                Integer.parseInt(dateNumbers[1]),
                Integer.parseInt(dateNumbers[0]),
                Integer.parseInt(dateNumbers[3].substring(0, 2)),
                Integer.parseInt(dateNumbers[3].substring(2)));
        ui.displayFoundList(tasks.findTaskWithDate(toFind));
        return false;
    }
}
