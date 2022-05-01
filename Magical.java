//COMPLETED!!! and docced

public interface Magical {
  //menu for the types of attacks a magical could have
  public static final String MAGIC_MENU = "1. Magic Missile\n2. Fireball";

  //the amount of items a magical has
  public static final int NUM_MAGIC_MENU_ITEMS = 2;

  /**
   * represents a missile attack
   * @param e the entity that we want to attack with an missile
   * @return a string stating who attacked who and for what damage
   */
  public String magicMissile(Entity e);

  /**
   * represents a fireball attack
   * @param e the entity that we want to attack with an fireball
   * @return a string stating who attacked who and for what damage
   */
  public String fireball(Entity e);
}
