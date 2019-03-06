package logic;

import java.math.BigInteger;
import java.util.Scanner;

public class Logic {
    Scanner wejscie = new Scanner(System.in);
    int[] tablica;
    public void wejscie() {
        System.out.println("podaj rozmiar tablicy:");
        int ile = wejscie.nextInt();
        tablica = new int[ile];

        System.out.println("podaj kolejne elementy tablicy:");
        for (int i =0 ; i<ile; i++){

        int input = wejscie.nextInt();
        tablica[i] = input;
        System.out.println(tablica[i]);
        }
    }

}
