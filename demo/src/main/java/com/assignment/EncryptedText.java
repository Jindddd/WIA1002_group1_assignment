package com.assignment;

import java.util.*;

public class EncryptedText {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Text: ");
        String text = scanner.nextLine();

        System.out.print("Shift: ");
        int shift = scanner.nextInt();

        System.out.print("Encrypt / Decrypt: ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("encrypt")) {
            String textAddedParentheses = addRandomParentheses(text);
            System.out.println(encryptText(textAddedParentheses, shift));

        } else if (choice.equalsIgnoreCase("decrypt")) {
            System.out.println(decryptText(text, shift));

        }
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

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '^') {
                // Decrypt the next letter and convert to uppercase
                char decryptedChar = decryptLetter(text.charAt(i + 1), shift);
                decryptedText.append(Character.toUpperCase(decryptedChar));

                // Skip both '^' and the decrypted letter
                i++;

            } else if (c == '$') {
                // Replace with space
                decryptedText.append(' ');

            } else if (c == '(') {
                // Extract the substring to be inverted
                int endIndex = text.indexOf(')', i);
                String toBeInverted = text.substring(i + 1, endIndex);

                // Reverse the substring and decrypt 
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
                // Append any other characters as they are
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

            if (Character.isUpperCase(c)) {
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

                // Extract the substring to be encrypted
                String toBeEncrypted = text.substring(i + 1, endIndex);

                // Encrypt the substring
                String encrypted = encryptText(toBeEncrypted, shift);

                // Reverse the substring
                String inverted = new StringBuilder(encrypted).reverse().toString();

                // Append the substring and closing parenthesis
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

        // Wrap around to 'a' if the shifed letter exceeds 'z'
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
}
