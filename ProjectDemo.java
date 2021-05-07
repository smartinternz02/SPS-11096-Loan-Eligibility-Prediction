package in.project;


import java.io.File;
import java.io.IOException;


import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.*;


import java.util.Arrays;



public class ProjectDemo 
{
	public static Instances getInstances (String fileName)
	{
		DataSource source;
		Instances dataset = null;
		try {
			source = new DataSource(fileName);
			dataset = source.getDataSet();
			dataset.setClassIndex(dataset.numAttributes()-1);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return dataset;
		/*
		System.out.println(fileName);
		int classIdx = 1;
		
		ArffLoader loader = new ArffLoader();
		//loader.setFile(new File(fileName));
		
		loader.setSource(ProjectDemo.class.getResourceAsStream(fileName));
		Instances dataSet = loader.getDataSet();
		dataSet.setClassIndex(classIdx);
		return dataSet;
		*/
		
	}
	
	public static void main(String[] args) throws Exception{

		Instances train_data = getInstances("D:\\ellipseProjects\\in.project\\src\\main\\java\\in\\project\\trainLoan1.arff");
		Instances test_data  = getInstances("D:\\ellipseProjects\\in.project\\src\\main\\java\\in\\project\\testLoan4.arff");
		//System.out.println(train_data.size());
		
	
		Classifier classifier = new weka.classifiers.functions.Logistic();
		
		classifier.buildClassifier(train_data);
		
		
		
		Evaluation eval = new Evaluation(train_data);
		eval.evaluateModel(classifier, test_data);
		
		System.out.println("** Logistic Regression Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
//		System.out.print(" the expression for the input data as per algorithm is ");
//		System.out.println(classifier);
		
		double confusion[][] = eval.confusionMatrix();
		System.out.println("Confusion matrix:");
		for (double[] row : confusion)
			System.out.println(	 Arrays.toString(row));
		System.out.println("-------------------");

		System.out.println("Area under the curve");
		System.out.println( eval.areaUnderROC(0));
		System.out.println("-------------------");
		
		System.out.println(Evaluation.getAllEvaluationMetricNames());
		
		System.out.print("Recall :");
		System.out.println(Math.round(eval.recall(1)*100.0)/100.0);
		
		System.out.print("Precision:");
		System.out.println(Math.round(eval.precision(1)*100.0)/100.0);
		System.out.print("F1 score:");
		System.out.println(Math.round(eval.fMeasure(1)*100.0)/100.0);
		
		System.out.print("Accuracy:");
		double acc = eval.correct()/(eval.correct()+ eval.incorrect());
		System.out.println(Math.round(acc*100.0)/100.0);
		
		
		System.out.println("-------------------");
		Instance predicationDataSet = test_data.get(2);
		double value = classifier.classifyInstance(predicationDataSet);
		
		System.out.println("Predicted label:");
		System.out.print(value);
	
		
		System.out.println("** Linear Regression Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);

		

		
	}


}