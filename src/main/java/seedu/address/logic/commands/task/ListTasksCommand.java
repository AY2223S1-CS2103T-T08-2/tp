package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.AssignedToContactsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordPredicate;

/**
 * Lists all tasks in the task panel to the user that match the specified requirements.
 */
public class ListTasksCommand extends TaskCommand {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Lists all tasks that satisfy the specified requirements.\n"
            + "Parameters: "
            + "[" + PREFIX_TITLE + "KEYWORD]"
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + PREFIX_TITLE + "fix "
            + PREFIX_CONTACT + "1 ";

    public static final String MESSAGE_SUCCESS = "Found %1$s tasks %2$s %3$s";

    private final Predicate<Task> basePredicate;
    private final Optional<String> keywordFilter;
    private final Set<Index> personIndexes = new HashSet<>();

    /**
     * Creates a ListTasksCommand to list the {@code Task}s with the specified filters
     * @param keywordFilter an optional keyword to filter by the task title
     * @param personsIndexes a set of indexes to view only tasks assigned to the corresponding contacts
     */
    public ListTasksCommand(Predicate<Task> basePredicate, Optional<String> keywordFilter, Set<Index> personsIndexes) {
        requireAllNonNull(basePredicate, personsIndexes);
        this.basePredicate = basePredicate;
        this.keywordFilter = keywordFilter;
        this.personIndexes.addAll(personsIndexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Task> titlePredicate = keywordFilter
                .map(k -> (Predicate<Task>) new TitleContainsKeywordPredicate(k))
                .orElse(Model.PREDICATE_SHOW_ALL_TASKS);

        AssignedToContactsPredicate contactsPredicate = new AssignedToContactsPredicate(model, personIndexes);

        model.updateFilteredTaskList(basePredicate.and(titlePredicate).and(contactsPredicate));

        return new CommandResult(
                String.format(
                        MESSAGE_SUCCESS,
                        model.getFilteredTaskList().size(),
                        keywordFilter.map(s -> String.format("containing '%s'", s)).orElse("").trim(),
                        contactsPredicate.getContacts().isEmpty()
                                ? ""
                                : String.format("that are assigned to %s", contactsPredicate.getContactNames())
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListTasksCommand)) {
            return false;
        }

        // state check
        ListTasksCommand e = (ListTasksCommand) other;
        return basePredicate.equals(e.basePredicate)
                && keywordFilter.equals(e.keywordFilter)
                && setIndexEquals(personIndexes, e.personIndexes);
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
