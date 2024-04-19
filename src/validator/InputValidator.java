package validator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import validator.ValidatorExceptions;
public class InputValidator {
    private final List<Predicate<String[]>> validationRules = new ArrayList<>();

    public InputValidator() {
        // Initialize validation rules
        addValidationRule(args -> args != null && args.length > 0, "Arguments cannot be null or empty.");

        addValidationRule(args -> {
            int numOfArgs = args.length;
            System.out.println("Number of arguments received: " + numOfArgs);
            return numOfArgs == ValidatorsOptions.getArgSize();
        }, "Must have only 3 arguments.");

        addValidationRule(args -> isIntegerInRange(args[1]), "The second argument must be an integer between 1 and 199.");

        // Add more rules as needed
    }

    private boolean isIntegerInRange(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= ValidatorsOptions.MIN.getLimit() && intValue <= ValidatorsOptions.MAX.getLimit();
        } catch (NumberFormatException e) {
            return false; // The string is not an integer
        }
    }
    private void addValidationRule(Predicate<String[]> rule, String errorMessage) {
        validationRules.add(args -> {
            if (!rule.test(args)) {
                throw new ValidatorExceptions(errorMessage);
            }
            return true; // This will always be true if no exception is thrown
        });
    }

    public void validate(String[] args) {
        validationRules.forEach(rule -> rule.test(args));
    }
}
