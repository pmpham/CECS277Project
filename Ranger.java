//COMPLETED!!! and docced

import java.util.Random;

public class Ranger extends Enemy implements Archer{

  /**
   * overloaded constructor - creates a enemy with a given name and max hp
   * @param n the name we want to give to the ranger
   * @param mHp the max hp we want to give to the ranger
   */
  public Ranger(String n, int mHp){
    super(n, mHp);
  }
  
  /**
   * attacks a hero with either a normal arrow or a fire arrow (chosen at random)
   * @param h the hero we want to attack
   * @return a string with the type of attack and damage done
   */
  @Override
  public String attack(Hero h){
    Random rand = new Random();
    int choice = rand.nextInt(2);
    if (choice == 0){
      return arrow(h);
    }else{
      return fireArrow(h);
    }
  }

  /**
   * attacks an entity passed in with a random amount of damage
   * @param e the entity that we want to attack with an arrow
   * @return a string stating who attacked who and for what damage
   */
  @Override
  public String arrow(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" shoots "+e.getName()+" with an arrow for "+ damage+" damage.");

  }

  /**
   * attacks an entity passed in with a random amount of damage
   * @param e the entity that we want to attack with an fire arrow
   * @return a string stating who attacked who and for what damage
   */
  @Override
  public String fireArrow(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" shoots "+e.getName()+" with a flaming arrow for "+ damage+" damage.");
  }
}
