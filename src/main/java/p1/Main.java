package p1;

import java.util.Scanner;

/**
 * Created by Kamil on 06.05.2020.
 */
public class Main {

    public static void main(String[] args) {

        //Wymiary 1 tabeli
        final int x1 = 3;
        final int y1 = 3;

        //Wymiary 2 tabeli
        final int x2 = 3;
        final int y2 = 1;

        //Wymiary 3 tabeli, ktora jest polaczeniem 1 i 2
        final int x3 = 3;
        final int y3 = 4;

        System.out.println("Podaj wartosci dla 1 tabeli");
        double tab1[][] = createTable(x1, y1);
        System.out.println();

        System.out.println("Podaj wartosci dla 2 tabeli");
        double tab2[][] = createTable(x2, y2);
        System.out.println();

        System.out.println("Aktualizacja - Macierze dodane");
        double finalTab[][] = addTable(tab1, tab2, x3, y3);
        System.out.println();

        System.out.println("Tak wyglada Twoje rownanie");
        showTable(finalTab, x3, y3);
        System.out.println();

        System.out.println("Zmiana wartosci pola [0][0] na wartosc 1");
        prepareEquation(finalTab, 1);
        showTable(finalTab, x3, y3);
        System.out.println();

        System.out.println("Tak wyglada Twoje rownanie po wyzerowaniu 1 kolumny");
        changeFirstColumn(finalTab);
        showTable(finalTab, x3, y3);
        System.out.println();

        System.out.println("Zmiana wartosci pola [1][1] na wartosc 1");
        prepareEquation(finalTab, 2);
        showTable(finalTab, x3, y3);
        System.out.println();

        System.out.println("Tak wyglada Twoje rownanie po wyzerowaniu 2 kolumny");
        changeSecondColumn(finalTab);
        showTable(finalTab, x3, y3);
        System.out.println();

        System.out.println("Wynik rozwiazania: ");
        createSolution(finalTab);

    }

