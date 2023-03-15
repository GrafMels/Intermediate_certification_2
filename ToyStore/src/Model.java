import java.util.ArrayList;
import java.util.Random;

public class Model {
    public static Toy createToy(boolean game, boolean age, int id, String name, int amount, int dropChance) {
        if (age) {
            ToySchoolboy toy = ((ToySchoolboy.SchoolboyBuilder) ToySchoolboy.newBuilder()
                    .setId(id)
                    .setName(name)
                    .setAgeGroup(age)
                    .setAmount(amount)
                    .setGameGroup(game)
                    .setDropChance(dropChance))
                    .build();
            // View.saveToy("ToySchoolBoy", name, toy);
            return toy;
        } else {
            ToyPreschooler toy = ((ToyPreschooler.PreschoolerBuilder) ToyPreschooler.newBuilder()
                    .setId(id)
                    .setName(name)
                    .setAgeGroup(age)
                    .setAmount(amount)
                    .setGameGroup(game)
                    .setDropChance(dropChance))
                    .build();
            // View.saveToy("ToyPreschooler", name, toy);
            return toy;
        }

    }

    public static void putInMap(Toy toy) {
        boolean inOrOff = toy.isGameGroup();
        if (inOrOff) {
            toy.setId(ToyCollections.getIdGame());
            ToyCollections.inGame.add(toy);
            ToyCollections.plusminusIdGame(true);
        } else {
            toy.setId(ToyCollections.getId());
            ToyCollections.allToy.add(ToyCollections.getId(), toy);
            ToyCollections.plusminusId(true);
        }
    }

    public static void startSort(ArrayList<Toy> toyList) {

        for (Toy toy : toyList) {
            putInMap(toy);
        }
    }

    public static int game() {
        ArrayList<Integer> listToySum = new ArrayList<Integer>();
        int toySum = 0;
        int toyPlus = 0;
        for (Toy toy : ToyCollections.inGame) {
            toySum = toySum + toy.getAmount() * toy.getDropChance();
            listToySum.add(toySum);
        }
        Random random = new Random();
        int randToy = random.nextInt(toySum + 1);
        for (int i = 0; i < listToySum.size(); i++) {
            toyPlus = listToySum.get(i);
            if (toyPlus > randToy) {
                return i;
            }
        }
        return ToyCollections.inGame.size() - 1;

    }

    public static void waitSec() {
        try {
            Thread.sleep(4000);
        } catch (Exception IOException) {
            // TODO: handle exception
        }

    }

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception InterruptedException) {
            // TODO: handle exception
        }
    }
}
