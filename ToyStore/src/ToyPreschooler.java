public class ToyPreschooler extends Toy {

    public static PreschoolerBuilder newBuilder() {
        return new ToyPreschooler().new PreschoolerBuilder();
    }

    public class PreschoolerBuilder extends ToyBuilder {
        public ToyPreschooler build() {
            ToyPreschooler toy = new ToyPreschooler();
            toy.setId(this.id);
            toy.setName(this.name);
            toy.setAmount(this.amount);
            toy.setDropChance(this.dropChance);
            toy.setAgeGroup(this.ageGroup);
            toy.setGameGroup(this.gameGroup);
            return toy;
        }

    }
}
