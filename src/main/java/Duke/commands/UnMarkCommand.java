package duke.commands;

import duke.util.TaskList;
import duke.util.UI;
import duke.util.Storage;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIndexException;
public class UnMarkCommand extends Command {
    private String[] words;
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    public UnMarkCommand(String[] words) {
        super();
        this.words = words;
    }
    /**
     * Executes the find command, searching for tasks containing the specified keyword.
     *
     * @param tasks The TaskList containing the list of tasks.
     * @param ui The UI object for displaying messages.
     * @param storage The Storage object for saving data to a file.
     * @return False indicating that the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int currentIdx = tasks.list().size();
        if (words.length == 1 || !isNumeric(words[1])) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx = Integer.parseInt(words[1]) - 1;
        if (taskIdx >= currentIdx || taskIdx < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        ui.displayUnMark(tasks.unMarkTask(taskIdx));
        storage.rewriteFile(tasks.list());
        return false;
    }
}
