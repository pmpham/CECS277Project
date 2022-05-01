// completed and docced

import java.awt.*;
import java.util.*;

public class Hero extends Entity implements Magical, Fighter, Archer{
  private Point loc;
  private int level, gold, keys, potions;

  /**
   * Overloaded Constructor - creates an entity with name and sets maxHp to 25
   * @param n the name for character
   */
  public Hero(String n){
    super(n,25);
    Map.getInstance().loadMap(1);
    loc = Map.getInstance().findStart();
    level = 1;
  }

  /**
   * increments the hero's level
   */
  public void levelUp(){
    level++;
  }

  /**
   * gets the Hero's level
   * @return hero's level
   */
  public int getLevel(){
    return level;
  }

  /**
   * @return a string with the hero's hp, level, gold, potions, and keys
   */
  @Override
  public String toString(){
    return(getName()+"\nHP: "+getHp()+"/25\n"+"Level: "+getLevel()+"\nGold: "+getGold()+"\nP: "+potions+" K: "+keys+"\n"+Map.getInstance().mapToString(loc));
  }

  /**
   * checks if it is possible to move north and moves the hero
   * @return the type of room the player moves to
   */
  public char goNorth(){
    if (loc.getY()==0){
      return('x');
    }
    else{
      loc.setLocation(loc.x, loc.y-1);
      Map.getInstance().reveal(loc);
      return(Map.getInstance().getCharAtLoc(loc));
    }
  }

  /**
   * checks if it is possible to move south and moves the hero
   * @return the type of room the player moves to
   */
  public char goSouth(){
    if (loc.getY()== 4){
      return('x');
    }
    else{
      loc.setLocation(loc.x, loc.y+1);
      Map.getInstance().reveal(loc);
      return(Map.getInstance().getCharAtLoc(loc));
    }
  }

  /**
   * checks if it is possible to move east and moves the hero
   * @return the type of room the player moves to
   */
  public char goEast(){
    if (loc.getX()== 4){
      return('x');
    }
    else{
      loc.setLocation(loc.x+1, loc.y);
      Map.getInstance().reveal(loc);
      return(Map.getInstance().getCharAtLoc(loc));
    }
  }

  /**
   * checks if it is possible to move west and moves the hero
   * @return the type of room the player moves to
   */
  public char goWest(){
    if (loc.getX()== 0){
      return('x');
    }
    else{
      loc.setLocation(loc.x-1, loc.y);
      Map.getInstance().reveal(loc);
      return(Map.getInstance().getCharAtLoc(loc));
    }
  }

  /**
   * displays the attack menu
   * @return different classes of attacks
   */
  public String getAttackMenu(){
    return ("1. Physical \n2. Magical \n3. Ranged");
  }

  /**
   * gets the choice for type of attack
   * @return an int representing the attack choice
   */
  public int getNumAttackMenuItems(){
    Scanner in = new Scanner( System.in );
		int input = 0;
		boolean valid = false;
		while( !valid ) {
			if( in.hasNextInt() ) {
				input = in.nextInt();
				if( input <= 3 && input >= 1 ) {
					valid = true;
				} else {
					System.out.println( "Invalid Range." );
				}
			} else {
				in.next(); //clear invalid string
				System.out.println( "Invalid Input." );
			}
		}
    //in.close();
		return input;
  }

  /**
   * displays the sub attack menu
   * @param choice an int representing the attack type
   * @return the sub attack menu with which weapons you can choose
   */
  public String getSubAttackMenu(int choice){
    if (choice == 1){
      return FIGHTER_MENU;
    }else if (choice ==2){
      return MAGIC_MENU;
    }else{
      return ARCHER_MENU;
    }
  }

  /**
   * gets choice of attack weapon
   * @param Choice the attack class type
   * @return an int representing what weapon the character wants to use
   */
  public int getNumSubAttackMenuItems(int Choice){
    Scanner in = new Scanner( System.in );
		int input = 0;
		boolean valid = false;
		while( !valid ) {
			if( in.hasNextInt() ) {
				input = in.nextInt();
				if( input <= 2 && input >= 1 ) {
					valid = true;
				} else {
					System.out.println( "Invalid Range." );
				}
			} else {
				in.next(); //clear invalid string
				System.out.println( "Invalid Input." );
			}
		}
    //in.close();
		return input;
  }

