package ImasGroup.Classification;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import java.io.File;

public class AgentDataClass {
    public static void main(String[] args) {
        Instances ins = null;
        Classifier cfs = null;
        try {
            // read the training set
            File file = new File("/Users/edison/Desktop/IMAS/text.arff");
            System.out.println(file);
            ArffLoader loader = new ArffLoader();   //ArffLoader类是weka.core.converters下的
            loader.setFile(file);
            ins = loader.getDataSet();
            System.out.println(ins.numAttributes());
            ins.setClassIndex(ins.numAttributes() - 1);

            // 初始化分类器
            cfs = (Classifier) Class.forName("weka.classifiers.bayes.NaiveBayes").newInstance();

            // 使用训练集对数据集训练
            cfs.buildClassifier(ins);

            // 使用测试数据集测试分类器的性能
            Instance testInst;

            Evaluation testingEvaluation = new Evaluation(ins);
            int length = ins.numInstances(); //得到数据集样本个数

            for (int i = 0; i < length; i++) {
                testInst = ins.instance(i);
                testingEvaluation.evaluateModelOnceAndRecordPrediction(cfs, testInst);
            }

            // print the classifying results
            System.out.println("分类正确率:" + (1 - testingEvaluation.errorRate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
