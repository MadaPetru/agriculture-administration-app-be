package ro.adi.agroadmin.ml;

import org.junit.jupiter.api.Test;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.functions.supportVector.PolyKernel;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// ... (other imports and createARFF method from previous example)

public class SVRExample {

    @Test
    public void main() throws Exception {
        // 1. Problem Statement: Predicting House Prices

        // We want to predict house prices based on their size (in square feet).
        // We have a dataset of house sizes and their corresponding prices.

        // 2. ARFF Data (house_prices.arff)

        // Create the ARFF file programmatically
        createARFF();


        // 3. Load the data
        DataSource source = new DataSource("house_prices.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1)
            data.setClassIndex(data.numAttributes() - 1); // Set the last attribute as the class (target)

        // Build SMOreg with a Polynomial Kernel (e.g., degree 2)
        SMOreg svr = new SMOreg();
        PolyKernel polyKernel = new PolyKernel();
        polyKernel.setExponent(2.0); // Set the degree of the polynomial
        svr.setKernel(polyKernel);
        svr.buildClassifier(data);

        System.out.println(svr);

        // Make a prediction for a new house (e.g., 1750 sq ft)
        // ... (prediction code as in the previous example)
        // 6. Make a prediction for a new house (e.g., 1750 sq ft)
        double[] newValues = new double[data.numAttributes()];
        newValues[0] = 1750; // House size
        DenseInstance newInstance = new DenseInstance(1.0, newValues);
        newInstance.setDataset(data);

        double predictedPrice = svr.classifyInstance(newInstance);
        System.out.println("Predicted price for a 1750 sq ft house: $" + predictedPrice);

        weka.classifiers.Evaluation eval = new weka.classifiers.Evaluation(data);
        eval.evaluateModel(svr, data);
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
        System.out.println("Correlation Coefficient: " + eval.correlationCoefficient());

    }

    public static void createARFF() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("house_prices.arff"));
        writer.write("@relation house_prices\n");
        writer.write("@attribute size numeric\n");
        writer.write("@attribute price numeric\n");
        writer.write("@data\n");
        writer.write("1000,150000\n");
        writer.write("1200,180000\n");
        writer.write("1500,225000\n");
        writer.write("1800,270000\n");
        writer.write("2000,300000\n");
        writer.write("2500,375000\n");
        writer.write("3000,450000\n");
        writer.close();
    }
}