    public static double[][] createTable(int x, int y) {

        double[][] tab = new double[x][y];

        double liczba = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.println("Podaj wartosc w wierszu " + (i+1) + " w kolumnie " + (j+1));
                Scanner sc = new Scanner(System.in);
                liczba = sc.nextDouble();
                tab[i][j] = liczba;
            }
        }

        return tab;
    }


    private static double[][] addTable(double[][] tab1, double[][] tab2, int x3, int y3) {

        double[][] finalTab = new double[x3][y3];

        for(int i =0; i<x3; i++)
            for(int j = 0; j<y3; j++){
                if(j!=3) {
                    finalTab[i][j] = tab1[i][j];
                }else
                    finalTab[i][j] = tab2[i][0];
            }

        return finalTab;
    }

    public static void showTable(double[][] FinalTab, int x3, int y3) {

        for (int i = 0; i < x3; i++) {
            for (int j = 0; j < y3; j++) {
                if(j==3){
                    System.out.print("|" + FinalTab[i][j]);
                }else {
                    if (FinalTab[i][j] < 0)
                        System.out.print(FinalTab[i][j] + " ");
                    else {
                        System.out.print(" " + FinalTab[i][j] + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void prepareEquation(double[][] tab, int repetition) {

        double currentCell = 0; //zmienna ktora odpowiada za obecnie badane pole w tablicy, uzyta dla lepszej czytelnosci kodu (nie jest konieczna, mozna ja zastapic tab[i][j]
        double inverseValue = 0; //to Zmienna ktora jest odwrotna do currentCell

        currentCell = tab[repetition-1][repetition-1];

        inverseValue = 1 / currentCell;

            //DLA ZMIENNEJ ROWNEJ 1
            if (currentCell == 1) {
                System.out.println("Wartosc Pola jest rowna 1, brak modyfikacji");
            }

            //DLA ZMIENNEJ ROWNEJ 0
            else if (currentCell == 0) {
                currentCell = currentCell + 1;
                tab[repetition-1][repetition-1] = currentCell;
                switch (repetition) {
                    case 1:
                        tab[0][1] = tab[0][1] + 1;
                        tab[0][2] = tab[0][2] + 1;
                        tab[0][3] = tab[0][3] + 1;
                        break;
                    case 2:
                        tab[1][2] = tab[1][2] + 1;
                        tab[1][3] = tab[1][3] + 1;
                        break;
                }
            }
            //DLA ZMIENNEJ ROZNEJ OD 0 I 1
            else {
                tab[repetition-1][repetition-1] = currentCell * inverseValue;

                //ZAMIANA POZOSTALYCH KOMOREK W DANYM WIERSZU
                switch (repetition) {
                    case 1:
                        tab[0][1] = tab[0][1] * inverseValue;
                        tab[0][2] = tab[0][2] * inverseValue;
                        tab[0][3] = tab[0][3] * inverseValue;
                        break;
                    case 2:
                        tab[1][2] = tab[1][2] * inverseValue;
                        tab[1][3] = tab[1][3] * inverseValue;
                        break;
                }
            }
        }

    private static void changeFirstColumn(double[][] finalTab) {

        //TWORZYMY ZMIENNE a i b, KTORE BEDA ODPOWIEDZIALNE ZA ZEROWANIE POL: [1][0] i [2][[0]
        double a = -finalTab[1][0]/finalTab[0][0]; //rownanie na a otrzymalisy z przeksztalcenia rownania finalTab[0][0]*x + finalTab[1][0] = 0
        double b = -finalTab[2][0]/finalTab[0][0]; //analogicznie jak wyzej

        //ZMIANA WIERSZA [1]
        finalTab[1][0] = a*finalTab[0][0]+finalTab[1][0];
        finalTab[1][1] = a*finalTab[0][1]+finalTab[1][1];
        finalTab[1][2] = a*finalTab[0][2]+finalTab[1][2];
        finalTab[1][3] = a*finalTab[0][3]+finalTab[1][3];

        //ZMIANA WIERSZA [2]
        finalTab[2][0] = b*finalTab[0][0]+finalTab[2][0];
        finalTab[2][1] = b*finalTab[0][1]+finalTab[2][1];
        finalTab[2][2] = b*finalTab[0][2]+finalTab[2][2];
        finalTab[2][3] = b*finalTab[0][3]+finalTab[2][3];
    }


    private static void changeSecondColumn(double[][] finalTab) {

        //TWORZYMY ZMIENNE a KTORA BEDZIE ODPOWIEDZIALNE ZA ZEROWANIE POLA [2][1]
        double a = -finalTab[2][1]/finalTab[1][1]; //rownanie na a otrzymalismy z przeksztalcenia rownania finalTab[1][1] *a + tab[2][1] = 0

        //ZAMIANA WIERSZA [2]
        finalTab[2][1] = a * finalTab[1][1] + finalTab [2][1];
        finalTab[2][2] = a * finalTab[1][2] + finalTab [2][2];
        finalTab[2][3] = a * finalTab[1][3] + finalTab [2][3];

    }


    private static void createSolution(double[][] finalTab) {

        double x1, x2, x3 ;

        x3 = finalTab[2][3]/finalTab[2][2];
        x2 = finalTab[1][3] - x3*finalTab[1][2];
        x1 = finalTab[0][3] - x2* finalTab[0][1] - x3 * finalTab[0][2];

        System.out.println("X1 = " + x1);
        System.out.println("X2 = " + x2);
        System.out.println("X3 = " + x3);
    }

}

//METODA ZMIENIAJACA WARTOSCI KOLUMN [0][0], [1][1], [2][2] NA WARTOSC 1 - MACIERZ O WYMIARACH 3X4
  /*  public static void prepareEquation(double[][] tab) {

        double Zamienna = 0; //zmienan ktora odpowiada za obecnie badane pole w tablicy, uyta dla lepszej czytelnosci kodu (nie jest konieczna, mozna ja zastabic tab[i][j]
        double Odwrotna = 0; //to Zmienna ktora jest odwrotna i przeciwna jednoczesnie w przypadku kiedy zmienna jest mniejsza od 0

        for (int i = 0, j = 0; i < 3; i++, j++) {

            Zamienna = tab[i][j];

            Odwrotna = 1 / Zamienna;

            //DLA ZMIENNEJ ROWNEJ 1
            if (Zamienna == 1) {
                continue;
            }

            //DLA ZMIENNEJ ROWNEJ 0
            else if (Zamienna == 0) {
                Zamienna = Zamienna + 1;
                tab[i][j] = Zamienna;
                switch (i) {
                    case 0:
                        tab[i][j + 1] = tab[i][j + 1] + 1;
                        tab[i][j + 2] = tab[i][j + 2] + 1;
                        tab[i][j + 3] = tab[i][j + 3] + 1;
                        break;
                    case 1:
                        tab[i][j - 1] = tab[i][j - 1] + 1;
                        tab[i][j + 1] = tab[i][j + 1] + 1;
                        tab[i][j + 2] = tab[i][j + 2] + 1;
                        break;
                    case 2:
                        tab[i][j - 2] = tab[i][j - 2] + 1;
                        tab[i][j - 1] = tab[i][j - 1] + 1;
                        tab[i][j + 1] = tab[i][j + 1] + 1;
                        break;
                }
            }
            //DLA ZMIENNEJ ROZNEJ OD 0 I 1
            else {
                tab[i][j] = Zamienna * Odwrotna;

                //ZAMIANA POZOSTALYCH KOMOREK W DANYM WIERSZU
                switch (i) {
                    case 0:
                        tab[i][j + 1] = tab[i][j + 1] * Odwrotna;
                        tab[i][j + 2] = tab[i][j + 2] * Odwrotna;
                        tab[i][j + 3] = tab[i][j + 3] * Odwrotna;
                        break;
                    case 1:
                        tab[i][j - 1] = tab[i][j - 1] * Odwrotna;
                        tab[i][j + 1] = tab[i][j + 1] * Odwrotna;
                        tab[i][j + 2] = tab[i][j + 2] * Odwrotna;
                        break;
                    case 2:
                        tab[i][j - 2] = tab[i][j - 2] * Odwrotna;
                        tab[i][j - 1] = tab[i][j - 1] * Odwrotna;
                        tab[i][j + 1] = tab[i][j + 1] * Odwrotna;
                        break;
                }
            }
        }
    }*/