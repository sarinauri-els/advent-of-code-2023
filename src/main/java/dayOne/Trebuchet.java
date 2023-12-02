package dayOne;

import java.util.List;

import static utilities.FileReader.readFile;

public class Trebuchet {
    public static void main(String[] args) {
        var partOneInput = readFile("src/main/java/dayOne/calibrations.txt");
        var calibrations = removeLetters(partOneInput);
        var partOneTotal = getPartOneTotal(calibrations);
        System.out.println("Part One Answer: " + partOneTotal);

        var partTwoInput = readFile("src/main/java/dayOne/calibrations.txt");
        var numbers = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        var partTwoTotal = partTwoTotal(partTwoInput, numbers);
        System.out.println("Part Two Answer: " + partTwoTotal);
    }

    private static int partTwoTotal(final List<String> partTwoInput, final List<String> numbers) {
        var total = 0;
        var rowNumber = 1;

        for (String calibration : partTwoInput) {
            System.out.println();
            System.out.println("Row Number, String: " + rowNumber + ", " + calibration);

            var length = calibration.length();

            // when you get the first number, also return the
            var firstNumber = findNumber(calibration, numbers, 0, 1, false);
            var secondNumber = findNumber(calibration, numbers, length - 1, length, true);

            var parsedFirstNumber = convertStringToInteger(firstNumber);
            var parsedSecondNumber = convertStringToInteger(secondNumber);

            total += Integer.parseInt(parsedFirstNumber + parsedSecondNumber);

            rowNumber++;
        }

        return total;
    }

    private static int getPartOneTotal(final List<String> calibrations) {
        var total = 0;

        for (String calibration : calibrations) {
            var length = calibration.length();
            var n = "" + calibration.charAt(0) + calibration.charAt(length - 1);
            total += Integer.parseInt(n);
        }
        return total;
    }

    private static List<String> removeLetters(final List<String> partOneInput) {
        return partOneInput.stream()
                .map(str -> str.replaceAll("[A-Za-z]", ""))
                .toList();
    }

    static String compareStrings(List<String> numbers, String subString) {
        for (String number : numbers) {
            if (subString.contains(number)) {
                return number;
            }
        }
        return null;
    }

    static String findNumber(String calibration, List<String> numbers, int startIndex, int endIndex, boolean reverseOrder) {
        System.out.println();
        System.out.println("0. Starting index, end index: " + startIndex + ", " + endIndex);
        var subString = calibration.substring(startIndex, endIndex);
//        System.out.println("1. Current substring: " + subString);

        var number = compareStrings(numbers, subString);
        System.out.println("2. Substring contains: " + number);

        if (number != null) {
            System.out.println("3. Returning: " + number);
            return number;
        } else if (endIndex < calibration.length() && !reverseOrder) {
            System.out.println("4. Number was null, increasing end index");
            endIndex++;
            return findNumber(calibration, numbers, startIndex, endIndex, false);
        } else if (startIndex > 0 && reverseOrder) {
            System.out.println("5. Number was null, decreasing start index from " + startIndex + " to " + (startIndex - 1));
            startIndex--;
            return findNumber(calibration, numbers, startIndex, endIndex, true);
        } else {
            System.out.println("6. Returning default null value");
            return null;
        }
    }

    static String convertStringToInteger(String strNumber) {
        if (strNumber.equals("one") || strNumber.equals("1")) {
            return "1";
        }

        if (strNumber.equals("two") || strNumber.equals("2")) {
            return "2";
        }

        if (strNumber.equals("three") || strNumber.equals("3")) {
            return "3";
        }

        if (strNumber.equals("four") || strNumber.equals("4")) {
            return "4";
        }

        if (strNumber.equals("five") || strNumber.equals("5")) {
            return "5";
        }

        if (strNumber.equals("six") || strNumber.equals("6")) {
            return "6";
        }

        if (strNumber.equals("seven") || strNumber.equals("7")) {
            return "7";
        }

        if (strNumber.equals("eight") || strNumber.equals("8")) {
            return "8";
        }

        if (strNumber.equals("nine") || strNumber.equals("9")) {
            return "9";
        }

        return "";
    }
}
