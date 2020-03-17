import java.util.Scanner;
public class ConnectFour {

    public static void printBoard(char[][] array){//prints rows backwards because the bottom of the board should be row 0
        for(int i = array.length-1; i >= 0; i--){
            for(int j = 0; j <array[i].length; j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void initializeBoard(char[][] array){//just creates the array and sets '-' at every index
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j <array[i].length; j++){
                array[i][j] = '-';
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType){//checks the first row in a column, if there is no chip there it places the chip, if there is a chip there then it checks the row above it
        int i = 0;
        for(i = 0; i < array.length; i++){
            if(array[i][col] == '-'){
                array[i][col] = chipType;
                return i;
            }
        }
        return i;//code wont compile without it because of the bounds
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        int c = 1;
        for(int i = col +1; i < array[row].length; i++){ //counts adjacent chips to the right of the chip
            if(array[row][i] == chipType)
                c++;
            else
                break;
        }
        for(int i = col - 1; i >= 0; i--){ //counts adjacent chips to the left of the chip
            if(array[row][i] == chipType)
                c++;
            else
                break;
        }

        if(c >=4)
            return true;
        c=1; //now checking for up and down


        for(int i = row +1; i < array.length; i++){//counts adjacent chips above the chip
            if(array[i][col] == chipType)
                c++;
            else
                break;
        }

        for(int i = row - 1; i >= 0; i--){//counts adjacent chips below the chip
            if(array[i][col] == chipType)
                c++;
            else
                break;
        }

        if(c >= 4) //checks up and down victory
            return true;
            return false;
    }

    public static boolean checkDraw(char[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j <array[i].length; j++){
                if(array[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What would you like the height of the board to be?");
        int h = in.nextInt();
        System.out.println("What would you like the length of the board to be?");
        int l = in.nextInt();
        char[][] array = new char[h][l];
        initializeBoard(array);
        printBoard(array);

        System.out.println("\nPlayer 1: x  \nPlayer 2: o ");
        int c = 2;
        int col;
        int row;
        while(c != 0){
            if(c%2 == 0){
                System.out.println("Player 1: Which column would you like to choose?");
                c++;
                col = in.nextInt();
                row = insertChip(array, col, 'x'); //insertChip returns the row the chip was placed in
                printBoard(array);
                if(checkIfWinner(array, col, row, 'x')){ //win condition, breaks the main while loop if true
                    System.out.println("Player 1 won the game!");
                    break;
                }
                if(checkDraw(array) == true){
                    System.out.println("Draw. Nobody wins.");
                    break;
                }
            }
            else{
                System.out.println("Player 2: Which column would you like to choose? ");
                c++;
                col = in.nextInt();
                row =insertChip(array, col, 'o');
                printBoard(array);
                if(checkIfWinner(array, col, row, 'o')){ //win condition, breaks the main while loop if true
                    System.out.println("Player 2 won the game!");
                    break;
                }
                if(checkDraw(array) == true){
                    System.out.println("Draw. Nobody wins.");
                    break;
                }
            }
        }
    }
}
