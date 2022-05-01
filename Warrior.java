//completed and docced

import java.util.Random;


public class Warrior extends Enemy implements Fighter{

  /**
   * overloaded constructor - creates a enemy with a given name and max hp
   * @param n the name we want to give to the warrior
   * @param mHp the max hp we want to give to the warrior
   */
  public Warrior(String n, int mHp){
    super(n, mHp);
  }

  /**
   * attacks a hero with either a sword or an axe (chosen at random)
   * @param h the hero we want to attack
   * @return a string with the type of attack and damage done
   */
  @Override
  public String attack(Hero h){
    Random rand = new Random();
    int choice = rand.nextInt(2);
    if (choice == 0){
      return sword(h);
    }else{
      return axe(h);
    }
  }

  /**
   * attacks an entity passed in with a random amount of damage
   * @param e the entity that we want to attack with an sword
   * @return a string stating who attacked who and for what damage
   */
  @Override
  public String sword(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" slashed "+e.getName()+" for "+ damage+" damage.");

  }

  /**
   * attacks an entity passed in with a random amount of damage
   * @param e the entity that we want to attack with an axe
   * @return a string stating who attacked who and for what damage
   */
  @Override
  public String axe(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" chopped "+e.getName()+" for "+ damage+" damage.");
  }
}
