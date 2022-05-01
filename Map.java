//COMPLETED ADN DOCCED

import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Map {
  private char[][] map;
  private boolean[][] revealed;
  private static Map instance = null;

  /**
   * default constructor - fills in the map array with 'x' and the
   * revealed array with false
   */
  private Map() {
    map = new char[5][5];
    revealed = new boolean[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        map[i][j] = 'x';
        revealed[i][j] = false;
      }
    }
  }

  /**
   * checks if there is a map already made and if not it makes the map
   * @return the map
   */
  public static Map getInstance() {
    if (instance == null) {
      instance = new Map();
    }
    return instance;
  }

  /**
   * checks the input and loads a text file with the map depending on the input
   * also sets all of revealed array to false
   * @param mapNum the map that we want to load
   */
  public void loadMap(int mapNum) {
    File file;
    if (mapNum == 1) {
      file = new File("Map1.txt");
    } else if (mapNum == 2) {
      file = new File("Map2.txt");
    } else {
      file = new File("Map3.txt");
    }
    try {
      Scanner in = new Scanner(file);
      for (int r = 0; r < 5; r++) {
        try {
          String line = in.nextLine();
          String[] chars = line.split(" ");
          for(int c = 0;c<5;c++){
            map[r][c] = chars[c].charAt(0);
            revealed[r][c] = false;
          }
          //System.out.println(Arrays.toString(map[r]));
        } catch (Exception e) {
          System.out.println("no next line");
        }
      }
      //in.close();
    } catch (FileNotFoundException e) {
      System.out.println("FNF");
    }

  }

  /**
   * returns the character at a given location
   * @param p the location we want to check
   * @return the character at the location we check
   */
  public char getCharAtLoc(Point p) {
    return map[(int) p.getY()][(int) p.getX()];
  }

  /**
   * parses through the map and finds the location of the start
   * @return a point with the coordinates of the start
   */
  public Point findStart() {
    Point start = new Point(0, 0);
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == 's') {
          start.setLocation(j, i);
          return (start);
        }
      }
    }
    return start;
  }

  /**
   * sets a given point on the revealed array to true
   * @param p the point we want to set to true
   */
  public void reveal(Point p) {
    revealed[(int) p.getY()][(int) p.getX()] = true;
  }

  /**
   * sets the character at a given location to 'n'
   * @param p the location we want to set to 'n'
   */
  public void removeCharAtLoc(Point p) {
    map[(int) p.getY()][(int) p.getX()] = 'n';
  }

  /**
   * returns a string of the map given which positions are revealed
   * also puts a '*' for the coords passed in
   * @param p the location we want to make '*'
   * @return a string with the map
   */
  public String mapToString(Point p) {
    String r = "";
    reveal(p);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (p.getX() == j && p.getY() == i) {
          r += '*';
        } else if (revealed[i][j] == true) {
          r += map[i][j];
        }else{r += 'x';}
      }
      r += "\n";
    }
    return r;
    // return "" + (map[(int) p.getY()][(int) p.getX()]);
  }
}
