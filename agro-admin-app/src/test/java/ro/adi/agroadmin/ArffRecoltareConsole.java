package ro.adi.agroadmin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ArffRecoltareConsole {

    public static void printRecoltareResults(String inputArffPath, double harvestCoefficient) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputArffPath))) {

            String line;
            boolean dataSection = false;
            List<String> attributeTypes = new java.util.ArrayList<>();
            List<String> attributeNames = new java.util.ArrayList<>();

            // Read header and attributes (only to get attribute types)
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("@attribute")) {
                    String[] parts = line.split(" ");
                    attributeNames.add(parts[1]);
                    attributeTypes.add(parts[2]);
                } else if (line.startsWith("@data")) {
                    dataSection = true;
                    break;
                }
            }

            if (!dataSection) {
                throw new IOException("No @data section found in input ARFF file.");
            }

            // Process data instances and print RECOLTARE results
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("%")) {
                    continue;
                }

                String[] values = line.split(",");
                if (values.length != attributeTypes.size()) {
                    System.out.println("Line with error: " + line);
                    continue;
                }

                double estimatedCost = Double.parseDouble(values[1]);
                String estimatedHarvestMeasureType = values[7];
                String estimatedCostCurrencyType = values[8];
                String estimatedRevenueCurrencyType = values[9];
                String plantType = values[10];

                double estimatedHarvest = calculateEstimatedHarvest(values);
                double estimatedRevenue = estimatedHarvest * harvestCoefficient;

                // Print RECOLTARE result to the console
                System.out.println("RECOLTARE," + estimatedCost + "," + estimatedHarvest + "," + estimatedRevenue + "," +
                        estimatedHarvestMeasureType + "," + estimatedCostCurrencyType + "," +
                        estimatedRevenueCurrencyType + "," + plantType);
            }
        }
    }

    private static double calculateEstimatedHarvest(String[] values) {
        // Implement your more complex harvest calculation logic here.
        double baseHarvest = 2.0;
        if ("PORUMB".equals(values[10])) {
            baseHarvest *= 1.2;
        }
        return baseHarvest;
    }

    public static void main(String[] args) {
        String inputArffPath = "input.arff";
        double harvestCoefficient = 1200.0;

        try {
            printRecoltareResults(inputArffPath, harvestCoefficient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}