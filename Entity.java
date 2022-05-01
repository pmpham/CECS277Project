//COMPLETED!!!! and docced

public abstract class Entity {
  private String name;
  private int hp;
  private int maxHp;

  /**
   * Default constructor, gives the entity a name and sets the hp to max.
   * @param n name of the entity
   * @param mHp max hp we want to assign it
   */
  public Entity(String n, int mHp){
    name = n;
    hp = mHp;
    maxHp = mHp;
  }

  /**
   * gets name of entity
   * @return name of entity
   */
  public String getName(){
    return name;
  }

  /**
   * gets the hp of entity
   * @return entity's hp
   */
  public int getHp(){
    return hp;
  }

  /**
   * heals the entity to max health
   */
  public void heal(){
    hp = maxHp;
  }

  /**
   * deals damage to the character
   * @param d the damage we want to deal to the entity
   */
  public void takeDamage(int d){
    if (d>=hp){
      hp = 0;
    }else{
      hp-=d;
    }
  }

  /**
   * represents the entity as a string
   * @return a string consisting of entity's name and hp over maxhp
   */
  @Override
  public String toString(){
    return (name + "\n" + hp +"/"+maxHp);
  }
}
