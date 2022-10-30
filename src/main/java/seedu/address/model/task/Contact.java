package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;
import java.util.Locale;

import seedu.address.model.person.Person;

/**
 * Contact is a Person who is related to a task and is present in the address book.
 * Guarantees: immutable; contact is valid as declared in {@link #isValidContact(String)}
 */
public class Contact {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\p{Alnum}[\\p{Alnum} ]*";

    private final String contactName;

    /**
     * Constructs a {@code Contact}.
     *
     * @param contactName A valid contact name
     */
    public Contact(String contactName) {
        requireNonNull(contactName);
        checkArgument(isValidContact(contactName), MESSAGE_CONSTRAINTS);
        this.contactName = contactName;
    }

    /**
     * Constructs a {@code Contract} from a Person.
     * @param person given Person
     */
    public Contact(Person person) {
        this(person.getName().toString());
    }

    /**
     * Returns true if a given string is a valid contact name.
     */
    public static boolean isValidContact(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns person's name (with proper capitalisation) if a given string is a name that belongs to one of the persons
     * in the list of persons. Otherwise returns an empty string.
     */
    public static String corrNameInPersonsList(List<Person> personList, String test) {
        for (Person person : personList) {
            if (person.getName().fullName.toLowerCase(Locale.ROOT).equals(test.toLowerCase(Locale.ROOT))) {
                return person.getName().fullName;
            }
        }
        return "";
    }

    public String getContactName() {
        return contactName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Contact) // instanceof handles nulls
            && contactName.equals(((Contact) other).getContactName()); // state check
    }

    @Override
    public int hashCode() {
        return contactName.hashCode();
    }

    @Override
    public String toString() {
        return contactName;
    }
}
