package com.hill.climb;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


@SuppressWarnings("WeakerAccess")
public class HillClimb {
  private int distance = Integer.MAX_VALUE;
  private int neighborhoodMaxLimit = 1000;
  private int neighborhoodMinLimit = -1000;

  public HillClimb() {
    System.out.println("Init hill climbing...");
  }

  public void runHillClimbing() {
    Random random = new Random();
    int x = this.neighborhoodMinLimit + random.nextInt(this.neighborhoodMaxLimit - this.neighborhoodMinLimit + 1);
    int y = this.neighborhoodMinLimit + random.nextInt(this.neighborhoodMaxLimit - this.neighborhoodMinLimit + 1);
    int fitness = this.getFitness(x, y);
    int iterations = 0;
    while (fitness > 0 ) {
      iterations++;
      boolean changed = false;
      for(Pair<Integer, Integer> p: this.getNeigborhood(x, y)) {
        int newFitness = this.getFitness(p.getKey(), p.getValue());
        if (newFitness < fitness) {
          x = p.getKey();
          y = p.getValue();
          fitness = newFitness;
          changed = true;
          System.out.println("New values: [" + x + ", " + y + "] with fitness: " + fitness + " ...");
          break;
        }
      }
      if (!changed) {
        x = this.neighborhoodMinLimit + random.nextInt(this.neighborhoodMaxLimit - this.neighborhoodMinLimit + 1);
        y = this.neighborhoodMinLimit + random.nextInt(this.neighborhoodMaxLimit - this.neighborhoodMinLimit + 1);
        fitness = this.getFitness(x, y);
      }
    }
    System.out.println("\tOptimum: [" + x + ", " + y + "]; \n\tNo of iterations: " + iterations + "\n\tEnding Hill Climbing...");
  }

  private boolean instrumentedTestFunction(int x, int y) {
    this.distance = Math.abs(x - y * y * (x % 20));
    return Application.functionToTest(x, y);
  }

  private int getFitness(int x, int y) {
    this.instrumentedTestFunction(x, y);
    int fitness = distance;
    return fitness;
  }

  private ArrayList<Pair<Integer, Integer>> getNeigborhood(int x, int y) {
    ArrayList<Integer> helperList = new ArrayList<Integer>(Arrays.asList(-1, 0, 1));
    ArrayList<Pair<Integer, Integer>> neighborList = new ArrayList<Pair<Integer, Integer>>();
    for (int i: helperList) {
      for (int j: helperList) {
        if (
          (i != 0 || j != 0) &&
          (this.neighborhoodMinLimit <= x + i && x + i <= this.neighborhoodMaxLimit) &&
          (this.neighborhoodMinLimit <= y + j && y + j <= this.neighborhoodMaxLimit)
        ) {
          Pair<Integer, Integer> tempValue = new Pair<Integer, Integer>(x + i, y + j);
          neighborList.add(tempValue);
        }
      }
    }
    return neighborList;
  }
}
