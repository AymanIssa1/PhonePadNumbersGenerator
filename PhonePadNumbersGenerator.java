package com.example.hibooks;


import java.util.ArrayList;

public class PhonePadNumbersGenerator {

    /**
     * A standard phone number pad has the following layout
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 0
     * <p>
     * Using this layout and a starting digit we can generate numbers as follows: The first digit is the
     * starting digit and each subsequent digit is directly left, right, above or below the previous digit on
     * the number pad. For example if the starting digit is 1, 1256 is a valid number, but 1289 is not
     * valid because 8 is not directly next to 2. Write a function that takes a starting digit d and an
     * integer n as input and returns a list of all unique numbers of length n generated in this way.
     * <p>
     * Test cases:
     * <p>
     * f(5, 1) = [5]
     * f(1, 3) = [121, 123, 125, 141, 145, 147]
     */

    public static void main(String... args) {
        System.out.print(solution(5, 1));
        System.out.print(solution(1, 3));
        System.out.print(solution(7, 3));
    }

    public static ArrayList<Integer> solution(int starterNumber, int totalCycles) {
        int[][] phonePad = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {-1, 0, -1}};

        ArrayList<Integer> result = new ArrayList<>();
        result.add(starterNumber);

        return generateNumbers(phonePad, result, 1, totalCycles);
    }

    public static ArrayList<Integer> generateNumbers(int[][] phonePad, ArrayList<Integer> numbers, int currentCycle, int totalCycles) {
        if (totalCycles == currentCycle)
            return numbers;

        ArrayList<Integer> newNumbers = new ArrayList<>();
        for (int number : numbers) {
            int lastDigit = getLastDigit(number);
            Point lastDigitPoint = getDigitPosition(phonePad, lastDigit);

            if (lastDigitPoint.x - 1 != -1)
                if (phonePad[lastDigitPoint.x - 1][lastDigitPoint.y] != -1)
                    newNumbers.add(number * 10 + phonePad[lastDigitPoint.x - 1][lastDigitPoint.y]);

            if (lastDigitPoint.x + 1 != 4)
                if (phonePad[lastDigitPoint.x + 1][lastDigitPoint.y] != -1)
                    newNumbers.add(number * 10 + phonePad[lastDigitPoint.x + 1][lastDigitPoint.y]);

            if (lastDigitPoint.y - 1 != -1)
                if (phonePad[lastDigitPoint.x][lastDigitPoint.y - 1] != -1)
                    newNumbers.add(number * 10 + phonePad[lastDigitPoint.x][lastDigitPoint.y - 1]);


            if (lastDigitPoint.y + 1 != 3)
                if (phonePad[lastDigitPoint.x][lastDigitPoint.y + 1] != -1)
                    newNumbers.add(number * 10 + phonePad[lastDigitPoint.x][lastDigitPoint.y + 1]);

        }

        return generateNumbers(phonePad, newNumbers, ++currentCycle, totalCycles);
    }

    public static Point getDigitPosition(int[][] phonePad, int digit) {
        for (int x = 0; x < phonePad.length; x++) {
            for (int y = 0; y < phonePad[x].length; y++) {
                if (phonePad[x][y] == digit)
                    return new Point(x, y);
            }
        }
        return null;
    }

    public static int getLastDigit(int n) {
        return (n % 10);
    }

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
