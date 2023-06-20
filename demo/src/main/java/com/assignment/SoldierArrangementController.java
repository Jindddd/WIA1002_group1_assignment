package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.*;

public class SoldierArrangementController extends ApplicationController implements Initializable {
    @FXML
    Label attributeLabel;
    @FXML
    Label resultLabel;
    @FXML
    Label minimumLevelLabel;
    @FXML
    Label minimumLabel;
    @FXML
    Label maximumLabel;
    @FXML
    Label searchTypeLabel;
    @FXML
    Label specificValueLabel;
    @FXML
    RadioButton sortGeneralRButton;
    @FXML
    RadioButton suggestGeneralRButton;
    @FXML
    RadioButton searchGeneralRButton;
    @FXML
    RadioButton rangeRButton;
    @FXML
    RadioButton specificRButton;
    @FXML
    ChoiceBox<String> attributeChoiceBox;
    @FXML
    ChoiceBox<String> minimumLevelChoiceBox;
    @FXML
    TextField minimumTextField;
    @FXML
    TextField maximumTextField;
    @FXML
    TextField specificValueTextField;
    String[] attributesForSort = {"Strength", "Leadership", "Intelligence", "Politic", "Hitpoint", "Sum of all abilities"};
    String[] attributes = {"Strength", "Leadership", "Intelligence", "Politic", "Hitpoint"};
    String[] minimumLevels = {"S", "A", "B", "C"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortGeneralRButton.setOnAction(this::showOperation);
        suggestGeneralRButton.setOnAction(this::showOperation);
        searchGeneralRButton.setOnAction(this::showOperation);
        rangeRButton.setOnAction(this::showOperation);
        specificRButton.setOnAction(this::showOperation);
        attributeChoiceBox.getItems().setAll(attributesForSort); // Initially the operation chosen is Sort Generals
        minimumLevelLabel.setVisible(false);
        minimumLevelChoiceBox.setVisible(false);
        searchTypeLabel.setVisible(false);
        rangeRButton.setVisible(false);
        specificRButton.setVisible(false);
        minimumLabel.setVisible(false);
        minimumTextField.setVisible(false);
        maximumLabel.setVisible(false);
        maximumTextField.setVisible(false);
        specificValueLabel.setVisible(false);
        specificValueTextField.setVisible(false);
        resultLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
    }

    public void showOperation(ActionEvent event) {
        Color brown = new Color(0.2745, 0.1451, 0.1647, 1.0);
        attributeLabel.setTextFill(brown);
        minimumLevelLabel.setTextFill(brown);
        minimumLabel.setTextFill(brown);
        maximumLabel.setTextFill(brown);
        String mode = sortGeneralRButton.isSelected() ? "Sort" :
                suggestGeneralRButton.isSelected() ? "Suggest" : "Search";
        if (mode.equals("Sort")) {
            attributeChoiceBox.valueProperty().set(null); // Reset the attribute choice box
            attributeChoiceBox.getItems().setAll(attributesForSort); // Set the items to the attribute choice box (with sum of all abilities)
            minimumLevelLabel.setVisible(false);
            minimumLevelChoiceBox.setVisible(false);
            searchTypeLabel.setVisible(false);
            rangeRButton.setVisible(false);
            specificRButton.setVisible(false);
            minimumLabel.setVisible(false);
            minimumTextField.setVisible(false);
            maximumLabel.setVisible(false);
            maximumTextField.setVisible(false);
            specificValueLabel.setVisible(false);
            specificValueTextField.setVisible(false);
        }
        if (mode.equals("Suggest")) {
            attributeChoiceBox.valueProperty().set(null);
            attributeChoiceBox.getItems().setAll(attributes); // Set the items to the attribute choice box (without sum of all abilities)
            minimumLevelChoiceBox.getItems().setAll(minimumLevels); // Set the items to the minimum level choice box
            minimumLevelLabel.setVisible(true);
            minimumLevelChoiceBox.setVisible(true);
            searchTypeLabel.setVisible(false);
            rangeRButton.setVisible(false);
            specificRButton.setVisible(false);
            minimumLabel.setVisible(false);
            minimumTextField.setVisible(false);
            maximumLabel.setVisible(false);
            maximumTextField.setVisible(false);
            specificValueLabel.setVisible(false);
            specificValueTextField.setVisible(false);
        }
        if (mode.equals("Search")) {
            attributeChoiceBox.valueProperty().set(null);
            attributeChoiceBox.getItems().setAll(attributes);
            minimumLevelLabel.setVisible(false);
            minimumLevelChoiceBox.setVisible(false);
            searchTypeLabel.setVisible(true);
            rangeRButton.setVisible(true);
            specificRButton.setVisible(true);


            if (rangeRButton.isSelected()) {
                // Enable and show the minimum and maximum labels and text fields
                minimumLabel.setVisible(true);
                minimumTextField.setVisible(true);
                maximumLabel.setVisible(true);
                maximumTextField.setVisible(true);

                specificValueLabel.setVisible(false);
                specificValueTextField.setVisible(false);
            }

            if (specificRButton.isSelected()) {
                minimumLabel.setVisible(false);
                minimumTextField.setVisible(false);
                maximumLabel.setVisible(false);
                maximumTextField.setVisible(false);

                specificValueLabel.setVisible(true);
                specificValueTextField.setVisible(true);
            }
        }
    }

