import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        int row = Integer.parseInt(input.split(",")[0].trim());
        int col = Integer.parseInt(input.split(",")[1].trim());

        int matrix[][]= new int[row][col];
        //New Matrix to check
        int[][] newMatrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            String columnInput = scanner.nextLine();
            for (int j = 0; j < col; j++) {
                matrix[i][j]= Integer.parseInt(columnInput.split("")[j].trim());
                newMatrix[i][j]= Integer.parseInt(columnInput.split("")[j].trim());
            }
        }

        String inputCoordinates = scanner.nextLine();

        int rowIndex = Integer.parseInt(inputCoordinates.split(",")[0].trim());
        int colIndex = Integer.parseInt(inputCoordinates.split(",")[1].trim());
        int loopIndex = Integer.parseInt(inputCoordinates.split(",")[2].trim());



        //Counter for times it changed
        int counter = 0;
        if (matrix[rowIndex][colIndex] == 1){
            counter = 1;
        }

        //Loop through the whole matrix
        while (0 < loopIndex) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    int greens = 0;

                    int startX = i - 1;
                    int startY = j - 1;
                    int endX = i + 1;
                    int endY = j + 1;

                    //Check if is in bounds
                    if (startX < 0) {
                        startX = 0;
                    }
                    if (startY < 0) {
                        startY = 0;
                    }
                    if (endX >= matrix.length) {
                        endX = matrix.length - 1;
                    }
                    if (endY >= matrix[i].length) {
                        endY = matrix[i].length - 1;
                    }

//                    for (int rowNum = startX; rowNum <= endX; rowNum++) {
//                        for (int colNum = startY; colNum <= endY; colNum++) {
//                            if (rowNum != i || colNum != j) {
//                                if (matrix[rowNum][colNum] == 1) {
//                                    greens++;
//                                } else {
//                                    reds++;
//                                }
//                            }
//                        }
//                    }
                    greens = greens(matrix, startX, startY, endX, endY, i, j);
                    if (greens == 3 || greens == 6 && newMatrix[i][j] == 0) {
                        if (i == rowIndex && j == colIndex) {
                            counter++;
                        }
                        newMatrix[i][j] = 1;
                    } else if (greens == 0 || greens == 1 || greens == 4 || greens == 5 || greens == 7 || greens == 8 && newMatrix[i][j] == 1) {
                        newMatrix[i][j] = 0;
                    }
                }
            }
            switchMatrix(matrix, newMatrix);
            printMatrix(matrix);
            loopIndex--;
        }
        System.out.println(counter);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int greens (int [][] matrix, int startX, int startY, int endX, int endY, int i, int j) {
        int greens = 0;
        for (int rowNum = startX; rowNum <= endX; rowNum++) {
            for (int colNum = startY; colNum <= endY; colNum++) {
                if (rowNum != i || colNum != j) {
                    if (matrix[rowNum][colNum] == 1) {
                        greens++;
                    }
                }
            }
        }
        return greens;
    }

    private static void switchMatrix(int[][]matrix, int[][]newMatrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }
}
