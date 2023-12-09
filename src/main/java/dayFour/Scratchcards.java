package dayFour;

import utilities.FileReader;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Scratchcards {
    public static void main(String[] args) {
        var data = FileReader.readFile("src/main/java/dayFour/scratchcards.txt");

        var partOneAnswer = 0;

        for (String row : data) {
            var winningNumbers = Arrays.stream(row.substring(row.indexOf(":") + 1, row.indexOf("|")).split(" ")).filter(x -> !x.isEmpty()).collect(Collectors.toList());
            var numbers = Arrays.stream(row.substring(row.indexOf("|") + 2).split(" ")).filter(x -> !x.isEmpty()).collect(Collectors.toList());

            numbers.retainAll(winningNumbers);

            var matches = numbers.size();

            partOneAnswer += calculateCardScore(matches);
        }

        System.out.println("Part One Answer: " + partOneAnswer);
    }

    private static int calculateCardScore(int matches) {
        if (matches > 0) {
            var score = 1;
            for (int i = 0; i < matches - 1; i++) {
                score = score * 2;
            }
            return score;
        }
        return 0;
    }
}