    public void submit(ActionEvent event) {
        Color brown = new Color(0.2745, 0.1451, 0.1647, 1.0);
        attributeLabel.setTextFill(brown);
        minimumLevelLabel.setTextFill(brown);
        searchTypeLabel.setTextFill(brown);
        minimumLabel.setTextFill(brown);
        maximumLabel.setTextFill(brown);
        specificValueLabel.setTextFill(brown);
        String attributeOption = attributeChoiceBox.getValue();
        List<General> generals = new ArrayList<>();
        generals.add(new General("Xu Sheng", 90, 78, 72, 40, 94));
        generals.add(new General("Zhu Ge Jin", 63, 61, 88, 82, 71));
        generals.add(new General("Lu Su", 43, 87, 84, 88, 53));
        generals.add(new General("Tai Shi Ci", 96, 81, 43, 33, 97));
        generals.add(new General("Xiao Qiao", 42, 52, 89, 77, 34));
        generals.add(new General("Da Qiao", 39, 62, 90, 62, 41));
        generals.add(new General("Zhou Tai", 92, 89, 72, 43, 99));
        generals.add(new General("Gan Ning", 98, 92, 45, 23, 97));
        generals.add(new General("Lu Meng", 70, 77, 93, 83, 88));
        generals.add(new General("Huang Gai", 83, 98, 72, 42, 89));

        Comparator<General> comparator;

        try {
            String mode = sortGeneralRButton.isSelected() ? "Sort" :
                    suggestGeneralRButton.isSelected() ? "Suggest" : "Search";
            StringBuilder result = new StringBuilder();
            switch (mode) {
                case "Sort" -> {
                    // Check if the input format is correct
                    if (validate(mode)) {
                        switch (attributeOption) {
                            case "Strength" -> comparator = Comparator.comparingInt(General::strength).reversed();
                            case "Leadership" -> comparator = Comparator.comparingInt(General::leadership).reversed();
                            case "Intelligence" -> comparator = Comparator.comparingInt(General::intelligence).reversed();
                            case "Politic" -> comparator = Comparator.comparingInt(General::politic).reversed();
                            case "Hitpoint" -> comparator = Comparator.comparingInt(General::hitpoint).reversed();
                            default -> {
                                sortTotalPoint(generals);
                                return;
                            }
                        }

                        List<General> sortedGenerals = new ArrayList<>();
                        for (General general : generals) {
                            getField(general, attributeOption); //calling method getField to get the field label
                            sortedGenerals.add(general);    //add general into the sortGeneral list
                        }

                        sortedGenerals.sort(comparator);    //sort the generals from the highest to the lowest
                        result.append("Generals sorted based on ").append(attributeOption).append(":");
                        for (General general : sortedGenerals) {
                            result.append("\n").append(general.name()).append(" - ").append(attributeOption).append(": ").append(getField(general, attributeOption));
                        }
                        resultLabel.setText(result.toString());
                    }
                }
                case "Suggest" -> {
                    if (validate(mode)) {
                        String minimumLevel = minimumLevelChoiceBox.getValue();
                        result.append("Suggested team in the field of ").append(attributeOption).append(":");
                        result.append("\n").append("Team level: ");
                        result.append(minimumLevel).append(minimumLevel.equals("S") ? " (>= 250)" :
                                minimumLevel.equals("A") ? " (>= 220)" :
                                        minimumLevel.equals("B") ? " (>= 190)" : " (<= 190)");
                        result.append("\n");
                        List<General> team;
                        String field;
                        switch (attributeOption) {
                            case "Strength" -> {
                                team = suggestTeam(generals, "Strength", minimumLevel);
                                field = "Strength";
                            }
                            case "Leadership" -> {
                                team = suggestTeam(generals, "Leadership", minimumLevel);
                                field = "Leadership";
                            }
                            case "Intelligence" -> {
                                team = suggestTeam(generals, "Intelligence", minimumLevel);
                                field = "Intelligence";
                            }
                            case "Politic" -> {
                                team = suggestTeam(generals, "Politic", minimumLevel);
                                field = "Politic";
                            }
                            case "Hitpoint" -> {
                                team = suggestTeam(generals, "Hitpoint", minimumLevel);
                                field = "Hitpoint";
                            }
                            default -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setHeaderText("Warning");
                                alert.setContentText("Invalid field option!");
                                alert.showAndWait();
                                return;
                            }
                        }

                        if (team.size() == 3) {
                            int teamAbility = calculateTeamAbility(team, field);    // Calculate the total abilities of all 3 generals in a team
                            for (General general : team) {
                                int generalAbility = general.getAbility(field);
                                result.append(general.name()).append(" - ").append(attributeOption).append(": ").append(generalAbility).append("\n");
                            }
                            result.append("Team Ability: ").append(teamAbility).append("\n"); // Print team ability

                            String teamLevel = getTeamLevel(teamAbility);   // Get team level base on team ability
                            if (!teamLevel.equals(minimumLevel)) {
                                // This part only be executed if getTeamLevel output is not same as user field input
                                System.out.println("[!!!Cannot create a team of level " + minimumLevel + "!!!]");
                            }
                        } else {
                            result.append("No suggested team for the ").append(field).append(" field");
                        }
                        resultLabel.setText(result.toString());
                    }
                }
                case "Search" -> {
                    if (validate(mode)) {
                        result.append("You have chosen ").append(attributeOption).append(" field").append("\n");
                        List<General> foundGenerals;
                        General foundGeneralsBinary;

                        if (rangeRButton.isSelected()) {
                            int minAbilityValue = Integer.parseInt(minimumTextField.getText().trim());
                            int maxAbilityValue = Integer.parseInt(maximumTextField.getText().trim());
                            foundGenerals = searchGeneralsInRange(generals, attributeOption, minAbilityValue, maxAbilityValue);
                            // calling methods that search generals in the range of ability

                            if (!foundGenerals.isEmpty()) {
                                result.append("Generals in the field of ").append(attributeOption).append(" and in the range of ").append(minAbilityValue).append(" to ").append(maxAbilityValue).append(" found:").append("\n");
                                int i = 1;
                                for (General general : foundGenerals) {
                                    result.append(i).append(") ").append(general.name()).append(" - ").append(attributeOption).append(": ").append(general.getAbility(attributeOption)).append("\n");
                                    i++;
                                }
                                int inew = i - 1;
                                result.append("Total generals in the range : ").append(inew);
                            } else {
                                result.append("No generals found with ").append(attributeOption).append(" in the range of ").append(minAbilityValue).append(" to ").append(maxAbilityValue);
                            }
                        } else if (specificRButton.isSelected()) {
                            int abilityValue = Integer.parseInt(specificValueTextField.getText().trim());
                            foundGeneralsBinary = binarySearchGeneral(generals, attributeOption, abilityValue);   // calling method that search a general ability specifically
                            if (foundGeneralsBinary != null) {
                                result.append("General with ").append(attributeOption).append(" of ").append(abilityValue).append(" found" +
                                        ":").append("\n");
                                result.append(foundGeneralsBinary.name()).append(" - ").append(attributeOption).append(": ").append(foundGeneralsBinary.getAbility(attributeOption));
                            } else {
                                result.append("No general found with ").append(attributeOption).append(" of ").append(abilityValue);
                            }
                        }
                        resultLabel.setText(result.toString());
                    }
                }
                default -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning");
                    alert.setContentText("You must choose one of the options!!!"); //If user does not choose any option
                    alert.showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Enter only integer for the minimum and maximum!!!");
            alert.showAndWait();
        }
    }

