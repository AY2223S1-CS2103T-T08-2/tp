package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.ListTasksCommand;

public class ListTasksCommandParserTest {

    private ListTasksCommandParser parser = new ListTasksCommandParser();

    @Test
    public void parse_validArgs_returnsAssignTaskCommand() {
        assertParseSuccess(parser, "  ", new ListTasksCommand(Optional.empty(), new HashSet<>()));
        assertParseSuccess(parser, " ti/hi", new ListTasksCommand(Optional.of("hi"), new HashSet<>()));

        assertParseSuccess(parser, " c/1", new ListTasksCommand(Optional.empty(),
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON))));

        assertParseSuccess(parser, " ti/hi c/1 c/2", new ListTasksCommand(Optional.of("hi"),
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        assertParseFailure(parser, " hello", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTasksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {

        assertParseFailure(parser, " c/one", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTasksCommand.MESSAGE_USAGE));
    }

}
