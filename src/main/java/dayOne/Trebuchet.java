package dayOne;

import static utilities.FileReader.readFile;

public class Trebuchet {
    public static void main(String[] args) {
        var brokenCalibrations = readFile("src/main/java/dayOne/calibrations.txt");

        var calibrations = brokenCalibrations.stream()
                .map(str -> str.replaceAll("[A-Za-z]", ""))
                .toList();

        var total = 0;

        for (String calibration : calibrations) {
            var length = calibration.length();
            var n = "" + calibration.charAt(0) + calibration.charAt(length - 1);
            total += Integer.parseInt(n);
        }

        System.out.println("Total score: " + total);
    }
}
