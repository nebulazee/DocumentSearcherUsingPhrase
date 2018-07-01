
public class openfile {
public  void open(String ff) {
try {
			if(ff.endsWith(".TXT")||ff.endsWith(".txt"))
			{ProcessBuilder proc =
			new ProcessBuilder("notepad.exe", ff);
			proc.start();
			}
			if(ff.endsWith(".HTML")||ff.endsWith(".html"))
			{
						ProcessBuilder proc =
						new ProcessBuilder("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe", ff);
						proc.start();	
			}
	}
catch (Exception e) {
e.printStackTrace();
}
}
}

