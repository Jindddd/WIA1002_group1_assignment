package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.*;
import javafx.event.Event;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class TextConverterController extends ApplicationController implements Initializable {
    @FXML
    TextArea inputTextArea;
    @FXML
    TextField shiftValueTextField;
    @FXML
    ChoiceBox<String> operationChoiceBox;
    String[] operations = {"Encrypt", "Decrypt"};
    @FXML
    Button convertButton;
    @FXML
    Label inputLabel;
    @FXML
    Label shiftValueLabel;
    @FXML
    Label operationChoiceLabel;
    @FXML
    RadioButton securedModeRButton;
    @FXML
    TextArea convertedTextCopiableLabel; // Use TextField instead because Label is not copiable
    private static final int MIN_SHIFT = 1;
    private static final int MAX_SHIFT = 25;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Disable context menu from coming up when right-clicking on the converted text
        convertedTextCopiableLabel.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
        operationChoiceBox.getItems().addAll(operations);
        operationChoiceBox.setOnAction(this::showMode);
    }

    public void showMode(ActionEvent event) {
        String choice = operationChoiceBox.getValue();
        if (choice.equals("Encrypt")) {
            securedModeRButton.setDisable(false);
        } else if (choice.equals("Decrypt")) {
            securedModeRButton.setDisable(true);
        }
    }

    public void convert(ActionEvent event) {
        inputLabel.setTextFill(Paint.valueOf("black"));
        shiftValueLabel.setTextFill(Paint.valueOf("black"));
        operationChoiceLabel.setTextFill(Paint.valueOf("black"));
        try {
            // Check if the input format is correct
            if (validate()) {
                String text = inputTextArea.getText();
                String shift = shiftValueTextField.getText();
                String choice = operationChoiceBox.getValue();

                // Encrypt or decrypt the text
                if (choice.equals("Encrypt")) {
                    String mode = securedModeRButton.isSelected() ? "Secured" : "Simple";
                    // Simple mode or secured mode
                    if (mode.equals("Simple")) {
                        String encryptedText = encryptText(addRandomParentheses(text), Integer.parseInt(shift));
                        convertedTextCopiableLabel.setText("Encrypted text: " + encryptedText);
                    } else {
                        String encryptedText = encryptText(addRandomNum(addRandomParentheses(text)), Integer.parseInt(shift));
                        convertedTextCopiableLabel.setText("Encrypted text: " + encryptedText);
                    }
                } else if (choice.equals("Decrypt")) {
                    String decryptedText = decryptText(text, Integer.parseInt(shift));
                    convertedTextCopiableLabel.setText("Decrypted text: " + decryptedText);
                }
                convertedTextCopiableLabel.setOpacity(1);
                // Set text to blue color
                convertedTextCopiableLabel.setStyle("-fx-text-inner-color: #006284;");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Enter only integer!!!");
            alert.showAndWait();
        }
    }
    boolean validate() {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        if (inputTextArea.getText().trim().isEmpty()) {
            errors.append("Please enter your text to encrypt/decrypt\n");
            inputLabel.setTextFill(Paint.valueOf("red"));
        }
        if (shiftValueTextField.getText().trim().isEmpty() || Integer.parseInt(shiftValueTextField.getText()) < MIN_SHIFT || Integer.parseInt(shiftValueTextField.getText()) > MAX_SHIFT) {
            errors.append("Please enter a number between 1-25 for shift value\n");
            shiftValueLabel.setTextFill(Paint.valueOf("red"));
        }
        if (operationChoiceBox.getValue() == null || operationChoiceBox.getValue().trim().isEmpty()){
            errors.append("Please select a operation choice\n");
            operationChoiceLabel.setTextFill(Paint.valueOf("red"));
        }
        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            printError(errors);
            return false;
        }
        // No errors
        return true;
    }

    /**
     * Decrypts the given text using the specified shift value.
     *
     * @param text The text to decrypt.
     * @param shift The shift value.
     * @return The decrypted text.
     */
    public static String decryptText(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        int length = text.length();
        boolean replaceComma = true;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            if (c == '&') {
                if (i + 1 < length && Character.isDigit(text.charAt(i + 1))) {
                    int num = Character.getNumericValue(text.charAt(i + 1));
                    int startIndex = i + 3;
                    int endIndex = text.indexOf('}', i);
                    if (endIndex <= startIndex) {
                        // Skip the current iteration if endIndex is invalid
                        continue;
                    }

                    // Extract the substring to be decrypted and decrypt
                    String toBeDecrypted = text.substring(startIndex, endIndex);
                    String decryptedSubstring = decryptText(toBeDecrypted, num);
                    decryptedText.append(decryptedSubstring);

                    // Skip the characters inside curley braces
                    i = endIndex;

                } else {
                    decryptedText.append(c);
                }

            } else if (c == '^') {
                // Decrypt the next letter and convert to uppercase
                char decryptedChar = decryptLetter(text.charAt(i + 1), shift);
                decryptedText.append(Character.toUpperCase(decryptedChar));

                // Skip both '^' and the decrypted letter
                i++;

            } else if (c == '$') {
                // Replace with space
                decryptedText.append(' ');

            } else if (c == '(') {
                int endIndex = text.indexOf(')', i);

                if (endIndex <= i) {
                    // Skip the current iteration if endIndex is invalid
                    continue;
                }

                // Reverse the substring and decrypt
                String toBeInverted = text.substring(i + 1, endIndex);
                String inverted = new StringBuilder(toBeInverted).reverse().toString();
                String decryptedInverted = decryptText(inverted, shift);
                decryptedText.append(decryptedInverted);

                // Skip the characters inside parentheses
                i = endIndex;

            } else if (Character.isLetter(c)) {
                // Decrypt letter with a shifted value
                char decryptedChar = decryptLetter(c, shift);
                decryptedText.append(decryptedChar);

            } else if (c != ')' && c != '{' && c != '}' && Character.isDigit(c)) {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }

    /**
     * Decrypts a single letter using the specified shift value.
     *
     * @param letter The letter to decrypt.
     * @param shift The shift value.
     * @return The decrypted letter.
     */
    private static char decryptLetter(char letter, int shift) {
        char decryptedChar = (char) (letter - shift);
        // Wrap around character in alphabet
        if (decryptedChar < 'a') {
            decryptedChar += 26;
        }
        return decryptedChar;
    }

    /**
     * Encrypts the given text using the specified shift value.
     *
     * @param text The text to encrypt.
     * @param shift The shift value.
     * @return The encrypted text.
     */
    public static String encryptText(String text, int shift) {

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '&') {
                int num = Character.getNumericValue(text.charAt(i + 1));
                int startIndex = i + 3;
                int endIndex = text.indexOf('}', i);

                if (endIndex <= startIndex) {
                    // Skip the current iteration if endIndex is invalid
                    continue;
                }

                // Extract substring to be encrypted and encrypt
                String toBeEncrypted = text.substring(startIndex, endIndex);
                String encrypted = encryptText(toBeEncrypted, num);
                encryptedText.append("&").append(num).append("{").append(encrypted).append("}");

                // Skip the substring
                i = endIndex;

            } else if (Character.isUpperCase(c)) {
                encryptedText.append('^');
                char encryptedChar = encryptLetter(Character.toLowerCase(c), shift);
                encryptedText.append(encryptedChar);

            } else if (c == ' ') {
                // Replace space with '$'
                encryptedText.append('$');

            } else if (c == '(') {
                // Append the opening parenthesis
                encryptedText.append('(');
                int endIndex = text.indexOf(')', i);

                if (endIndex <= i) {
                    // Skip the current iteration if endIndex is invalid
                    continue;
                }

                // Extract the substring to be encrypted and encrypt
                String toBeEncrypted = text.substring(i + 1, endIndex);
                String encrypted = encryptText(toBeEncrypted, shift);
                String inverted = new StringBuilder(encrypted).reverse().toString();
                encryptedText.append(inverted);
                encryptedText.append(')');

                // Skip the substring
                i = endIndex;

            } else if (Character.isLetter(c)) {
                // Encrypt letter with a shifted value
                char encryptedChar = encryptLetter(c, shift);
                encryptedText.append(encryptedChar);

            } else {
                // Append any other characters as they are
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    /**
     * Encrypts a single letter using the specified shift value.
     *
     * @param letter The letter to encrypt.
     * @param shift The shift value.
     * @return The encrypted letter.
     */
    private static char encryptLetter(char letter, int shift) {
        // Shift the letter
        char encryptedChar = (char) (letter + shift);

        // Wrap around character in alphabet
        if (encryptedChar > 'z') {
            encryptedChar -= 26;
        }
        return encryptedChar;
    }

    /**
     * Adds random parentheses to the given string.
     *
     * @param string The string to add parentheses to.
     * @return The modified string with random parentheses.
     */
    public static String addRandomParentheses(String string) {
        StringBuilder result = new StringBuilder();
        int length = string.length();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(string.charAt(i));
            // Check if there is enough remaining length to add parentheses
            // and if the current character and the next character are not already parentheses
            if (i < length - 1 && string.charAt(i) != '(' && string.charAt(i + 1) != ')') {
                // Generate a random decision to determine whether to add parentheses
                if (random.nextBoolean()) {
                    // Calculate the maximum length for substring
                    int maxInnerLength = length - 1 - i;

                    // Check if the maximum inner length is less than 2, meaning it cannot accommodate parentheses
                    if (maxInnerLength < 2) {
                        // If not possible to add then skip to next iteration
                        continue;
                    }

                    // Generate a random inner length between 2 and maxInnerLength
                    int innerLength = random.nextInt(maxInnerLength - 2 + 1) + 2;

                    // Append opening parenthesis
                    result.append('(');
                    // Append the substring to be enclosed within parentheses
                    result.append(string, i + 1, Math.min(i + 1 + innerLength, length));
                    // Append closing parenthesis
                    result.append(')');
                    // Move index to the end of the enclosed substring
                    i += innerLength;

                }

            }
        }

        return result.toString();
    }

    public static String addRandomNum(String string) {
        StringBuilder result = new StringBuilder();
        int length = string.length();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(string.charAt(i));
            // Check if there is enough remaining length to add parentheses
            // and if the current character and the next character are not already parentheses
            if (i < length - 1 && string.charAt(i) != '(' && string.charAt(i + 1) != ')') {
                // Generate a random decision to determine whether to add parentheses
                if (random.nextBoolean()) {
                    // Calculate the maximum length for substring
                    int maxInnerLength = length - 1 - i;

                    // Check if the maximum inner length is less than 1, meaning it cannot accommodate parentheses
                    if (maxInnerLength < 1) {
                        // If not possible to add then skip to next iteration
                        continue;
                    }

                    // Generate a random inner length between 1 and maxInnerLength
                    int innerLength = random.nextInt(maxInnerLength) + 1;

                    // Append opening parenthesis
                    result.append('&').append(random.nextInt(9) + 1).append('{');
                    // Append the substring to be enclosed within parentheses
                    result.append(string, i + 1, Math.min(i + 1 + innerLength, length));
                    // Append closing parenthesis
                    result.append('}');
                    // Move index to the end of the enclosed substring
                    i += innerLength;
                }
            }
        }

        return result.toString();
    }
}
