package com.assignment.backup;

import java.util.Random;
import java.util.Scanner;

public class TextConverter {

    private static final int MIN_SHIFT = 1;
    private static final int MAX_SHIFT = 25;
    private static final int SIMPLE_MODE = 1;
    private static final int SECURED_MODE = 2;
    private static final int ENCRYPTION = 1;
    private static final int DECRYPTION = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text to encrypt / decrypt.");
        System.out.print("\nText: ");
        String text = scanner.nextLine();
        System.out.println("-------------------------------------------------------------------");

        int shift = getValidShift(scanner);
        System.out.println("-------------------------------------------------------------------");

        int choice = getValidChoice(scanner);
        System.out.println("-------------------------------------------------------------------");

        // Encrypt or decrypt the text
        if (choice == ENCRYPTION) {
            int mode = getValidMode(scanner);
            System.out.println("-------------------------------------------------------------------");

            // Simple mode or secured mode
            if (mode == SIMPLE_MODE) {
                String encryptedText = encryptText(addRandomParentheses(text), shift);
                System.out.println("Encrypted text: " + encryptedText);
            } else if (mode == SECURED_MODE) {
                String encryptedText = encryptText(addRandomNum(addRandomParentheses(text)), shift);
                System.out.println("Encrypted text: " + encryptedText);
            }
        } else if (choice == DECRYPTION) {
            String decryptedText = decryptText(text, shift);
            System.out.println("Decrypted text: " + decryptedText);
        }
    }

    private static int getValidShift(Scanner scanner) {
        int shift;
        System.out.println("Enter shift value.");
        System.out.println("Shift value must be 1-25.");

        while (true) {
            try {
                System.out.print("\nShift: ");
                shift = Integer.parseInt(scanner.nextLine());
                if (shift < MIN_SHIFT || shift > MAX_SHIFT) {
                    throw new IllegalArgumentException("Shift value must be between 1 and 25.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return shift;
    }

    private static int getValidMode(Scanner scanner) {
        int mode;
        System.out.println("Select mode.");
        System.out.println("[1] Simple Mode.");
        System.out.println("[2] Secured Mode.");

        while (true) {
            try {
                System.out.print("\nMode: ");
                mode = Integer.parseInt(scanner.nextLine());
                if (mode != SIMPLE_MODE && mode != SECURED_MODE) {
                    throw new IllegalArgumentException("Invalid mode. Please enter either 1 or 2.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return mode;
    }

    private static int getValidChoice(Scanner scanner) {
        int choice;
        System.out.println("Select operation:");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");

        while (true) {
            try {
                System.out.print("\nOperation: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice != ENCRYPTION && choice != DECRYPTION) {
                    throw new IllegalArgumentException("Invalid operation. Please enter either 1 or 2.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return choice;
    }

    /**
     * Decrypts the given text using the specified shift value.
     *
     * @param text  The text to decrypt.
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
                    int endIndex = text.indexOf('}', i); // Find the index of the closing curly brace
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

            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }

    /**
     * Decrypts a single letter using the specified shift value.
     *
     * @param letter The letter to decrypt.
     * @param shift  The shift value.
     * @return The decrypted letter.
     */
    private static char decryptLetter(char letter, int shift) {
        char decryptedChar = (char) (letter - shift);
        // Wrap around character in alphabet
        if (decryptedChar < 'a') {
            decryptedChar = (char) ('z' - ('a' - decryptedChar - 1) % 26);
        }
        return decryptedChar;
    }

    /**
     * Encrypts the given text using the specified shift value.
     *
     * @param text  The text to encrypt.
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
     * @param shift  The shift value.
     * @return The encrypted letter.
     */
    private static char encryptLetter(char letter, int shift) {
        // Shift the letter 
        char encryptedChar = (char) (letter + shift);

        // Wrap around character in alphabet
        if (encryptedChar > 'z') {
            encryptedChar = (char) ('a' + (encryptedChar - 'z' - 1) % 26);
        }
        return encryptedChar;
    }

    /**
     * Adds random parentheses to the given string.
     *
     * @param text The string to add parentheses to.
     * @return The modified string with random parentheses.
     */
    public static String addRandomParentheses(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        int length = words.length;
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            if (random.nextBoolean()) {
                if (i == length - 1) {
                    result.append("(").append(words[i]).append(")");
                } else {
                    result.append("(").append(words[i]).append(") ");
                }
            } else {
                if (i == length - 1) {
                    result.append(words[i]);
                } else {
                    result.append(words[i]).append(" ");
                }
            }
        }
        return result.toString();
    }

    public static String addRandomNum(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        int length = words.length;
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            if (random.nextBoolean()) {
                if (i == length - 1) {
                    result.append("&").append(random.nextInt(9) + 1).append("{").append(words[i]).append("}");
                } else
                    result.append("&").append(random.nextInt(9) + 1).append("{").append(words[i]).append("} ");
            } else {
                if (i == length - 1) {
                    result.append(words[i]).append(" ");
                } else {
                    result.append(words[i]);
                }
            }
        }
        return result.toString();
    }
}