  /**
   * deals damage to the enemy passed in and returns a string of the weapon used and how much damage done
   * @param e the enemy we want to attack
   * @param choice the attack class we want to use
   * @param subChoice the weapon we want to use in that class
   * @return
   */
  public String attack(Enemy e, int choice, int subChoice){
    if (choice == 1){
      if (subChoice == 1){
        String fight =  sword(e);
        if (e.getHp()==0){
          Map.getInstance().removeCharAtLoc(loc);
        }
        return fight;
      }else{
        String fight = axe(e);
        if (e.getHp()==0){
          Map.getInstance().removeCharAtLoc(loc);
        }
        return fight;
      }
    }else if (choice == 2){
      if (subChoice ==1){
        String fight = magicMissile(e);
        if (e.getHp()==0){
          Map.getInstance().removeCharAtLoc(loc);
        }
        return fight;
      }else{
        String fight = fireball(e);
        if (e.getHp()==0){
          Map.getInstance().removeCharAtLoc(loc);
        }
        return fight;
      }
    }else if(choice == 3){
      if (subChoice==1){
        String fight = arrow(e);
        if (e.getHp()==0){
          Map.getInstance().removeCharAtLoc(loc);
        }
        return fight;
      }else{
        String fight = fireArrow(e);
        if (e.getHp()==0){
          Map.getInstance().removeCharAtLoc(loc);
        }
        return fight;
      }
    }else{
      return "wrong choice";
    }
  }

  /**
   * gets how much gold the hero has
   * @return the hero's gold
   */
  public int getGold(){
    return gold;
  }

  /**
   * adds gold to the hero's gold
   * @param g the gold we want to give the hero
   */
  public void collectGold(int g){
    gold+=g;
  }

  /**
   * deducts the hero's gold if they have enough
   * @param g the amount gold we want to deduct
   * @return false if the player does not have enough gold, true if he does
   */
  public boolean spendGold(int g){
    if (g>gold){
      return false;
    }
    gold-=g;
    return true;
  }

  /**
   * checks if the hero has a key
   * @return true if they have a key, false if they dont have a key
   */
  public boolean hasKey(){
    if (keys>0){
      return true;
    }
    return false;
  }

  /**
   * adds a key to the hero's key count. also if the spot they are on is 'i', 
   * it gets set to nothing
   */
  public void pickUpKey(){
    if(Map.getInstance().getCharAtLoc(loc) == 'i'){
      Map.getInstance().removeCharAtLoc(loc);
    }
    keys++;
  }

  /**
   * checks if the hero has a key and if they do it uses it
   * @return true if the hero can use a key and false if they cannot use a key
   */
  public boolean useKey(){
    if (hasKey()){
      keys--;
      return true;
    }
    return false;
  }

  /**
   * checks if the hero has a potion
   * @return true if they have a potion, false if they do not
   */
  public boolean hasPotion(){
    if (potions>0){
      return true;
    }
    return false;
  }

  /**
   * adds a potion to the hero's potion count. also if the spot they are on is 'i', 
   * it gets set to nothing
   */
  public void pickUpPotion(){
    if(Map.getInstance().getCharAtLoc(loc) == 'i'){
      Map.getInstance().removeCharAtLoc(loc);
    }
    potions++;
  }

  /**
   * checks if the hero has a potion and if they do, it uses it
   * @return true if the hero can use a potion and false if they cannot
   */
  public boolean usePotion(){
    if (hasPotion()){
      potions--;
      return true;
    }
    return false;
  }

  /**
   * attacks an enemy with a sword
   * @param e the enemy we want to attack
   * @return the attack details with who we attacked and for what damage.
   * 
   */
  @Override
  public String sword(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" slashed "+e.getName()+" for "+ damage+" damage.");

  }

  /**
   * attacks an enemy with an axe
   * @param e the enemy we want to attack
   * @return the attack details with who we attacked and for what damage.
   * 
   */
  @Override
  public String axe(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" chopped "+e.getName()+" for "+ damage+" damage.");
  }

  /**
   * attacks an enemy with a missile
   * @param e the enemy we want to attack
   * @return the attack details with who we attacked and for what damage.
   * 
   */
  @Override
  public String magicMissile(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" zaps "+e.getName()+" with Magic Missile for "+ damage+" damage.");

  }

  /**
   * attacks an enemy with a fireball
   * @param e the enemy we want to attack
   * @return the attack details with who we attacked and for what damage.
   * 
   */
  @Override
  public String fireball(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" hits "+e.getName()+" with fireball for "+ damage+" damage.");
  }

  /**
   * attacks an enemy with an arrow
   * @param e the enemy we want to attack
   * @return the attack details with who we attacked and for what damage.
   * 
   */
  @Override
  public String arrow(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" shoots "+e.getName()+" with an arrow for "+ damage+" damage.");

  }

  /**
   * attacks an enemy with a fire arrow
   * @param e the enemy we want to attack
   * @return the attack details with who we attacked and for what damage.
   * 
   */
  @Override
  public String fireArrow(Entity e){
    Random rand = new Random();
    int damage = rand.nextInt(5)+1;
    e.takeDamage(damage);
    return (this.getName()+" shoots "+e.getName()+" with a flaming arrow for "+ damage+" damage.");
  }
}