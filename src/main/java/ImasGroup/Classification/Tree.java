package ImasGroup.Classification;
import weka.classifiers.trees.J48;

import weka.core.Instances;
import weka.classifiers.Classifier;

import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.ArffSaver;


import java.io.File;

public class Tree {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Classifier m_classifier = new J48();
		    
		    Instances allData = DataSource.read("/Users/edison/Desktop/IMAS/Iris/iris.csv");
	        ArffSaver saver = new ArffSaver();
	        saver.setInstances(allData);
	        saver.setFile(new File("/Users/edison/Desktop/IMAS/Iris/iris.arff"));
	        saver.writeBatch();               
		    Instances allData1 = DataSource.read("/Users/edison/Desktop/IMAS/Iris/iris.csv");
	        ArffSaver saver1 = new ArffSaver();
	        saver1.setInstances(allData1);
	        saver1.setFile(new File("/Users/edison/Desktop/IMAS/Iris/iris.arff"));
	        saver1.writeBatch();    

	        File inputFile = new File("/Users/edison/Desktop/IMAS/Iris/iris.arff");
            ArffLoader atf = new ArffLoader();   
            atf.setFile(inputFile);  
            Instances instancesTrain = atf.getDataSet();

           inputFile = new File("/Users/edison/Desktop/IMAS/Iris/iris.arff");
            atf.setFile(inputFile);            
            Instances instancesTest = atf.getDataSet();
            instancesTest.setClassIndex(0);
            
          
            double sum = instancesTest.numInstances(),
            right = 0.0f;  
            instancesTrain.setClassIndex(0);         
             m_classifier.buildClassifier(instancesTrain);
            for(int  i = 0;i<sum;i++)
            {  
                if(m_classifier.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue())
                {  
                  right++;
                }  
            }  
            System.out.println("J48 classification precision:"+(right/sum));  
    }  

}
