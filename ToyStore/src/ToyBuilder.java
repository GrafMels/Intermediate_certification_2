abstract class ToyBuilder {
    protected int id;
    protected String name;
    protected int amount;
    protected int dropChance;
    protected boolean ageGroup;
    protected boolean gameGroup;

    protected ToyBuilder() {
    }

    public ToyBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ToyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ToyBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ToyBuilder setDropChance(int dropChance) {
        this.dropChance = dropChance;
        return this;
    }

    public ToyBuilder setAgeGroup(boolean ageGroup) {
        this.ageGroup = ageGroup;
        return this;
    }

    public ToyBuilder setGameGroup(boolean gameGroup) {
        this.gameGroup = gameGroup;
        return this;
    }
}
