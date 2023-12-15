package dayThree;

import utilities.FileReader;
import java.util.regex.Pattern;

public class GearRatios {
    public static void main(String[] args) {
        var data = FileReader.readFile("src/main/java/dayThree/gearRatios.txt");
        var numberOfRows = data.size();

        var partOneAnswer = 0;

        for (int rowNumber = 0; rowNumber < numberOfRows; rowNumber++) {
            var row = data.get(rowNumber);
            var numbers = row.replaceAll("\\D+", ",").split(",");

            for (String number : numbers) {
                var specialCharacters = 0;

                var indexOfNumber = row.indexOf(number);
                var numberLength = number.length();

                var startIndex = indexOfNumber - 1;
                var endIndex = indexOfNumber + numberLength + 1;

                if (startIndex < 0) {
                    startIndex++;
                }

                if (endIndex >= row.length()) {
                    endIndex--;
                }

                if (rowNumber - 1 >= 0) {
                    if (containsSpecialCharacters(data.get(rowNumber - 1), startIndex, endIndex)) {
                        specialCharacters++;
                    }
                }

                if (containsSpecialCharacters(row, startIndex, endIndex)) {
                    specialCharacters++;
                }

                if (rowNumber + 1 < data.size()) {
                    if (containsSpecialCharacters(data.get(rowNumber + 1), startIndex, endIndex)) {
                        specialCharacters++;
                    }
                }

                if (specialCharacters > 0) {
                    partOneAnswer += Integer.parseInt(number);
                }

                StringBuilder sb = new StringBuilder(row);

                for (int i = startIndex; i < endIndex; i++) {
                    sb.setCharAt(i, '.');
                }

                row = sb.toString();
            }
        }

        System.out.println("Part One Answer: " + partOneAnswer);
    }

    private static boolean containsSpecialCharacters(String row, int startIndex, int endIndex) {
        var pattern = Pattern.compile("[^a-z0-9.]", Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(row.substring(startIndex, endIndex));
        return matcher.find();
    }
}
