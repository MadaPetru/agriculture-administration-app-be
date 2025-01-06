package ro.adi.agroadmin.ml;

import org.junit.jupiter.api.Test;
import ro.adi.agroadmin.ArffGenerator;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaLinearRegression {
    @Test
    void linearRegression() throws Exception {
        // Load data
        var filePath = "src/test/resources/ml/crop_data.arff";
        DataSource source = new DataSource(filePath); // Path to your ARFF file
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1); // Set last attribute as class
        }

        // Build linear regression model
        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(data);

        // Print model details
        System.out.println(lr);

        // Make a prediction (example)
        double[] newInstance = {130, 65, 85}; // Ploughing, seeds, fertilizer costs
        double prediction = lr.classifyInstance(new weka.core.DenseInstance(1.0, newInstance));
        System.out.println("Predicted yield for [130, 65, 85]: " + prediction);
    }

    @Test
    public void generateArffFile() {
        var filePath = "src/test/resources/ml/crop_data.arff";
        ArffGenerator.generate(filePath);
    }
}
