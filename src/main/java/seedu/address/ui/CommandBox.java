package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    // command log navigation
    private final List<String> commandLog;
    private int pointer;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandLog = new ArrayList<>();
        load();
    }

    /**
     * Load objects and data.
     */
    private void load() {
        pointer = -1;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleNavigationKeysPressed);
        // prevents the suggestions popup from disappearing when user presses tab
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB) {
                if (!isCommandTextFieldEmpty()) {
                    event.consume();
                }
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String currentText = commandTextField.getText();
        // Guard: If command text field is empty, no command is entered
        if (currentText.isEmpty()) {
            return;
        }

        try {
            commandLog.add(currentText);
            pointer = commandLog.size();

            commandTextField.setText("");
            commandExecutor.execute(currentText);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Allows the user to navigate between previously entered commands with the UP and DOWN arrow keys.
     * If no more previous command and UP key is pressed, nothing happens.
     * If current text matches the latest command and DOWN key is pressed, nothing happens.
     */
    private void handleNavigationKeysPressed(KeyEvent event) {
        // Guard: Checks if either command log navigation key are pressed
        if (event.getCode() != KeyCode.UP && event.getCode() != KeyCode.DOWN) {
            return;
        }
        event.consume();

        // Guard: Checks if command text field satisfies condition.
        if (!isNavigable()) {
            return;
        }

        // Guard: If there are no commands, nothing happens.
        if (commandLog.size() == 0) {
            return;
        }

        if (event.getCode() == KeyCode.UP) {
            // If the text field shows the earliest command, UP arrow does nothing
            if (pointer == 0) {
                return;
            } else {
                commandTextField.setText(commandLog.get(--pointer));
            }
        } else if (event.getCode() == KeyCode.DOWN) {
            if (pointer == commandLog.size()) {
                return;
            } else if (pointer == commandLog.size() - 1) {
                pointer++;
                commandTextField.setText("");
            } else {
                commandTextField.setText(commandLog.get(++pointer));
            }
        }

        commandTextField.positionCaret(commandTextField.getText().length());
    }

    // Detects text changes in commandTextField to update suggestions popup
    @FXML
    private void handleTextChanged() {
        // Guard: Stop if no text in command text field
        if (isCommandTextFieldEmpty()) {
            return;
        }

        // "/" requests focus on the CommandTextField. Since it is an invalid starting command text, we can clear it.
        if (getCommandText().length() == 1 && getCommandText().equals("/")) {
            commandTextField.clear();
        }
    }

    /**
     * Sets focus on the CommandTextField.
     */
    public void focus() {
        commandTextField.requestFocus();
    }

    /**
     * Gets the current text in the command text field.
     */
    private String getCommandText() {
        return commandTextField.getText();
    }

    /**
     * Check if command text field is empty
     */
    private boolean isCommandTextFieldEmpty() {
        return commandTextField.getText().isEmpty();
    }

    /**
     * Checks if command text field can be navigated using the UP and DOWN arrow keys.
     */
    private boolean isNavigable() {
        return isCommandTextFieldEmpty()
            || (!isCommandTextFieldEmpty() && getCommandText().equals(commandLog.get(pointer)));
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
