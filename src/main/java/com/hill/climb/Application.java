package com.hill.climb;

@SuppressWarnings("WeakerAccess")
public class Application {
  public static void main(String[] args) {
    System.out.println("Application initialized...");
    HillClimb test = new HillClimb();
    test.runHillClimbing();
    System.out.println("Exit application...");
  }

  public static boolean functionToTest(int x, int y) {
    if (x == y * y * (x % 20)) {
      return true;
    } else {
      return false;
    }
  }
}
