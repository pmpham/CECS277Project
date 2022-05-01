//COMPLETED!! and docced

import java.util.Random;

public class Wizard extends Enemy implements Magical{

  /**
   * overloaded constructor - creates a enemy with a given name and max hp
   * @param n the name we want to give to the wizard
   * @param mHp the max hp we want to give to the wizard
   */
  public Wizard(String n, int mHp){
    super(n, mHp);
  }

  /**
   * attacks a hero with either a missile or a fireball (chosen at random)
   * @param h the hero we want to attack
   * @return a string with the type of attack and damage done
   */
  @Override
  public String attack(Hero h){
    Random rand = new Random();
    int choice = rand.nextInt(2);
    if (choice == 0){
      return magicMissile(h);
    }else{
      return fireball(h);
    }
  }

  /**
   * attacks an entity passed in with a random amount of damage
   * @param e the entity that we want to attack with an missile
   * @return a string stating who attacked who and for what damage
   */
  @Override
  public String magicMissile(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" zaps "+e.getName()+" with Magic Missle for "+ damage+" damage.");

  }

  /**
   * attacks an entity passed in with a random amount of damage
   * @param e the entity that we want to attack with an fireball
   * @return a string stating who attacked who and for what damage
   */
  @Override
  public String fireball(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" hits "+e.getName()+" with fireball for "+ damage+" damage.");
  }
}
