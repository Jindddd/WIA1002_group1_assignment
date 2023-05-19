public class General {
    private final String name;
    private final int intelligence;
    private final int strength;

    public General(String name, int intelligence, int strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }
}
