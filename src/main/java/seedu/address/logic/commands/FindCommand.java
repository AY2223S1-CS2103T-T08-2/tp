package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ContainsTagPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords, or any of the
 * argument tags.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive), or whose tags contain any of the specified tags, and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: [NAME_KEYWORD]... "
            + "[" + PREFIX_TAG + "TAG_KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie "
            + PREFIX_TAG + "developers " + PREFIX_TAG + "qa";

    private final NameContainsKeywordsPredicate namePredicate;
    private final ContainsTagPredicate tagsPredicate;

    /**
     * @param namePredicate for names to filter
     * @param tagsPredicate for tags to filter
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate, ContainsTagPredicate tagsPredicate) {
        requireAllNonNull(namePredicate, tagsPredicate);
        this.namePredicate = namePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> filter = tagsPredicate.or(namePredicate);
        model.updateFilteredPersonList(filter);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)); // state check
    }
}
