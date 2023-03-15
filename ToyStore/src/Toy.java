public abstract class Toy implements ToyFunc {

    private int id = 0;
    private String name = "";
    private int amount = 0;
    private int dropChance = 20;
    private boolean ageGroup = true;
    private boolean gameGroup = true;

    public boolean isAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(boolean ageGroup) {
        this.ageGroup = ageGroup;
    }

    public boolean isGameGroup() {
        return gameGroup;
    }

    public void setGameGroup(boolean gameGroup) {
        this.gameGroup = gameGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDropChance() {
        return dropChance;
    }

    public void setDropChance(int drop_chance) {
        this.dropChance = drop_chance;
    }

    public void minusAmount() {
        this.amount = this.amount - 1;
    }

    public void plusAmount() {
        this.amount = this.amount + 1;
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %d", id, name, amount, dropChance);
    }

    public String toFile() {
        if (gameGroup) {
            if (ageGroup) {
                return String.format("{\n\t\"id\": %d,\n\t\"amount\": %d,\n\t\"dropChance\": %d,\n\t\"age\": %s\n}",
                        id, amount, dropChance, true);
            } else {
                return String.format("{\n\t\"id\": %d,\n\t\"amount\": %d,\n\t\"dropChance\": %d,\n\t\"age\": %s\n}",
                        id, amount, dropChance, false);
            }

        } else {
            return String.format("{\n\t\"id\": %d,\n\t\"amount\": %d,\n\t\"dropChance\": %d\n}", id, amount,
                    dropChance);
        }
    }
}
