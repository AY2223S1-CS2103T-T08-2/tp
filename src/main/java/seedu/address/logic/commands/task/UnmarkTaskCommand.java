package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks a task of a person as complete or incomplete.
 */
public class UnmarkTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Unmarks the task complete by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD_FULL + " 1";

    public static final String MESSAGE_SUCCESS = "Task %1$d is marked as incomplete";

    private final Index targetIndex;

    private final Logger log = Logger.getLogger("test");

    /**
     * @param targetIndex of the person's task to be updated
     */
    public UnmarkTaskCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);

        this.targetIndex = targetIndex;

        log.warning("warning");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        Task editedTask =
                new Task(taskToUnmark.getTitle(),
                        false,
                        taskToUnmark.getDeadline(),
                        taskToUnmark.getProject(),
                        taskToUnmark.getAssignedContacts());

        model.setTask(taskToUnmark, editedTask);

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkTaskCommand)) {
            return false;
        }

        // state check
        UnmarkTaskCommand e = (UnmarkTaskCommand) other;
        return targetIndex.equals(e.targetIndex);
    }
}

