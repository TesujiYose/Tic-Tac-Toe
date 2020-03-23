package tictactoe;

import java.util.Scanner;

public class Main {



    public static void printBoard(char[][] battlefield) {
        System.out.println("---------");
        for (int i = 0 ; i < 3 ; i++) {
            System.out.print("| ");
            for (int j = 0 ; j < 3 ; j++) {
                System.out.print(battlefield[i][j]+" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

    }



    public static boolean isGameEnd (char[][] battlefield) {
        boolean xWin = false;
        boolean oWin = false;
        boolean gameFin = true;
        int numO = 0;
        int numX = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (battlefield[i][j]=='X') numX++;
                if (battlefield[i][j]=='O') numO++;
                if (battlefield[i][j]=='_' || battlefield[i][j]==' ') gameFin = false;
                if( (battlefield[0][j]==battlefield[1][j])&&(battlefield[0][j]==battlefield[2][j])) {
                   if (battlefield[0][j]=='O') oWin = true;
                   if (battlefield[0][j]=='X') xWin = true;
                }
            }
            if((battlefield[i][0]==battlefield[i][1])&&(battlefield[i][0]==battlefield[i][2])) {
                if (battlefield[i][0]=='O') oWin = true;
                if (battlefield[i][0]=='X') xWin = true;
            }
        }

        if (((battlefield[0][0]==battlefield[1][1])&&(battlefield[0][0]==battlefield[2][2])) ||
         ((battlefield[2][0]==battlefield[1][1])&&(battlefield[2][0]==battlefield[0][2]))) {
            if (battlefield[1][1]=='O') oWin = true;
            if (battlefield[1][1]=='X') xWin = true;
        }

        //System.out.print("State"+xWin+oWin+gameFin);
        if ((numO>numX+1) || (numO+1<numX)) {
          //  System.out.print("Impossible");
            return false;
        }
        if (xWin && oWin) {
           // System.out.print("Impossible");
            return false;
        }

        if (!xWin && !oWin && gameFin) {
            printBoard(battlefield);
            System.out.print("Draw");
            return true;
        }

        if (xWin) {
            printBoard(battlefield);
            System.out.print("X wins");
            return true;
        }
        if (oWin) {
            printBoard(battlefield);
            System.out.print("O wins");
            return true;
        }

        if (!xWin && !oWin && !gameFin) {
            //System.out.print("Game not finished");
            return false;
        }

        return false;
    }

    public static void move (char[][] battlefield, int x, int y, char ch) {
        battlefield[x][y] = ch;
       // printBoard(battlefield);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean turnX = true;
        //System.out.print("Enter cells: ");
        String state = "_________";


        char[][] battlefield = {   {state.charAt(0), state.charAt(1), state.charAt(2)},
                                    {state.charAt(3), state.charAt(4), state.charAt(5)},
                                    {state.charAt(6), state.charAt(7), state.charAt(8)}
        };

        String[][] reference = {
                {"1 3", "2 3", "3 3"},
                {"1 2", "2 2", "3 2"},
                {"1 1", "2 1", "3 1"}
        };
        while (!isGameEnd(battlefield)){
        printBoard(battlefield);
      //  printState(battlefield);



        int x = 0;
        int y = 0;


        boolean correctMove = false;
        do {
            System.out.print("Enter the coordinates: ");
            String line = sc.nextLine();

            String[] arr = line.split(" ");
            try {


                x = Integer.parseInt(arr[0]);
                y = Integer.parseInt(arr[1]);

                if (((x>3) || (x<1)) || (y>3) || (y<1) ) throw new IndexOutOfBoundsException();
            }
            catch (NumberFormatException e){
                System.out.println("You should enter numbers!");
                continue;
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (line.equals(reference[i][j])) {

                        if (battlefield[i][j]=='_'){
                            correctMove = true;
                            x = i;
                            y = j;
                        }




                    }
                }
            }

            if (!correctMove) System.out.println("This cell is occupied! Choose another one!");


        }while (!correctMove );


        if (turnX){
            move(battlefield, x, y, 'X');
            turnX = !turnX ;
        }
        else {
            move(battlefield, x, y, 'O');
            turnX = !turnX ;
        }


        }


    }
}