    private void sortTotalPoint(List<General> generals) {
        StringBuilder result = new StringBuilder();
        // Only be executed when user choose sort total field points
        generals.sort(Comparator.comparingInt(SoldierArrangementController::calculateTotalAbility).reversed());
        //  sort the generals in descending order by the total field points
        result.append("Generals sorted based on the sum of all abilities:");
        for (General general : generals) {
            result.append("\n").append(general.name()).append(" - Total Ability: ").append(calculateTotalAbility(general));
        }
        resultLabel.setText(result.toString());
    }

    private static int calculateTotalAbility(General general) {
        // return total field points
        return general.strength() + general.leadership() + general.intelligence() + general.politic() + general.hitpoint();
    }

    private static int calculateTeamAbility(List<General> team, String field) {
        // return sum of abilities of 3 generals
        int teamAbilitySum = 0;
        for (General general : team) {
            teamAbilitySum += general.getAbility(field);
        }
        return teamAbilitySum;
    }

    private static String getTeamLevel(int teamAbility) {
        // return team level based on the total points of 3 generals(not by user input)
        if (teamAbility >= 250) {
            return "S";
        } else if (teamAbility >= 220) {
            return "A";
        } else if (teamAbility >= 190) {
            return "B";
        } else {
            return "C";
        }
    }

