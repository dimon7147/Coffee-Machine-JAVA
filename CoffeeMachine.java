package machine;

import java.util.Scanner;

enum Drinks{
    ESPRESSO(250,Integer.MAX_VALUE,16,4),
    LATTE(350,75,20,7),
    CAPPUCCINO(200,100,12,6);

    int water;
    int milk;
    int coffeeBeans;
    int cost;


    Drinks(int water, int milk, int coffeeBeans, int cost){
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cost = cost;
    }
}


public class CoffeeMachine {
    int water = 400;
    int milk = 540;
    int coffeeBeans = 120;
    int cups = 9;
    int cash = 550;

    public static void main(String[] args) {
        var machine = new CoffeeMachine();
        machine.start(machine);
    }

    private void printRemaining(){
        System.out.println("The coffee machine has:\n" +
                water+" of water\n" +
                milk+" of milk\n" +
                coffeeBeans+" of coffee beans\n" +
                cups+" of disposable cups\n" +
                "$"+cash+" of money\n");
    }

    private boolean isEnoughResources(Drinks drink){
        int[] components = {this.water/drink.water, this.coffeeBeans/drink.coffeeBeans, cups-1, this.milk/drink.milk};
        var min = Integer.MAX_VALUE;
        var id = 0;
        for (int i = 0; i < components.length-1; i++){
            if (components[i] < min){
                min = components[i];
                id = i;
            }
        }
        if (min >= 1){
            return true;
        }
        switch (id){
            case 0: {
                System.out.println("Sorry, not enough water!");
                return false;
            }
            case 1: {
                System.out.println("Sorry, not enough coffee beans!");
                return false;
            }
            case 2: {
                System.out.println("Sorry, not enough disposable cups!");
                return false;
            }
            case 3: {
                System.out.println("Sorry, not enough milk!");
                return false;
            }
        }
        System.out.println("Wtf?");
        return false;
    }

    private void makeDrink(int selected){
        Drinks drink;
        switch (selected){
            case 1: {
                drink = Drinks.ESPRESSO;
                if (!isEnoughResources(drink)) return;
                System.out.println("I have enough resources, making you a coffee!");
                water -= 250;
                coffeeBeans -= 16;
                cash += 4;
                cups--;
                break;
            }
            case 2: {
                drink = Drinks.LATTE;
                if (!isEnoughResources(drink)) return;
                System.out.println("I have enough resources, making you a coffee!");
                water -= 350;
                milk -= 75;
                coffeeBeans -= 20;
                cash += 7;
                cups--;
                break;
            }
            case 3: {
                drink = Drinks.CAPPUCCINO;
                if (!isEnoughResources(drink)) return;
                System.out.println("I have enough resources, making you a coffee!");
                water -= 200;
                milk -= 100;
                coffeeBeans -= 12;
                cash += 6;
                cups--;
                break;
            }
        }
    }

    private void fill(){
        var scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add: ");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        cups += scanner.nextInt();
    }

    private void take(){
        System.out.println("I gave you $"+cash);
        cash = 0;
    }

    public void start(CoffeeMachine machine){
        var scanner = new Scanner(System.in);
        while (true){
            System.out.println("Write action (buy, fill, take): ");
            var action = scanner.next();
            switch (action){
                case "buy": {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    var item = scanner.next();
                    if (item.equals("back")) break;
                    machine.makeDrink(Integer.parseInt(item));
                    break;
                }
                case "fill": {
                    machine.fill();
                    break;
                }
                case "take": {
                    machine.take();
                    break;
                }
                case "remaining": {
                    machine.printRemaining();
                    break;
                }
                case "exit": {
                    return;
                }
            }
        }
    }
}
