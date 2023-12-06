package dayTwo;

import java.util.Arrays;

import static utilities.FileReader.readFile;

public class CubeGame {
    public static void main(String[] args) {
        var data = readFile("src/main/java/dayTwo/games.txt");

        var games = data.stream()
                .map(row -> row.substring(8))
                .map(row -> row.split(";")).toList();

        var partOneAnswer = 0;
        var gameNumber = 1;

        for (String[] game : games) {
            if (isPossible(game)) {
                partOneAnswer += gameNumber;
            }
            gameNumber++;
        }

        System.out.println("Part One Answer: " + partOneAnswer);

        var partTwoAnswer = games.stream()
                .mapToInt(CubeGame::getLowestCubeCounts)
                .sum();

        System.out.println("Part Two Answer: " + partTwoAnswer);
    }

    private static int getLowestCubeCounts(final String[] game) {
        var minBlue = 0;
        var minRed = 0;
        var minGreen = 0;

        for (String rounds : game) {
            for (String round : rounds.split(",")) {
                var currCubeCount = Integer.parseInt(removeNoneNumericCharacters(round));
                if (round.contains("blue") && currCubeCount > minBlue) {
                    minBlue = currCubeCount;
                }

                if (round.contains("red") && currCubeCount > minRed) {
                    minRed = currCubeCount;
                }

                if (round.contains("green") && currCubeCount > minGreen) {
                    minGreen = currCubeCount;
                }
            }
        }
        return minBlue * minRed * minGreen;
    }

    private static boolean isPossible(final String[] game) {
        var maxBlue = 14;
        var maxRed = 12;
        var maxGreen = 13;
        var isPossible = true;

        for (String rounds : game) {
            for (String round : rounds.split(",")) {
                if (round.contains("blue") && Integer.parseInt(removeNoneNumericCharacters(round)) > maxBlue) {
                    isPossible = false;
                }

                if (round.contains("red") && Integer.parseInt(removeNoneNumericCharacters(round)) > maxRed) {
                    isPossible = false;
                }

                if (round.contains("green") && Integer.parseInt(removeNoneNumericCharacters(round)) > maxGreen) {
                    isPossible = false;
                }
            }

        }
        return isPossible;
    }

    static String removeNoneNumericCharacters(String str) {
        return str.replaceAll("[^0-9]", "");
    }
}