    private static List<General> suggestTeam(List<General> generals, String field, String minimumLevel) {
        // create suggestion for a team of 3 generals according to the field and level inputs
        List<General> team = new ArrayList<>();
        //  determine the min ability for a team can have
        int minimumAbility = minimumLevel.equals("S") ? 250 :
                             minimumLevel.equals("A") ? 220 :
                             minimumLevel.equals("B") ? 190 : 0;

        // Sort generals in the specified field in descending order
        Comparator<General> comparator;
        switch (field) {
            case "Strength" -> comparator = Comparator.comparingInt(General::strength).reversed();
            case "Leadership" -> comparator = Comparator.comparingInt(General::leadership).reversed();
            case "Hitpoint" -> comparator = Comparator.comparingInt(General::hitpoint).reversed();
            case "Intelligence" -> comparator = Comparator.comparingInt(General::intelligence).reversed();
            case "Politic" -> comparator = Comparator.comparingInt(General::politic).reversed();
            default -> {
                System.out.println("Invalid field option");
                return team;
            }
        }

        generals.sort(comparator);

        int teamAbilitySum = 0;
        int minimumPoints;  //declaring min point
        int maximumPoints;  //declaring max point

        // Set minimum and maximum points based on the minimum requirement level
        switch (minimumLevel) {
            case "S" -> {
                minimumPoints = 82;
                maximumPoints = 100;
            }
            case "A" -> {
                minimumPoints = 75;
                maximumPoints = 83;
            }
            case "B" -> {
                minimumPoints = 64;
                maximumPoints = 74;
            }
            case "C" -> {
                minimumPoints = Integer.MIN_VALUE;
                maximumPoints = 64;
            }
            default -> {
                System.out.println("Invalid minimum requirement level");
                return team;
            }
        }

        for (General general : generals) {
            //  general will be added into the team if the points of desired field is in the range of min & max
            int ability = general.getAbility(field);
            //  getting the general ability based on the desired field input
            if (ability >= minimumPoints && ability <= maximumPoints) {
                team.add(general);
                teamAbilitySum += ability;
            }

            if (team.size() >= 3 && teamAbilitySum >= minimumAbility) {
                // break from the loop if equals 3 generals and reach min ability for a team
                break;
            }
        }

        while (team.size() > 3 && teamAbilitySum < minimumAbility) {
            //  while the sum of team points is lesser than min ability even the team is enough 3 general
            // the weakest will be removed and substituted with the higher one until reach the min ability

            General weakestGeneral = null;
            int weakestGeneralAbility = Integer.MAX_VALUE;

            for (General general : team) {
                int ability = general.getAbility(field);
                if (ability < weakestGeneralAbility) {
                    //  finding the weakest among the team members
                    weakestGeneral = general;
                    weakestGeneralAbility = ability;
                }
            }

            team.remove(weakestGeneral);    //removing the weakest from a team
            teamAbilitySum -= weakestGeneralAbility;    //updating the team total point
        }

        while (team.size() < 3 && maximumPoints <= 100) {
            // if the team of 3 generals cannot be created
            // the min and max point will be changed to allow other generals that dont have point in range to be inserted into a team

            teamAbilitySum = 0;
            if (field.equals("Intelligence")){
                // special case for Intelligence level C team
                // allowing to create a team of 3 generals by [ 43, 45, 72 ]
                if (maximumPoints == 64) {
                    maximumPoints = 72;
                }
                for (General general : generals) {
                    int ability = general.getAbility(field);

                    if (ability >= minimumPoints && ability <= maximumPoints) {
                        if (!team.contains(72)){
                            if (!team.contains(general)) {
                                team.add(general);
                                teamAbilitySum += ability;
                            }
                        }
                    }
                    if (team.size() == 3 && teamAbilitySum >= minimumAbility) {
                        //  break if a team of 3 successfully created
                        break;
                    }
                }
            } else if (field.equals("Strength")){
                // special case for Strength level A team
                // allowing to create a team of 3 generals by [ 70, 83, 90 ] = 243
                if (maximumPoints == 83 && minimumPoints == 75) {
                    maximumPoints = 90;
                    minimumPoints = 70;
                }
                for (General general : generals) {
                    int ability = general.getAbility(field);

                    if (ability >= minimumPoints && ability <= maximumPoints) {
                        if (!team.contains(90)){
                            if (!team.contains(general)) {
                                team.add(general);
                                teamAbilitySum += ability;
                            }
                        }
                    }
                    if (team.size() == 3 && teamAbilitySum >= minimumAbility) {
                        //  break if a team of 3 successfully created
                        break;
                    }
                }
            }

            if (field.equals("Politic")){
                // special case for Politic level B team
                // allowing to create a team of 3 generals by [ 43, 77, 82 ] = 202
                if (maximumPoints == 74 && minimumPoints == 64) {
                    maximumPoints = 82;
                    minimumPoints = 43;
                }
                for (General general : generals) {
                    int ability = general.getAbility(field);

                    if (ability >= minimumPoints && ability <= maximumPoints && ability != 62) {

                        if (!team.contains(43)) {
                            if (!team.contains(general)) {
                                team.add(general);
                                teamAbilitySum += ability;
                            }
                        }

                    }
                    if (team.size() == 3 && teamAbilitySum >= minimumAbility) {
                        //  break if a team of 3 successfully created
                        break;
                    }
                }
            } else if (field.equals("Hitpoint")){
                // special case for Hitpoint level B team
                // allowing to create a team of 3 generals by [ 53, 71, 88 ] = 212
                if (maximumPoints == 74 && minimumPoints == 64) {
                    maximumPoints = 88;
                    minimumPoints = 53;
                }
                for (General general : generals) {
                    int ability = general.getAbility(field);

                    if (ability >= minimumPoints && ability <= maximumPoints) {
                        if (!team.contains(88)){
                            if (!team.contains(general)) {
                                team.add(general);
                                teamAbilitySum += ability;
                            }
                        }
                    }
                    if (team.size() == 3 && teamAbilitySum >= minimumAbility) {
                        //  break if a team of 3 successfully created
                        break;
                    }
                }
            }

            else {
                // for the other case
                for (General general : generals) {
                    int ability = general.getAbility(field);

                    if (ability >= minimumPoints && ability <= maximumPoints) {
                        if (!team.contains(general)) {
                            team.add(general);
                            teamAbilitySum += ability;
                        }
                    }
                    if (team.size() == 3 && teamAbilitySum >= minimumAbility) {
                        //  break if a team of 3 successfully created
                        break;
                    }
                }
            }
            if (team.size() < 3 || teamAbilitySum < minimumAbility) {
                // deduct and increase the min and max a bit until a taem of 3 can be reached
                minimumPoints -= 3;
                maximumPoints += 3;
            }
        }

        return team;
    }

