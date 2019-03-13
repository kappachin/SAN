package logic;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Logic {
    Scanner wejscie = new Scanner(System.in);
    int[] tablica;

    public static void perm(int n,int[] list)
    {
        //algorytm Heap'a
        if(n == 1)
        {
            System.out.println(Arrays.toString(list)); //jesli dlugosc listy wynosi 1 zwraca 1 pozycje
        }
        else
        {
            for(int i=0; i<n; i++)
            {
                perm(n-1,list);

                int j = ( n % 2 == 0 ) ? i : 0;

                int t = list[n-1];
                list[n-1] = list[j];
                list[j] = t;
            }
        }
    }

    public int[] getTablica() {
        return tablica;
    }

    public int wejscie() {
        System.out.println("podaj rozmiar tablicy:");
        int ile = wejscie.nextInt();
        tablica = new int[ile];

        System.out.println("podaj kolejne elementy tablicy:");
        for (int i =0 ; i<ile; i++){

        int input = wejscie.nextInt();
        tablica[i] = input;

        //txt
        }
        System.out.println("tablica sklada sie z :");
        printArray();
        System.out.println();
        return ile;
        }

    public void printArray() {
        for (int i = 0; i < tablica.length; i++) {
            System.out.print(tablica[i] + " ");
        }
    }



}
