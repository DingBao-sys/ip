package duke.commands;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;
public class UnMarkCommandTest {
    @Test
    public void executeForStringTest() {
        Storage storage = new Storage("./data/duke.txt");
        UI ui = new UI();
        TaskList tasks = new TaskList(storage.readFile());
        try {
            String input = "unMark 5";
            String[] words = input.split(" ", 2);
            System.out.println(new ListCommand().executeForString(tasks, ui, storage));
            System.out.println(new UnMarkCommand(words).executeForString(tasks, ui, storage));
        } catch (DukeException e) {
            System.out.println(ui.exceptionMsg(e));
        }
    }
}