
import java.io.*;

class stringmatcher
{
   public String readFile(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader (file));
    String         line = null;
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try {
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    } finally {
        reader.close();
    }
}

	/*public static void main(String[] ar)
	{
		try{
		stringmatcher ob=new stringmatcher();

		String line=ob.readFile("D:\\New folder (11)\\wayne.txt");
		System.out.println(line);
		}catch(Exception e){}
	}
	*/

}