package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;

/**
 * Assigns person(s) to a task.
 */
public class AssignTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "assign";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Assigns person(s) to a task.\n"
            + "Parameters: "
            + "TASK_INDEX (must be a positive integer) "
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "1 "
            + PREFIX_CONTACT + "2 "
            + PREFIX_CONTACT + "3 ";

    public static final String MESSAGE_SUCCESS = "Assigned persons to task: %1$s";

    public static final String MESSAGE_RESET_SUCCESS = "Removed all assigned persons from task: %1$s";

    private final Index taskIndex;
    private final Set<Index> personIndexes = new HashSet<>();

    /**
     * Creates an AssignTaskCommand to assign persons to the given task.
     *
     * @param taskIndex of the task to be updated
     * @param personsIndexes of all persons to be assigned to task
     */
    public AssignTaskCommand(Index taskIndex, Set<Index> personsIndexes) {
        requireNonNull(taskIndex);
        requireNonNull(personsIndexes);
        this.taskIndex = taskIndex;
        this.personIndexes.addAll(personsIndexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Task> lastShownTaskList = model.getFilteredTaskList();
        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToAssignTo = lastShownTaskList.get(taskIndex.getZeroBased());

        List<Person> lastShownPersonsList = model.getFilteredPersonList();
        Set<Contact> assignedContacts = new HashSet<>();
        for (Index personIndex : personIndexes) {
            if (personIndex.getZeroBased() >= lastShownPersonsList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Contact contactToAssign =
                new Contact(lastShownPersonsList.get(personIndex.getZeroBased()).getName().fullName);
            assignedContacts.add(contactToAssign);
        }

        Task editedTask = new Task(
                taskToAssignTo.getTitle(),
                taskToAssignTo.getCompleted(),
                taskToAssignTo.getDeadline(),
                assignedContacts
        );

        model.setTask(taskToAssignTo, editedTask);

        if (personIndexes.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_RESET_SUCCESS, taskIndex.getOneBased()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, taskIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignTaskCommand)) {
            return false;
        }

        // state check
        AssignTaskCommand e = (AssignTaskCommand) other;
        return taskIndex.equals(e.taskIndex) && setIndexEquals(personIndexes, e.personIndexes);
    }

    private boolean setIndexEquals(Set<Index> firstSet, Set<Index> secondSet) {
        if (firstSet.size() != secondSet.size()) {
            return false;
        }

        Set<Integer> firstSetValues = firstSet.stream().map(Index::getZeroBased).collect(Collectors.toSet());
        Set<Integer> secondSetValues = secondSet.stream().map(Index::getZeroBased).collect(Collectors.toSet());

        return firstSetValues.equals(secondSetValues);
    }

}
