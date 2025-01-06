package ro.adi.agroadmin;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArffGenerator {

    private static String sql = "SELECT operation, estimated_cost, estimated_harvest, estimated_revenue, plant_type, area, roughly_distance_from_farm" +
            " FROM " + "farming_land_operation_history" + " JOIN farming_land AS fm ON fm.id = farming_land_id";

    public static void generateArff(String jdbcUrl, String username, String password, String arffFilePath) throws SQLException, IOException {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            try (FileWriter writer = new FileWriter(arffFilePath)) {
                // Write ARFF header
                writer.write("@relation " + "farming_land_operation_history" + "\n\n");

                List<String> attributeTypes = new ArrayList<>();
                List<Integer> includedColumnIndexes = new ArrayList<>(); // Keep track of included columns

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);

                    includedColumnIndexes.add(i); // Add the index of the included column
                    int columnType = metaData.getColumnType(i);
                    String attributeType;

                    switch (columnType) {
                        case Types.INTEGER:
                        case Types.DOUBLE:
                        case Types.FLOAT:
                        case Types.NUMERIC:
                            attributeType = "numeric";
                            break;
                        case Types.DATE:
                        case Types.TIMESTAMP:
                            attributeType = "date \"yyyy-MM-dd HH:mm:ss\"";
                            break;
                        case Types.BOOLEAN:
                            attributeType = "{true,false}";
                            break;
                        default:
                            attributeType = "string";
                    }
                    attributeTypes.add(attributeType);
                    writer.write("@attribute " + columnName + " " + attributeType + "\n");
                }

                writer.write("\n@data\n");

                // Write data instances (only included columns)
                while (resultSet.next()) {
                    for (int includedColumnIndex : includedColumnIndexes) {
                        String value;
                        if (attributeTypes.get(includedColumnIndexes.indexOf(includedColumnIndex)).startsWith("date")) {
                            Timestamp timestamp = resultSet.getTimestamp(includedColumnIndex);
                            if (timestamp != null) {
                                value = "\"" + timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\"";
                            } else {
                                value = "?";
                            }
                        } else if (attributeTypes.get(includedColumnIndexes.indexOf(includedColumnIndex)).equals("{true,false}")) {
                            value = String.valueOf(resultSet.getBoolean(includedColumnIndex));
                        } else {
                            value = resultSet.getString(includedColumnIndex);
                            if (value == null) {
                                value = "?";
                            }
                        }
                        writer.write(value);
                        if (includedColumnIndexes.indexOf(includedColumnIndex) < includedColumnIndexes.size() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");
                }
            }
        }
    }

    public static void generate(String arffFilePath) {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;trustServerCertificate=true"; // Replace with your DB URL
        String username = "sa"; // Replace with your DB username
        String password = "passwordAAA111!!!"; // Replace with your DB password
        try {
            generateArff(jdbcUrl, username, password, arffFilePath);
            System.out.println("ARFF file generated successfully: " + arffFilePath);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}