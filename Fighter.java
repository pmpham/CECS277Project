//COMPLETED!!! and docced

public interface Fighter {
  //menu for the types of attacks a fighter could have
  public static final String FIGHTER_MENU = "1. Sword\n2. Axe";

  //the amount of items a fighter has
  public static final int NUM_FIGHTER_MENU_ITEMS = 2;

  /**
   * represents a sword attack
   * @param e the entity that we want to attack with an sword
   * @return a string stating who attacked who and for what damage
   */
  public String sword(Entity e);

  /**
   * represents an axe attack
   * @param e the entity that we want to attack with an axe
   * @return a string stating who attacked who and for what damage
   */
  public String axe(Entity e);
}
