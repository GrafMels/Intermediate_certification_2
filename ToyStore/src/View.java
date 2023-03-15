import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private static Scanner scanner = new Scanner(System.in);
    public static String folderNameSchool = "ToySchoolBoy";
    public static String folderName = "ToyPreschooler";
    public static String folderNameInGame = "ToyInGame";

    public static ArrayList<Toy> readAll(String folderNameNow) {
        File toyFolder = new File(String.format("src\\toysName\\%s", folderNameNow));
        ArrayList<Toy> toyList = new ArrayList<Toy>();
        for (File file : toyFolder.listFiles()) {
            try {
                FileReader jsonReader = new FileReader(file);
                try (Scanner jsonScaner = new Scanner(jsonReader)) {
                    jsonScaner.nextLine();
                    String[] jsonString = new String[8];
                    for (int i = 0; jsonScaner.hasNextLine(); i++) {
                        if (i == 0) {
                            try {
                                jsonString[i] = Integer.toString(ToyCollections.getId());

                            } catch (Exception e) {
                            }

                        } else if (i == 1) {
                            jsonString[i] = file.getName();
                        } else {
                            String[] splitedJsonString = jsonScaner.nextLine().split(": ");

                            try {
                                jsonString[i] = splitedJsonString[1]
                                        .replace(",", "")
                                        .replace("\"", "")
                                        .replace("   ", "");
                            } catch (Exception e) {
                            }

                        }

                    }

                    int amount = Integer.parseInt(jsonString[3]);
                    int id = Integer.parseInt(jsonString[2]);
                    int dropChance = Integer.parseInt(jsonString[4]);
                    boolean game = false;
                    String name = (jsonString[1] == null || jsonString[1].length() == 0) ? null
                            : jsonString[1].substring(0, jsonString[1].length() - 5);
                    game = false;
                    boolean age = false;
                    if (folderName == folderNameNow) {
                        age = true;
                    }
                    try {
                        if (jsonString[5] != null) {
                            if (jsonString[5].equals("true")) {
                                age = true;
                                game = true;
                            } else {
                                age = false;
                                game = true;
                            }
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    Toy toy = Model.createToy(
                            game,
                            age,
                            id,
                            name,
                            amount,
                            dropChance);
                    toyList.add(toy);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return toyList;
    }

    public static int menuFirst() {
        System.out.println("Выберете дейтсвие и введите соответсвующую цифру.");
        System.out.println("1. Разыграть игрушку");
        System.out.println("2. Добавить игрушку в игру");
        System.out.println("3. Убрать игрушку из игры");
        System.out.println("4. Добавить новое количество игрушек вне игры");
        System.out.println("5. Показать все игрушки вне игры");
        System.out.println("6. Показать все игрушки в игре");
        System.out.println("7. Добавить новый тип игрушки");
        System.out.println("8. Добавить новый шанс выпадения игрушки вне игры");
        System.out.println("9. Сохранить всё");
        System.out.println("0. Выход");
        int answer = scanner.nextInt();
        return answer;
    }

    public static void saveToy(String folder, String name, Toy toy) {
        File file = new File(String.format("src\\toysName\\%s\\%s.json", folder, name));
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(toy.toFile());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int choiceToy(boolean choice) {
        System.out.println("Введите номер игрушки: ");
        if (choice) {
            System.out.print(ToyCollections.ToStringGame());
        } else {
            System.out.print(ToyCollections.ToStringAll());
        }
        int answer = 0;
        if (scanner.hasNextInt()) {
            answer = scanner.nextInt();
        }
        return answer;
    }

    public static Toy createToy() {
        System.out.print("Введите имя игрушки: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        boolean x1 = true;
        System.out.println("Введите возрастную группу: ");
        System.out.println("1. Дошкольник");
        System.out.println("2. Школьник");
        boolean ageGroup = false;
        if (scanner.hasNextInt()) {
            int tempInt = scanner.nextInt();
            if (tempInt == 1) {
                ageGroup = false;
            } else if (tempInt == 2) {
                ageGroup = true;
            } else {
                x1 = false;
            }
        }

        System.out.println("Введите колличество таких игрушек: ");
        int amount = -10;
        if (scanner.hasNextInt()) {
            amount = scanner.nextInt();
        }

        System.out.println("Введите шанс выпадения: ");
        int dropChance = -10;
        if (scanner.hasNextInt()) {
            dropChance = scanner.nextInt();
        }
        if (x1 | dropChance > 0 | dropChance < 100 | amount > 0) {
            Controller.answer2 = false;
        }
        return Model.createToy(false, ageGroup, 0, name, amount, dropChance);
    }

    public static void saveAll() {
        for (int i = 0; i < ToyCollections.allToy.size(); i++) {
            if (ToyCollections.allToy.get(i).isAgeGroup()) {
                saveToy(folderName, String.format("%s", ToyCollections.allToy.get(i).getName()),
                        ToyCollections.allToy.get(i));
            } else {
                saveToy(folderNameSchool, String.format("%s", ToyCollections.allToy.get(i).getName()),
                        ToyCollections.allToy.get(i));
            }
        }
        for (int i = 0; i < ToyCollections.inGame.size(); i++) {
            saveToy(folderNameInGame, String.format("%s", ToyCollections.inGame.get(i).getName()),
                    ToyCollections.inGame.get(i));
        }
    }

    public static int newAmount() {
        System.out.println("Введите новое колличество для данной игрушки: ");
        int answer = 0;
        if (scanner.hasNextInt()) {
            answer = scanner.nextInt();
        }
        return answer;
    }

    public static int newDropChance() {
        System.out.println("Введите новый шанс выпадения игрушки(от 0 до 100): ");
        int answer = 0;
        if (scanner.hasNextInt()) {
            answer = scanner.nextInt();
        }
        return answer;
    }

    public static void deleteToy(String folder, String name) {
        File file = new File(String.format("src\\toysName\\%s\\%s.json", folder, name));
        file.delete();
    }
}