package entities;

import java.util.Scanner;

public class Menu {
    private Ecosystem ecosystem;
    private Scanner scanner;
    private int numCycles;

    public Menu() {
        ecosystem = new Ecosystem();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить начальные ресурсы");
            System.out.println("2. Добавить растение");
            System.out.println("3. Добавить животное");
            System.out.println("4. Установить количество циклов симуляции");
            System.out.println("5. Показать текущие условия симуляции");
            System.out.println("6. Запустить симуляцию");
            System.out.println("7. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка ввода
            switch (choice) {
                case 1 -> addResources();
                case 2 -> addPlant();
                case 3 -> addAnimal();
                case 4 -> setSimulationCycles();
                case 5 -> showCurrentConditions();
                case 6 -> startSimulation();
                case 7 -> running = false;
                default -> System.out.println("Некорректный выбор, попробуйте снова.");
            }
        }
    }

    private void addResources() {
        System.out.print("Введите начальное количество воды: ");
        int water = scanner.nextInt();
        System.out.print("Введите начальное количество солнечного света: ");
        int sunlight = scanner.nextInt();
        System.out.print("Введите количество воды, на которое она будет восстанавливаться в конце цикла: ");
        int waterRecovery = scanner.nextInt();
        System.out.print("Введите количество света, на которое он будет восстанавливаться в конце цикла: ");
        int sunLightRecovery = scanner.nextInt();
        ecosystem.addResources(water, sunlight, waterRecovery, sunLightRecovery);
        System.out.println("Ресурсы успешно добавлены!");
    }

    private void addPlant() {
        System.out.print("Введите название растения: ");
        String name = scanner.next();
        System.out.print("Введите количество воды, потребляемое растением: ");
        int waterConsumption = scanner.nextInt();
        System.out.print("Введите количество солнечного света, потребляемое растением: ");
        int sunlightConsumption = scanner.nextInt();
        ecosystem.addPlant(name, waterConsumption, sunlightConsumption);
        System.out.println("Растение добавлено в экосистему!");
    }

    private void addAnimal() {
        System.out.print("Введите название животного: ");
        String name = scanner.next();
        System.out.print("Выберите диету животного (1 - травоядное, 2 - хищник): ");
        int dietChoice = scanner.nextInt();
        boolean isHerbivore = dietChoice == 1;
        ecosystem.addAnimal(name, isHerbivore);
        System.out.println("Животное добавлено в экосистему!");
    }

    private void setSimulationCycles() {
        System.out.print("Введите количество циклов симуляции: ");
        numCycles = scanner.nextInt();
        if (numCycles > 0) {
            System.out.println("Количество циклов установлено!");
        } else {
            System.out.println("Некорректное количество циклов. Повторите попытку.");
            setSimulationCycles();
        }
    }

    private void showCurrentConditions() {
        System.out.println("Текущие условия экосистемы:");
        ecosystem.showConditions();
        System.out.println("Количество циклов симуляции: " + numCycles);
    }

    private void startSimulation() {
        if (ecosystem.isReadyForSimulation()) {
            ecosystem.run(numCycles);
            System.out.println("Симуляция завершена.");
        } else {
            System.out.println("Необходимо задать ресурсы и организмы для начала симуляции.");
        }
    }
}
