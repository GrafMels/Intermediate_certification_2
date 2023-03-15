import java.util.LinkedList;

public class ToyCollections {
    static public LinkedList<Toy> inGame = new LinkedList<Toy>();

    private static int idGame = 0;

    static public LinkedList<Toy> allToy = new LinkedList<Toy>();

    private static int id = 0;

    public static int getId() {
        return id;
    }

    public static void plusminusId(boolean operation) {
        if (operation) {
            id += 1;
        } else {
            id -= 1;
        }
    }

    public static int getIdGame() {
        return idGame;
    }

    public static void plusminusIdGame(boolean operation) {
        if (operation) {
            idGame += 1;
        } else {
            idGame -= 1;
        }
    }

    public void inGame(Toy toy) {

    }

    public void offside(Toy toy) {

    }

    public static String ToStringAll() {
        String out = "";
        for (int i = 0; i < allToy.size(); i++) {
            out = out + allToy.get(i) + "\n";
        }
        return out;
    }

    public static String ToStringGame() {
        String out = "";
        for (int i = 0; i < inGame.size(); i++) {
            out = out + inGame.get(i) + "\n";
        }
        return out;
    }

    public static int addToPlayable(int id) {
        for (int j = 0; j < inGame.size(); j++) {
            if (inGame.get(j).getName().equals(allToy.get(id).getName())) {
                inGame.get(j).plusAmount();
                allToy = minusToy(allToy, id);
                if (allToy.get(id).getAmount() < 1) {
                    allToy = removeToy(allToy, id);
                    plusminusId(false);
                }
                return 10;
            }
        }
        allToy.get(id).minusAmount();
        Toy toy = allToy.get(id);
        Model.putInMap(Model.createToy(true, toy.isAgeGroup(), idGame, toy.getName(), 1, toy.getDropChance()));
        if (allToy.get(id).getAmount() < 1) {
            allToy = removeToy(allToy, id);
            plusminusId(false);
        }
        return 10;
    }

    public static int removeToPlayable(int id) {
        for (int j = 0; j < allToy.size(); j++) {
            if (inGame.get(j).getName().equals(allToy.get(id).getName())) {
                allToy.get(j).plusAmount();
                inGame = minusToy(inGame, id);
                if (inGame.get(id).getAmount() < 1) {
                    inGame = removeToy(inGame, id);
                    plusminusIdGame(false);
                }
                return 10;
            }
        }
        Toy toy = inGame.get(id);
        Model.putInMap(Model.createToy(false, toy.isAgeGroup(), getId(), toy.getName(), 1, toy.getDropChance()));
        if (inGame.get(id).getAmount() < 1) {
            inGame = removeToy(inGame, id);
            plusminusIdGame(false);
        }
        inGame = minusToy(inGame, id);
        return 10;
    }

    public static LinkedList<Toy> minusToy(LinkedList<Toy> list, int id) {
        list.get(id).minusAmount();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i);
        }
        return list;
    }

    public static LinkedList<Toy> removeToy(LinkedList<Toy> list, int id) {
        list.remove(id);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i);
        }
        return list;
    }
}
