package com.assignment;

import java.util.*;

public class SoldierArrangement{
    public static void main(String[] args) {
        List<General> generals = new ArrayList<>();
        generals.add(new General("Sun Quan", 96, 98, 72, 77, 95));
        generals.add(new General("Zhang Zhao", 22, 80, 89, 99, 60));
        generals.add(new General("Zhou Yu", 80, 86, 97, 80, 90));
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

        Scanner scanner = new Scanner(System.in);

        System.out.println("1) Sort generals based on ability");
        System.out.println("2) Create a team of 3 generals in a specific field");
        System.out.println("3) Search for a general with a specific ability");
        System.out.print("Choose an option [ Enter -1 to exit ] : ");
        int option = scanner.nextInt();
        while (option >= 1 && option <= 3) {

            switch (option) {
                case 1 -> sortGenerals(generals);
                case 2 -> createTeam(generals, scanner);
                case 3 -> searchGeneral(generals, scanner);
                default -> System.out.println("Invalid option");
            }
            System.out.println("---------------------------------------------------");
            System.out.println("1) Sort generals based on ability");
            System.out.println("2) Create a team of 3 generals in a specific field");
            System.out.println("3) Search for a general with a specific ability");
            System.out.print("Choose an option [ Enter -1 to exit ] :");
            option = scanner.nextInt();
        }
        System.out.println("You wished to exit,Thank You.");
    }

    private static void sortGenerals(List<General> generals) {
        // sort generals method
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------");
        System.out.println("1) Strength");
        System.out.println("2) Leadership");
        System.out.println("3) Intelligence");
        System.out.println("4) Politic");
        System.out.println("5) Hitpoint");
        System.out.println("6) Sum of all fields");
        System.out.print("Choose a field to sort : ");
        int fieldOption = scanner.nextInt();

        Comparator<General> comparator = null;
        // declaring comparator 

        switch (fieldOption) {
            case 1 -> comparator = Comparator.comparingInt(General::strength).reversed();
            case 2 -> comparator = Comparator.comparingInt(General::leadership).reversed();
            case 3 -> comparator = Comparator.comparingInt(General::intelligence).reversed();
            case 4 -> comparator = Comparator.comparingInt(General::politic).reversed();
            case 5 -> comparator = Comparator.comparingInt(General::hitpoint).reversed();
            case 6 -> sortTotalPoint(generals);
            default -> {
                System.out.println("Invalid field option");
                return;
            }
        }

            if (fieldOption != 6) {     
               //this part will be executed when user enter field option other than 6
                System.out.println("You have chosen " + getFieldLabel(fieldOption) + " field");
                List<General> sortedGenerals = new ArrayList<>();
                for (General general : generals) {
                    getField(general, fieldOption); //calling method getField to get the field label
                    sortedGenerals.add(general);    //add general into the sortGeneral list
                }

                sortedGenerals.sort(comparator);    //sort the generals from the highest to the lowest
                System.out.println("---------------------------------------------------");
                System.out.println("Generals sorted based on " + getFieldLabel(fieldOption) + ":");
                for (General general : sortedGenerals) {
                    System.out.println(general.name() + " - " + getFieldLabel(fieldOption) + ": " + getField(general, fieldOption));
                }
            }
    }

    private static String getFieldLabel(int fieldOption) {
        // return field label
        return switch (fieldOption) {
            case 1 -> "Strength";
            case 2 -> "Leadership";
            case 3 -> "Intelligence";
            case 4 -> "Politic";
            case 5 -> "Hitpoint";
            default -> "";
        };
    }

    private static int getField(General general, int fieldOption) {
        //  go to the method according to the field option
        return switch (fieldOption) {
            case 1 -> general.strength();
            case 2 -> general.leadership();
            case 3 -> general.intelligence();
            case 4 -> general.politic();
            case 5 -> general.hitpoint();
            default -> 0;
        };
    }

    private static void sortTotalPoint(List<General> generals) {
        // Only be executed when user choose sort total field points
        generals.sort(Comparator.comparingInt(SoldierArrangement::calculateTotalAbility).reversed());
        //  sort the generals in descending order by the total field points
        System.out.println("---------------------------------------------------");
        System.out.println("Generals sorted based on the sum of abilities:");
        for (General general : generals) {
            System.out.println(general.name() + " - Total Ability: " + calculateTotalAbility(general));
        }
    }

    private static int calculateTotalAbility(General general) {
        // return total field points
        return general.strength() + general.leadership() + general.intelligence() + general.politic() + general.hitpoint();
    }

