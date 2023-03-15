public class ToySchoolboy extends Toy {
    public static SchoolboyBuilder newBuilder() {
        return new ToySchoolboy().new SchoolboyBuilder();
    }

    public class SchoolboyBuilder extends ToyBuilder {
        public ToySchoolboy build() {
            ToySchoolboy toy = new ToySchoolboy();
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