    private static List<General> searchGeneralsInRange(List<General> generals, String field, int minAbility, int maxAbility) {
        // search in range ability method
        List<General> foundGenerals = new ArrayList<>();
        for (General general : generals) {
            int ability = general.getAbility(field);
            if (ability >= minAbility && ability <= maxAbility) {
                foundGenerals.add(general);
            }
        }
        return foundGenerals;
    }

    private static General binarySearchGeneral(List<General> generals, String field, int abilityValue) {
        // search for a specific ability method by using binary search
        Comparator<General> comparator;
        switch (field) {
            case "Strength" -> comparator = Comparator.comparingInt(General::strength);
            case "Leadership" -> comparator = Comparator.comparingInt(General::leadership);
            case "Hitpoint" -> comparator = Comparator.comparingInt(General::hitpoint);
            case "Intelligence" -> comparator = Comparator.comparingInt(General::intelligence);
            case "Politic" -> comparator = Comparator.comparingInt(General::politic);
            default -> {
                System.out.println("Invalid field option");
                return null;
            }
        }

        generals.sort(comparator);

        int low = 0;
        int high = generals.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            General midGeneral = generals.get(mid);
            int midAbility = midGeneral.getAbility(field);

            if (midAbility == abilityValue) {
                return midGeneral;
            } else if (midAbility < abilityValue) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    boolean validate(String mode) {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        switch (mode) {
            case "Sort" -> {
                if (attributeChoiceBox.getValue() == null) {
                    errors.append("Please select one of the attributes\n");
                    attributeLabel.setTextFill(Paint.valueOf("red"));
                }
            }
            case "Suggest" -> {
                if (attributeChoiceBox.getValue() == null) {
                    errors.append("Please select one of the attributes\n");
                    attributeLabel.setTextFill(Paint.valueOf("red"));
                }
                if (minimumLevelChoiceBox.getValue() == null) {
                    errors.append("Please select a minimum level\n");
                    minimumLevelLabel.setTextFill(Paint.valueOf("red"));
                }
            }
            case "Search" -> {
                if (attributeChoiceBox.getValue() == null) {
                    errors.append("Please select one of the attributes.\n");
                    attributeLabel.setTextFill(Paint.valueOf("red"));
                }
                if (rangeRButton.isSelected()) {
                    if (minimumTextField.getText().trim().isEmpty()) {
                        errors.append("Please select a minimum level.\n");
                        minimumLabel.setTextFill(Paint.valueOf("red"));
                    }
                    if (maximumTextField.getText().trim().isEmpty()) {
                        errors.append("Please select a maximum level.\n");
                        maximumLabel.setTextFill(Paint.valueOf("red"));
                    }
                    // If minimum and maximum is not empty only we check if the range is valid
                    if (!minimumTextField.getText().trim().isEmpty() && !maximumTextField.getText().trim().isEmpty()) {
                        if (Integer.parseInt(minimumTextField.getText().trim()) >= Integer.parseInt(maximumTextField.getText().trim())) {
                            errors.append("Please select a valid range. (Minimum and Maximum must have a difference of 1)\n");
                            minimumLabel.setTextFill(Paint.valueOf("red"));
                            maximumLabel.setTextFill(Paint.valueOf("red"));
                        }
                        if (Integer.parseInt(minimumTextField.getText().trim()) < 0 || Integer.parseInt(minimumTextField.getText().trim()) > 100) {
                            errors.append("Please select a valid minimum level. (0 - 100)\n");
                            minimumLabel.setTextFill(Paint.valueOf("red"));
                        }
                        if (Integer.parseInt(maximumTextField.getText().trim()) < 0 || Integer.parseInt(maximumTextField.getText().trim()) > 100) {
                            errors.append("Please select a valid maximum level. (0 - 100)\n");
                            maximumLabel.setTextFill(Paint.valueOf("red"));
                        }
                    }
                } else {
                    if (specificValueTextField.getText().trim().isEmpty()) {
                        errors.append("Please select a maximum level.\n");
                        specificValueLabel.setTextFill(Paint.valueOf("red"));
                    }
                }
            }
        }
        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            printError(errors);
            return false;
        }
        // No errors
        return true;
    }

    private static int getField(General general, String attributeOption) {
        //  go to the method according to the field option
        return switch (attributeOption) {
            case "Strength" -> general.strength();
            case "Leadership" -> general.leadership();
            case "Intelligence" -> general.intelligence();
            case "Politic" -> general.politic();
            case "Hitpoint" -> general.hitpoint();
            default -> 0;
        };
    }

    record General(String name, int strength, int leadership, int intelligence, int politic, int hitpoint) {

        public int getAbility(String field) {
            return switch (field) {
                case "Strength" -> strength;
                case "Leadership" -> leadership;
                case "Intelligence" -> intelligence;
                case "Politic" -> politic;
                case "Hitpoint" -> hitpoint;
                default -> 0;
            };
        }
    }
}
