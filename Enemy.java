//COMPLETED!! and docced

public abstract class Enemy extends Entity{

  /**
   * overloaded constructor - creates an enemy with a given name and max hp
   * @param n the name we want the enemy to have
   * @param mHp the max hp we want the enemy to have
   */
  public Enemy(String n, int mHp){
    super(n,mHp);
  }

  /**
   * abstract class that allows the enemy to attack a hero passed in
   * @param h the hero we want the enemy to attack
   * @return a string stating who attacked who and for what damage
   */
  public abstract String attack(Hero h);
}
