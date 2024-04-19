package validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Validates input arguments based on a set of predefined rules.
 * Each rule is defined to ensure that the input arguments meet the required criteria for the application to function correctly.
 */
public class InputValidator {
    private final List<Predicate<String[]>> validationRules = new ArrayList<>();

    /**
     * Constructs a new InputValidator and initializes the validation rules.
     */
    public InputValidator() {
        // Ensures arguments are not null or empty
        addValidationRule(args -> args != null && args.length > 0, "Arguments cannot be null or empty.");

        // Ensures the exact number of required arguments
        addValidationRule(args -> args.length == ValidatorsOptions.getArgSize(),
                "Must have only " + ValidatorsOptions.getArgSize() + " arguments.");

        // Ensures the second argument is an integer within a specified range
        addValidationRule(args -> isIntegerInRange(args[1]),
                "The second argument must be an integer between " +
                        ValidatorsOptions.MIN.getLimit() + " and " +
                        ValidatorsOptions.MAX.getLimit() + ".");
    }

    /**
     * Validates that a given input value is an integer within a specified range.
     *
     * @param value The string value to validate.
     * @return true if the value is an integer within the range, false otherwise.
     */
    private boolean isIntegerInRange(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= ValidatorsOptions.MIN.getLimit() && intValue <= ValidatorsOptions.MAX.getLimit();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Adds a new validation rule to this validator.
     *
     * @param rule The predicate representing the validation rule.
     * @param errorMessage The error message to throw if the validation fails.
     */
    private void addValidationRule(Predicate<String[]> rule, String errorMessage) {
        validationRules.add(args -> {
            if (!rule.test(args)) {
                throw new ValidatorExceptions(errorMessage);
            }
            return true;
        });
    }

    /**
     * Validates the given arguments against all registered validation rules.
     *
     * @param args The arguments to validate.
     * @throws ValidatorExceptions if any validation rule fails.
     */
    public void validate(String[] args) {
        validationRules.forEach(rule -> rule.test(args));
    }
}
