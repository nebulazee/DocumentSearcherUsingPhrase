import java.io.*;
import java.lang.String;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
class fparsing extends JFrame
{
	int flag=0;
	JTextField t=new JTextField();
	JLabel l=new JLabel("SELECT DRIVE");
	JButton b1=new JButton("SEARCH");

	JScrollPane panel=new JScrollPane();
	//JList<String> li=new JList<String>(new DefaultListModel<String>());
	List li=new List();
	List list2=new List();
	JLabel l2=new JLabel("ENTER THE PHRASE");
	JTextField t2=new JTextField();

	String phrase=null;
	JButton browse=new JButton("BROWSE");

	public fparsing()
	{
		//setSize(250,250);
	 	setBounds(300,100,400,600);
				setLayout(null);
		//t.setBounds(10,10,50,50);
		t.setBounds(150,30,100,30);
		add(t);

		/*list2.setBounds(250,30,100,30);
		add(list2);
		list2.add("D:\\");
		list2.add("E:\\");
		list2.add("F:\\");
		list2.addItemListener(new ItemListener(){
				        public void itemStateChanged(ItemEvent ie)
				        {
							sel_drive();
				        //label.setText("You selected "+list.getSelectedItem());
		        }
			});
		*/
		browse.setBounds(250,30,100,30);
		add(browse);
		browse.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){browse_file(e);}});
		l.setBounds(20,30,150,30);
		add(l);

		l2.setBounds(20,60,150,30);
		add(l2);

		t2.setBounds(150,60,200,30);
		add(t2);

		b1.setBounds(150,100,100,30);
		add(b1);

		b1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){get_file();}});
		li.setBounds(20,140,350,400);
		add(li);
		li.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie)
			{
				open_file();
			}
		  }
		);
		//panel.add(new JScrollPane(li));
		panel.setBounds(20,140,350,400);
		add(panel);
		panel.setViewportView(li);


		setVisible(true);


	}
	
	public void browse_file(ActionEvent e)
	{
		/*FileDialog f=new FileDialog(this,"SELECT FILE OR FOLDER",FileDialog.LOAD);
		f.setVisible(true);
		String d=f.getDirectory();
		t.setText(d);*/

		JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File("."));
		//    chooser.setDialogTitle(choosertitle);
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    //
		    // disable the "All files" option.
		    //
		    chooser.setAcceptAllFileFilterUsed(false);
		    //
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
		     // System.out.println("getCurrentDirectory(): "
		     //    +  chooser.getCurrentDirectory());
		     t.setText(chooser.getSelectedFile().toString());
		      //System.out.println("getSelectedFile() : "
		      //   +  chooser.getSelectedFile());
		      }
		    else {
		      //System.out.println("No Selection ");

      }

}

	
	
	public void open_file()
	{
		openfile obj=new openfile();
		obj.open(li.getSelectedItem().toString());
	}
	public void sel_drive()
	{
		t.setText(list2.getSelectedItem().toString());
	}
    public void get_file()
    {
		//((DefaultListModel)li.getModel()).clear();
    	li.clear();
    	flag=0;
		//t.setText(li.getSelectedItem().toString());

		String a=t.getText();

    	String phrase=t2.getText();
		//System.out.println(a);
		//parsing obj=new parsing();
		try{
		File f=new File(a);
            parse(f,phrase);
            if(flag==1)
            System.out.println("file found");
		}catch(NullPointerException ee){}
		if(flag==0)
		{
			JOptionPane.showMessageDialog(null,"file not found", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null,"These files contain the entered phrase", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			
	}


	public static void main(String ra[])throws IOException
	{
            fparsing ob=new fparsing();


	}
	public void parse(File f,String phrase)
	{

	{
		File files[]=f.listFiles();
                for(int i=0;i<files.length;i++)
                {
                    if(files[i].isDirectory())
                    {
                    parse(files[i],phrase);
                    }
                    else
                    {
						  //if(files[i].canRead()){

						   if(files[i].toString().endsWith(".TXT")||files[i].toString().endsWith(".txt")||files[i].toString().endsWith(".html"))
                      		{


								if(files[i].toString().endsWith(".TXT")||files[i].toString().endsWith(".txt"))
								{

									try{
											stringmatcher ob=new stringmatcher();


											String line=ob.readFile(files[i].toString());
											if(line.indexOf(phrase)!=-1)
											{

												flag=1;
												//((DefaultListModel)li.getModel()).addElement(files[i].toString());
												li.add(files[i].toString());
											}

										}catch(Exception e){e.printStackTrace();}
								}//if block

								if(files[i].toString().endsWith(".HTML")||files[i].toString().endsWith(".html"))
									{
									  	  try{
												FileReader in = new FileReader(files[i].toString());
								    		     Html2Text parser = new Html2Text();
											     parser.parse(in);
											     in.close();

									   			String line1=parser.getText();

								       			if(line1.indexOf(phrase)!=-1)
														{

														flag=1;
														//((DefaultListModel)li.getModel()).addElement(files[i].toString());
														li.add(files[i].toString());
														}




									        }
									         catch (Exception e) {
										      e.printStackTrace();
										               }

									}




							}//main if block

						   //}if can readread
                    }
                }
 		}
	}
}