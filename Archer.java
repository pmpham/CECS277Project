//COMPLETED!!! and docced

public interface Archer {
  //menu for the types of attacks an archer could have
  public static final String ARCHER_MENU = "1. Arrow\n2. Fire Arrow";

  //the amount of items an archer has
  public static final int NUM_ARCHER_MENU_ITEMS = 2;

  /**
   * represents an arrow attack
   * @param e the entity that we want to attack with an arrow
   * @return a string stating who attacked who and for what damage
   */
  public String arrow(Entity e);

  /**
   * represents an fire arrow attack
   * @param e the entity that we want to attack with an fire arrow
   * @return a string stating who attacked who and for what damage
   */
  public String fireArrow(Entity e);
}