    private static void createTeam(List<General> generals, Scanner scanner) {
        // method for determining the field and level inputs before creates a team of 3 generals 
        System.out.println("---------------------------------------------------");
        System.out.println("1) Strength");
        System.out.println("2) Leadership");
        System.out.println("3) Intelligence");
        System.out.println("4) Politic");
        System.out.println("5) Hitpoint");
        System.out.print("Choose a field : ");
        int fieldOption = scanner.nextInt();
        System.out.println("You have choose " + getFieldLabel(fieldOption) + " field");

        System.out.print("Choose a minimum requirement level (S, A, B, C): ");
        String minimumLevel = scanner.next();

        List<General> team;
        String field;

        switch (fieldOption) {
            case 1 -> {
                team = suggestTeam(generals, "Strength", minimumLevel);
                field = "Strength";
            }
            case 2 -> {
                team = suggestTeam(generals, "Leadership", minimumLevel);
                field = "Leadership";
            }
            case 3 -> {
                team = suggestTeam(generals, "Intelligence", minimumLevel);
                field = "Intelligence";
            }
            case 4 -> {
                team = suggestTeam(generals, "Politic", minimumLevel);
                field = "Politic";
            }
            case 5 -> {
                team = suggestTeam(generals, "Hitpoint", minimumLevel);
                field = "Hitpoint";
            }
            default -> {
                System.out.println("Invalid field option");
                return;
            }
        }
        System.out.println("---------------------------------------------------");
        if (team.size() == 3) {
            int teamAbility = calculateTeamAbility(team, field);    //calculate the total abilities of all 3 generals in a team
            System.out.println("Suggested team in the field of " + field + ":");
            for (General general : team) {
                int generalAbility = general.getAbility(field);
                System.out.println(general.name() + " - " + getFieldLabel(fieldOption) + ": " + generalAbility);
            }
            System.out.println("Team Ability: " + teamAbility); //print team ability

            String teamLevel = getTeamLevel(teamAbility);   //get team level base on team ability
            if (!teamLevel.equals(minimumLevel)) {
                //  this part only be executed if getTeamLevel output is not same as user field input
                System.out.println("[!!!Cannot create a team of level " + minimumLevel + "!!!]");
            }
            System.out.println("Team level: " + teamLevel);
        } 
        else {
            System.out.println("No suggested team for the " + field + " field");
        }
    }
    
    private static List<General> suggestTeam(List<General> generals, String field, String minimumLevel) {
        // create suggestion for a team of 3 generals according to the field and level inputs
        List<General> team = new ArrayList<>();
        int minimumAbility = getMinimumAbility(minimumLevel);
        //  determine the min ability for a team can have 

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
                System.out.println("Minimum requirement of S : >= 250 ");
                minimumPoints = 82;
                maximumPoints = 100;
            }
            case "A" -> {
                System.out.println("Minimum requirement of A : >= 220 & < 250");
                minimumPoints = 75;
                maximumPoints = 83;
            }
            case "B" -> {
                System.out.println("Minimum requirement of B : >= 190 & < 220");
                minimumPoints = 64;
                maximumPoints = 74;
            }
            case "C" -> {
                System.out.println("Minimum requirement of C : < 190 ");
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

    private static void searchGeneral(List<General> generals, Scanner scanner) {
        // method to determine which general(s) that wanted to be searched
        // determine the field
        // determine either search in range or specific
        System.out.println("---------------------------------------------------");
        System.out.println("1) Strength");
        System.out.println("2) Leadership");
        System.out.println("3) Intelligence");
        System.out.println("4) Politic");
        System.out.println("5) Hitpoint");
        System.out.print("Choose a field : ");
        int fieldOption = scanner.nextInt();
        System.out.println("----------------------------------------------------");
        System.out.println("You have chosen " + getFieldLabel(fieldOption) + " field");
        System.out.println("1) search general(s) in range [0-100] ");
        System.out.println("2) search a general by a specific value");
        System.out.print("Choose 1 or 2 : ");
        int cho = scanner.nextInt();

        List<General> foundGenerals;
        General foundGeneralsBinary;
        String field = getFieldLabel(fieldOption);

        if (cho == 1) {
            
            System.out.print("Enter the minimum ability value [0-100] : "); //  getting min range
            int minAbilityValue = scanner.nextInt();
            System.out.print("Enter the maximum ability value [0-100] : "); //  getting max range
            int maxAbilityValue = scanner.nextInt();
            foundGenerals = searchGeneralsInRange(generals, field, minAbilityValue, maxAbilityValue);   // calling methos that search generals in the range of ability

            if (!foundGenerals.isEmpty()) {
                System.out.println("Generals in the field of " + field + " and in the range of " + minAbilityValue + " to " + maxAbilityValue + " found:");
                int i = 1;
                for (General general : foundGenerals) {
                    System.out.println(i + ") " + general.name() + " - " + getFieldLabel(fieldOption) + ": " + general.getAbility(field));
                    i++;
                }
                int inew = i - 1;
                System.out.println("Total generals in the range : " + inew);
            } else {
                System.out.println("No generals found with " + field + " in the range of " + minAbilityValue + " to " + maxAbilityValue);
            }
        }
        if (cho == 2) {
            
            System.out.print("Enter a specific value [0-100] :");   // getting a specific ability value
            int abilityValue = scanner.nextInt();
            foundGeneralsBinary = binarySearchGeneral(generals, field, abilityValue);   // calling method that search a general ability specifically 
            if (foundGeneralsBinary != null) {
                System.out.println("General with " + field + " of " + abilityValue + " found:");
                System.out.println(foundGeneralsBinary.name() + " - " + getFieldLabel(fieldOption) + ": " + foundGeneralsBinary.getAbility(field));
            } else {
                System.out.println("No general found with " + field + " of " + abilityValue);
            }
        }
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
    
    private static int calculateTeamAbility(List<General> team, String field) {
        // return sum of abilities of 3 generals
        int teamAbilitySum = 0;
        for (General general : team) {
            teamAbilitySum += general.getAbility(field);
        }
        return teamAbilitySum;
    }
    
    
    private static int getMinimumAbility(String minimumAbility) {
        // return minimum ability for each level
        int min;
        switch (minimumAbility) {
            case "S" -> min = 250;
            case "A" -> min = 220;
            case "B" -> min = 190;
            default -> {
                return 0;
            }
        }
        return min;
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
