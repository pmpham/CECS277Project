//COMPLETED!!! and docced

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class EnemyGenerator {

  //hash map of the enemy names and their base hp
  private HashMap<String,Integer> enemies = new HashMap<String,Integer>();

  /**
   * default constructor - reads the enemy.txt file and adds all of the names and values into enemies hashmap
   */
  public EnemyGenerator(){
    File file = new File("Enemies.txt");
    try{
      Scanner in = new Scanner(file);
      while (in.hasNextLine()){
        String line = in.nextLine();
        String [] split = line.split(",");
        Integer health = Integer.parseInt(split[1]);
        enemies.put(split[0], health);
      }
    }catch(FileNotFoundException e){
      System.out.println("fnf");
    }
  }

  /**
   * randomly chooses an enemy from the enemies hashmap and creates an enemy type
   * @param level the level of the player, used to make enemy stronger or weaker
   * @return the enemy created
   */
  public Enemy generateEnemy(int level){
    double healthMultiplier = 1+ (level/2);
    Object[] keys =  enemies.keySet().toArray();
    Random rand = new Random();
    int randKeyIdx = rand.nextInt(keys.length);
    String randKey = keys[randKeyIdx].toString();
    int health = (int)(enemies.get(randKey)*healthMultiplier);
    int choice = rand.nextInt(3);
    if(choice == 0){
      Warrior temp = new Warrior(randKey,health);
      return temp;
    }else if (choice == 1){
      Wizard temp = new Wizard(randKey, health);
      return temp;
    }else{
      Ranger temp = new Ranger(randKey, health);
      return temp;
    }
  }
}
