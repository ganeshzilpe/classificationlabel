import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import weka.classifiers.functions.SMO;
import weka.core.Instances;
public class Project 
{
	public static void main(String[] args) {
		try
		{
BufferedReader reader = new BufferedReader(new         FileReader("H:/Study/MS_II/Data Mining/Project/NewTesting2/train.arff"));
			Instances train;
			train = new Instances(reader);
			train.setClassIndex(0);//first attribute is class attribute

			reader = null;
reader = new BufferedReader(new FileReader("H:/Study/MS_II/Data Mining/Project/NewTesting2/testWithEmptyClass.arff"));
			Instances test = new Instances(reader);
			test.setClassIndex(0);
			
			reader.close();
			
			SMO smoTree = new SMO(); //classifer
			
			smoTree.buildClassifier(train);
			Instances labeled = new Instances(test);
			
			//label instances
			for(int i=0; i<test.numInstances(); i++)
			{
double classLabel = smoTree.classifyInstance(test.instance(i));
				labeled.instance(i).setClassValue(classLabel);
			}
			
BufferedWriter writer = new BufferedWriter(new FileWriter("H:/Study/MS_II/Data Mining/Project/NewTesting2/testWithEmptyClass.arff"));
			writer.write(labeled.toString());
			System.out.println("Done");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
