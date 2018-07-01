import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.io.*;
import java.util.*;

public class Html2Text extends HTMLEditorKit.ParserCallback {
    StringBuffer s;
    FileInputStream fin;
    BufferedReader reader;
    String inputLine;
    String finalContents = "";
    BufferedWriter writer;
    FileReader in;
    String text="",fname="",substr="",finalContents1="",finalContents2="",text1="";
    String st_temp="",st_temp1="";
    File fl;
    int idxs=0,idxe=0,len=0;
    String stop_list[]={"a","an","and","are","as","at","be","by","for","from","has","he","in","is","it","its","of","on","that","the","to","was","were","will","with"};
String s_textfile="", sub_textfile="";
int idx_textfile=0;
StringTokenizer st1;

public Html2Text(){}
 public void ex_contents(String st) {
	try {
   // the HTML to convert
      fl=new File(st);
     fin=new FileInputStream(fl);
     reader = new BufferedReader(new InputStreamReader(fin));
    while ((inputLine = reader.readLine()) != null) {
    finalContents += "\n" + inputLine.replace("<br", "\n<br");
    }

  while(finalContents.indexOf("<style>")!=-1)
    {
     idxs=finalContents.indexOf("<style>");
     idxe=finalContents.indexOf("</style>",idxs+1);
     substr= finalContents.substring(idxs,idxe);
     //System.out.println(idxs+"   "+idxe);
     finalContents=finalContents.replace(substr,"");
     }
    finalContents1=finalContents;
    while(finalContents1.indexOf("<a rel")!=-1)
    {
       idxs=finalContents1.indexOf("<a rel");
       idxe=finalContents1.indexOf("</a>",idxs+1);
       substr= finalContents1.substring(idxs,idxe);
      // System.out.println(idxs+"   "+idxe);
       finalContents1=finalContents1.replace(substr,"");
     }
    fname="temp/"+fl.getName();
    writer = new BufferedWriter(new FileWriter(fname));
    writer.write(finalContents1);
    writer.close();

     in = new FileReader(fname);
     Html2Text parser = new Html2Text();
     parser.parse(in);
     in.close();
     //System.out.println(parser.getText());
     text=parser.getText();
     st1=new StringTokenizer(text," ");
     int fg=0;
     while(st1.hasMoreTokens())
     {
           st_temp=st1.nextToken();
               st_temp1="";
           for(int i=0;i<st_temp.length();i++)
           {
                if((st_temp.charAt(i)>='A' && st_temp.charAt(i)<='Z') || (st_temp.charAt(i)>='a' && st_temp.charAt(i)<='z') || st_temp.charAt(i)=='.')
                  {
                        st_temp1+=""+st_temp.charAt(i);
                  }
            }
            fg=0;
            for(int i=0;i<stop_list.length;i++)
            {
                    if(st_temp1.equals(stop_list[i]))
                    {
                    	fg=1;
                         break;
                    }
            }
	if(fg==0)
	{
	    text1+=" "+st_temp1;
	}
     }

    st1=new StringTokenizer(text1," ");
   String s_split="",s_split1="";
   String text2="";
   while(st1.hasMoreTokens())
    {
       s_split=st1.nextToken();
       s_split1="";
       for(int i=0;i<s_split.length();i++)
       {
             if(s_split.charAt(i)>='A' && s_split.charAt(i)<='Z')
             {
                  s_split1+=" "+s_split.charAt(i);
             }
            else
            {
	 s_split1+=""+s_split.charAt(i);
             }
        }
       text2+=" "+s_split1;
     }
    s_textfile=fl.getName();
    idx_textfile= s_textfile.indexOf('.');
    sub_textfile=s_textfile.substring(0,idx_textfile);
    writer = new BufferedWriter(new FileWriter("temp/"+sub_textfile+".txt"));
    writer.write(text2);
    writer.close();
   }
   catch (Exception e) {
     e.printStackTrace();
   }
}

 public void parse(Reader in) throws IOException {
   s = new StringBuffer();
   ParserDelegator delegator = new ParserDelegator();
   // the third parameter is TRUE to ignore charset directive
   delegator.parse(in, this, Boolean.TRUE);
 }

 public void handleText(char[] text, int pos) {
   s.append(text);
 }
 public String getText() {
   return s.toString();
 }
 /*public static void main(String[] sr)
 {
	 Html2Text ob=new Html2Text();
	 ob.ex_contents("D:\\java\\fun.html");
	 System.out.println(ob.getText());
 }*/
}