import java.util.Scanner;
 
public class Solver {
     
    private static int[][] board = new int[9][9];
     
    public static void main(String[] args) {
     
    Scanner keyboard = new Scanner(System.in);
     
    System.out.println("Welcome to the sudoku solver!");
    System.out.println("Type inital values separated by spaces, \nwith zeros for blank spots.");
     
    for(int y = 0; y < 9; y++) {
        System.out.print("Enter row values: ");
        for(int x = 0; x < 9; x++) {
        board[x][y] = keyboard.nextInt();
        }
        keyboard.nextLine();
    }
     
    System.out.println("Initial state: ");
    printBoard();
 
    solve(0, 0);
 
    System.out.println("Solved state: ");
    printBoard();
    }
 
    public static void solve(int x, int y) {
    // find next open cell
    int[] nextOpenCell = findOpenCell(x, y);
    if(nextOpenCell == null) return;
    int xPos = nextOpenCell[0];
    int yPos = nextOpenCell[1];
     
    System.out.println("xPos: " + xPos + " yPos: " + yPos); // check...
 
    // attempt to fill
    for(int currentNumber = fillCell(1, xPos, yPos); currentNumber != -1; 
        currentNumber = fillCell(currentNumber+1, xPos, yPos)) {
 
        if(xPos >= 8)
        solve(0, yPos+1);
        else
        solve(xPos+1, yPos);
        if(finished()) return;
    }
    }
 
    public static int fillCell(int val, int x, int y) {
        if(val > 9) return -1;
    if(checkRow(val, x) && checkColumn(val, y) && checkBox(val, x, y)) {
        board[x][y] = val;
        return val;
    }
    return fillCell(val+1, x, y);
    }
 
    public static boolean checkRow(int val, int x) {
    for(int i = 0; i < 9; i++) {
        if(board[x][i] == val) return false;
    }
    return true;
    }
 
    public static boolean checkColumn(int val, int y) {
    for(int i = 0; i < 9; i++) {
        if(board[i][y] == val) return false;
    }
    return true;
    }
 
    public static boolean checkBox(int val, int x, int y) {
 
    // find 3x3's upper right x/ys
    int i = (x / 3) * 3;
    int j = (y / 3) * 3;
 
    // check each cell in the 3x3
    for(int row = i; row < i+3; row++) {
        for(int column = j; column < j+3; column++) {
        if(board[row][column] == val) return false;
        }
    }
    return true;
    }
 
    public static void printBoard() {
    for(int y = 0; y < 9; y++) {
        if(y%3 == 0) System.out.println("");
        for(int x = 0; x < 9; x++) {
        if(x%3 == 0) System.out.print("  ");
        if(board[x][y] == 0)
            System.out.print("_ ");
        else
            System.out.print(board[x][y] + " ");
        }
        System.out.println("");
    }
    }
 
    public static int[] findOpenCell(int x, int y) {
    for(int column = y; column < 9; column++) {
        for(int row = x; row < 9; row++) {
        if(board[row][column] == 0) {
            return new int[] {row, column};
        }
        }
    }
    return null;
    } 
 
    public static boolean finished() {
    for(int row = 0; row < 9; row++) {
        for(int column = 0; column < 9; column++) {
        if(board[row][column] == 0)
            return false;
        }
    }
    return true;
    }
}