package agh.po;

import agh.po.MapElements.Animal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Genotype {
    private final int size = 32;
    private final int[] genotype = new int[size];

    public Genotype() {
        int i;
        //faster than fixing
        for (i = 0; i < 8; i++) {
            this.genotype[i] = i; //each number occurs at least once
        }
        for (i = 8; i < size; i++) {
            this.genotype[i] = (new Random()).nextInt(8);
        }
        Arrays.sort(genotype);
    }

    public Genotype(Animal parent1, Animal parent2) {
        int[] partition = new int[2];
        Random rand = new Random();

        partition[0] = rand.nextInt(31);
        partition[1] = rand.nextInt(31 - partition[0]) + partition[0];

        for (int i = 0; i < partition[0]; i++) {
            this.genotype[i] = parent1.getGenotype().getSpecificGen(i);
        }
        for (int i = partition[0]; i < partition[1]; i++) {
            this.genotype[i] = parent2.getGenotype().getSpecificGen(i);
        }
        for (int i = partition[1]; i < size; i++) {
            this.genotype[i] = parent1.getGenotype().getSpecificGen(i);
        }

        //fixing
        int[] usedGenes = new int[8];
        for (int gene : genotype) {
            usedGenes[gene]++;
        }

        for (int i = 0; i < 8; i++) {
            if (usedGenes[i] == 0) {
                for (int j = 0; j < size; j++) {
                    if (usedGenes[j] > 1) {
                        genotype[j] = i;
                        usedGenes[i]++;
                        usedGenes[genotype[j]]--;
                    }
                }
            }
        }
        Arrays.sort(genotype);
    }

    private int getSpecificGen(int i) {
        return this.genotype[i];
    }

    public int getRandomGen() {
        return this.genotype[(new Random()).nextInt(size)];
    }
}
