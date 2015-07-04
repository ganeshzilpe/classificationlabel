import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class DataMining 
{
	List<String> file1;
	List<String> file2;
	List<String> file3;
	private List<String> readFile(String filename)
	{
		List<String> records = new ArrayList<String>();
		try
		{
BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null)
			{
				records.add(line);
			}
			reader.close();
			return records;
		}
		catch (Exception e)
		{
System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}
	}
	public void operation()
	{
		String sample = "@attribute 'Feature ";
		for(int i=0; i<file1.size(); i++)
		{
			String[] fileinput1 = file1.get(i).split("\\{");
			String[] fileinput2 = file2.get(i).split("\\{");

			fileinput1 = fileinput1[1].split("}");
			fileinput2 = fileinput2[1].split("}");

			fileinput1 = fileinput1[0].split(",");
			fileinput2 = fileinput2[0].split(",");

			String result ="";
			Vector vect = new Vector();
			result = result + fileinput1[0];
			vect.add(fileinput1[0]);
			for(int l=1; l< fileinput1.length; l++)
			{
				vect.add(fileinput1[l]);
				result=result+","+fileinput1[l];
			}


			for(int k=0; k<fileinput2.length; k++)
			{
				if(!(vect.contains((String)fileinput2[k])))
				{
					vect.add((String)fileinput2[k]);
					result = result+","+fileinput2[k];
				}
			}

			String []resultArray = null;
			if(result.contains(","))
			{
				resultArray = result.split(",");
				Arrays.sort(resultArray);
				result ="";
				if(resultArray[0]!="")
					result = resultArray[0];
				else
					result = resultArray[1];
				for(int m=1; m<resultArray.length; m++)
					result = result + "," + resultArray[m];
System.out.println(sample+(file3.size()+1)+"'"+" {"+result+"}");
				file3.add(sample+(i+1)+"'"+" {"+result+"}");
			}
			else
			{
				file3.add(sample+(i+1)+"'"+" {"+result+"}");
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter("H:\\Study\\MS_II\\Data Mining\\Project\\3rd Submission\\Testing\\file3.txt");
			for (int i = 0; i < file3.size(); i++) {
				fw.write(file3.get(i)+"\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		DataMining dm = new DataMining();
		dm.file1 = dm.readFile("H:\\Study\\MS_II\\Data Mining\\Project\\3rd Submission\\Testing\\file1.txt");
		dm.file2 = dm.readFile("H:\\Study\\MS_II\\Data Mining\\Project\\3rd Submission\\Testing\\file2.txt");
		dm.file3 = new ArrayList<String>();
		dm.operation();
	}
}
