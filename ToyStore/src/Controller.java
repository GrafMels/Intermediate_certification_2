public class Controller {
    public static boolean answer2 = true;

    public static void start() {
        Model.clear();
        Model.startSort(View.readAll(View.folderName));
        Model.startSort(View.readAll(View.folderNameSchool));
        Model.startSort(View.readAll(View.folderNameInGame));
        // System.out.println(ToyCollections.ToStringAll());
        int answer1 = 1;
        while (answer1 != 0) {
            Model.clear();
            answer1 = View.menuFirst();
            if (answer1 == 1) {
                if (!ToyCollections.inGame.isEmpty()) {
                    Model.clear();
                    int number = Model.game();
                    System.out.println(String.format("Победитель получает игрушку \"%s\"",
                            ToyCollections.inGame.get(number).getName()));
                    if (ToyCollections.inGame.get(number).getAmount() == 0) {
                        ToyCollections.inGame = ToyCollections.removeToy(ToyCollections.inGame, number);
                    } else {
                        ToyCollections.inGame = ToyCollections.minusToy(ToyCollections.inGame, number);
                    }
                    Model.waitSec();
                } else {
                    Eror.emptyList();
                }

            } else if (answer1 == 2) {
                if (!ToyCollections.allToy.isEmpty()) {
                    Model.clear();
                    ToyCollections.addToPlayable(View.choiceToy(false));
                } else {
                    Eror.emptyList();
                }

            } else if (answer1 == 3) {
                if (!ToyCollections.inGame.isEmpty()) {
                    ToyCollections.removeToPlayable(View.choiceToy(true));
                } else {
                    Eror.emptyList();
                }

            } else if (answer1 == 4) {
                Model.clear();
                ToyCollections.allToy.get(View.choiceToy(false)).setAmount(View.newAmount());

            } else if (answer1 == 5) {
                Model.clear();
                System.out.println(ToyCollections.ToStringAll());
                Model.waitSec();

            } else if (answer1 == 6) {
                Model.clear();
                System.out.println(ToyCollections.ToStringGame());
                Model.waitSec();

            } else if (answer1 == 7) {
                while (answer2) {
                    Model.clear();
                    Toy toy = View.createToy();
                    Model.putInMap(toy);
                }
                answer2 = true;

            } else if (answer1 == 8) {
                int answer3 = -11;
                int choisenToy = 0;
                while (answer3 < 1 | answer3 > 99) {
                    Model.clear();
                    choisenToy = View.choiceToy(false);
                    answer3 = View.newDropChance();
                }
                ToyCollections.allToy.get(choisenToy).setDropChance(answer3);

            } else if (answer1 == 9) {
                View.saveAll();

            } else if (answer1 == 0) {
                View.saveAll();

            } else {

                Eror.wrongData();
            }
        }
    }
}
