//COMPLETED AND DOCCED

import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    EnemyGenerator enemies = new EnemyGenerator();
    System.out.print("What is your name, traveler? ");
    String name = in.nextLine();
    Hero h = new Hero(name);
    while (true) {
      System.out.print(h.toString());
      int move = mainMenu(h);
      char type;
      if (move == 1) {
        type = h.goNorth();
      } else if (move == 2) {
        type = h.goSouth();
      } else if (move == 3) {
        type = h.goEast();
      } else if (move == 4) {
        type = h.goWest();
      } else {
        break;
      }
      if (type == 'x') {
        System.out.println("Out of Bounds.");
      } else if (type == 'n') {
        System.out.println("There was nothing here.");
      } else if (type == 's') {
        store(h);
      } else if (type == 'f') {
        if (h.hasKey()) {
          h.levelUp();
          Map.getInstance().loadMap(h.getLevel() % 3);
          h.useKey();
          System.out.println("You find a locked gate. Luckily you have a key! You proceed to the next area.");
        } else {
          System.out.println("You find a locked gate.But you dont have a key to unlock this door.");
        }
      } else if (type == 'i') {
        Random rand = new Random();
        int item = rand.nextInt(2);
        if (item == 0) {
          h.pickUpKey();
          System.out.println("You found a key!");
        } else {
          h.pickUpPotion();
          System.out.println("You found a potion!");
        }
      } else if (type == 'm') {
        Enemy temp = enemies.generateEnemy(h.getLevel());
        boolean alive= true;
        System.out.print("You encountered a ");
        while(temp.getHp()>0){
        alive = monsterRoom(h, temp);}
        if (alive == false) {
          System.out.println("Game Over.");
        }
      } else {
      }
    }
  }

  /**
   * prompts the user to move in any direction (NESW) and returns an int representing the direction
   * @param h the hero we want to move
   * @return int representing the direction of movement
   */
  public static int mainMenu(Hero h) {
    System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
    Scanner in = new Scanner(System.in);
    int move = 0;
    boolean valid = false;
    while (!valid) {
      if (in.hasNextInt()) {
        move = in.nextInt();
        if (move <= 5 && move >= 1) {
          valid = true;
        } else {
          System.out.println("Invalid Range.");
        }
      } else {
        in.next(); // clear invalid string
        System.out.println("Invalid Input.");
      }
    }
    return move;
  }

  /**
   * keeps looping if the enemy is alive or until the player runs away
   * @param h the hero that will be fighting in the monster roomm
   * @param e the enemy that the hero is going up against
   * @return true if the hero is alive after the encounter and false if they arent
   */
  public static boolean monsterRoom(Hero h, Enemy e) {
    while (e.getHp() > 0) {
      System.out.println(e.getName());
      System.out.println("HP: " + e.getHp());
      if (h.hasPotion()) {
        System.out.println("1. Fight\n2. Run Away\n3. Drink Potion");
        int action = h.getNumAttackMenuItems();
        if (action == 1) {
          return fight(h, e);
        } else if (action == 2) {
          Random rand = new Random();
          int move = rand.nextInt(4) + 1;
          if (move == 1) {
            h.goNorth();
            e.takeDamage(e.getHp());
            break;
          } else if (move == 2) {
            h.goSouth();
            e.takeDamage(e.getHp());
            break;
          } else if (move == 3) {
            h.goEast();
            e.takeDamage(e.getHp());
            break;
          } else if (move == 4) {
            h.goWest();
            e.takeDamage(e.getHp());
            break;
          }
          break;
        } else if (action == 3) {
          h.usePotion();
          h.heal();
        }
      } else if (!h.hasPotion()) {
        System.out.println("1. Fight\n2. Run Away");
        Scanner in = new Scanner(System.in);
        int action = 0;
        boolean valid = false;
        while (!valid) {
          if (in.hasNextInt()) {
            action = in.nextInt();
            if (action <= 2 && action >= 1) {
              valid = true;
            } else {
              System.out.println("Invalid Range.");
            }
          } else {
            in.next();
            System.out.println("Invalid Input.");
          }
        }
        if (action == 1) {
          return fight(h, e);

        } else if (action == 2) {
          Random rand = new Random();
          int move = rand.nextInt(4) + 1;
          if (move == 1) {
            h.goNorth();
            e.takeDamage(e.getHp());
            break;
          } else if (move == 2) {
            h.goSouth();
            e.takeDamage(e.getHp());
            break;
          } else if (move == 3) {
            h.goEast();
            e.takeDamage(e.getHp());
            break;
          } else if (move == 4) {
            h.goWest();
            e.takeDamage(e.getHp());
            break;
          }
        }
      }
    }
    if (h.getHp() > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * plays out a fight between a hero and enemy. the hero attacks first and if the enemy is still alive after, they attack back. if the enemy is killed, they drop gold
   * @param h the hero that is fighting
   * @param e the enemy that the hero is fighting
   * @return true if the hero is still alive and false if they died
   */
  public static boolean fight(Hero h, Enemy e) {
    System.out.println(h.getAttackMenu());
    int attackType = h.getNumAttackMenuItems();
    System.out.println(h.getSubAttackMenu(attackType));
    int weapon = h.getNumSubAttackMenuItems(attackType);
    System.out.println(h.attack(e, attackType, weapon));
    if (e.getHp() == 0) {
      System.out.println("You defeated the " + e.getName());
      Random rand = new Random();
      int gold = rand.nextInt(16) + 10;
      System.out.println("you find " + gold + " gold on the corpse.");
      h.collectGold(gold);

    } else {
      System.out.println(e.attack(h));
    }
    if (h.getHp() != 0) {
      return true;
    }
    return false;
  }

  /**
   * allows the user to buy either potions or keys if they have enough money. if they do not have enough money it will say they cannot afford it
   * if they can afford it the gold is subtracted and the item is added to their inv
   * @param h the hero that is going to be buying stuff
   */
  public static void store(Hero h) {
    System.out.println("Welcome to the store. What would you like to buy?");
    System.out.println("1. Health Potion - 25g\n2. Key - 50g\n3. Nothing, just browsing...");
    
    //gets input of what the user wants to buy
    Scanner in = new Scanner(System.in);
    int choice = 0;
    boolean valid = false;
    while (!valid) {
      if (in.hasNextInt()) {
        choice = in.nextInt();
        if (choice <= 3 && choice >= 1) {
          valid = true;
        } else {
          System.out.println("Invalid Range.");
        }
      } else {
        in.next(); // clear invalid string
        System.out.println("Invalid Input.");
      }
    }
    if (choice == 1){
      if(h.getGold()>=25){
        System.out.println("You purchased a potion!");
        h.spendGold(25);
        h.pickUpPotion();
      }else{System.out.println("You cannot afford a potion.");}
    }else if (choice ==2){
      if(h.getGold()>=50){
        System.out.println("You purchased a potion!");
        h.spendGold(50);
        h.pickUpKey();
      }else{System.out.println("You cannot afford a key.");}
    }else{}
  }
}
