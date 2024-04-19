package main;

import validator.InputValidator;
import runner.Runner;
import validator.ValidatorExceptions;
public class Main {

    public static void main(String[] args) {


        try {
            InputValidator validator = new InputValidator();
            validator.validate(args); // This will throw ValidatorExceptions if validation fails
            Runner.startProg(args); // Proceed if validation is successful
        } catch (ValidatorExceptions exc) {
            // Catching specific validation exceptions
            System.err.println("Validation failed: " + exc.getMessage());
            System.exit(1); // Exit with a non-zero status to indicate error
        } catch (Exception e) {
            // Catching other unexpected exceptions
            System.err.println("Error occurred: " + e.getMessage());
            System.exit(1); // Exit with a non-zero status to indicate error
        }
    }

}
