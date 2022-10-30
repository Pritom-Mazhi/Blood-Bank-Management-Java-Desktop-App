package projectBloodBank;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class BloodBank implements WindowListener, ActionListener {
	private File file;
	private BufferedWriter save; //to write data in file
	private BufferedReader read; //to read data from file
	private Frame parentFrame, childFrame;
	private JPanel p1, p2, p3, p4, p5, p6, p7, p8, pTitle;
	private JLabel lResultMessege, lResultGroup, lDonorCounter, lEntry, lEntryMessege, lSearch, lSearchMessege;
	private JLabel lFirstName, lLastName, lStreetAddress, lCity, lPhoneNumber, lEmailAddress, lDateOfBirth, lSex, lBloodSelect, lWeight;
	private JLabel lError, lTitle;
	private TextArea tResult;
	private String sex;
	private JTextField tStreetAddress;
	private JTextField tFirstName, tLastName, tPhoneNumber, tEmailAddress, tWeight;
	private JComboBox jBloodGroup, jSearchBloodGroup, jCities, jDay, jMonth, jYear;
	private JRadioButton sMale, sFemale, sOther;
	private ButtonGroup sGroup;
	private String[] bloodGroup = {"Select", "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
	private String[] searchBloodGroup = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
	private String[] citySelect = {"Select", "Bagerhat", "Bandarban", "Barguna", "Barisal", "Bhola", "Bogra", "Brahmanbaria", "Chandpur", "Chittagong", 
		"Chuadanga", "Comilla", "Coxs Bazar", "Dhaka", "Dinajpur", "Faridpur", "Feni", "Gaibandha", "Gazipur", "Gopalganj", "Habiganj", 
		"Jaipurhat", "Jamalpur", "Jessore", "Jhalokathi", "Jhenaidah", "Khagrachhari", "Khulna", "Kishoreganj", "Kurigram", "Kushtia", 
		"Lakshmipur", "Lalmonirhat", "Madaripur", "Magura", "Manikganj", "Meherpur", "Maulvibazar", "Munshiganj", "Mymensingh", "Naogaon", 
		"Narayanganj", "Narsingdi", "Natore", "Nawabganj", "Netrokona", "Nilphamari", "Noakhali", "Narail", "Pabna", "Panchagarh", 
		"Patuakhali", "Pirojpur", "Rajbari", "Rajshahi", "Rangamati", "Rangpur", "Satkhira", "Shariatpur", "Sherpur", "Sirajganj", 
		"Sunamganj", "Sylhet", "Tangail", "Thakurgaon"};
	private String[] daySelect = {"dd", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private String[] monthSelect = {"mmm", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private String[] yearSelect = {"yyyy", "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919",
		"1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939",	
		"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959",
		"1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
		"1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", 
		"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014"};
	private JButton search, submit, back, reset, disclaimer, about, help, feed, x;

	public BloodBank() {
		//Frames
		parentFrame = new Frame("Blood Bank Management");
		childFrame = new Frame("Search Result");

		parentFrame.setUndecorated(true);
		childFrame.setUndecorated(true);
		
		//Panels
		//ParentFrame
		p1 = new JPanel();
		p1.setLayout(null);
		p2 = new JPanel();
		p2.setLayout(null);
		p3 = new JPanel();
		p3.setLayout(null);
		p4 = new JPanel();
		p4.setLayout(null);
		p5 = new JPanel();
		p5.setLayout(null);
		pTitle = new JPanel();
		pTitle.setLayout(null);
			//ChildFrame
		p6 = new JPanel();
		p6.setLayout(null);
		p7 = new JPanel();
		p7.setLayout(null);
		p8 = new JPanel();
		p8.setLayout(null);
		
		//Labels
			//ParentFrame
	   	lSearchMessege = new JLabel("~ Find Blood Donor ~");
	   	lSearch = new JLabel("Select blood group from the dropdown list below: ");
		lEntryMessege = new JLabel("~ You May Donate ~");
		lEntry = new JLabel("Please enter required informations: ");
		lFirstName = new JLabel("First Name:");
		lLastName = new JLabel("Last Name:");
		lStreetAddress = new JLabel("Street: (You may leave it blank if you want) ");
		lCity = new JLabel("City: ");
		lPhoneNumber = new JLabel("Phone Number:");
		lEmailAddress = new JLabel("Email Address: (You may leave it blank if you want)");
		lDateOfBirth= new JLabel("Date of Birth:");
		lWeight = new JLabel("Weight: (kg)");
		lSex = new JLabel("Sex:");
		lBloodSelect = new JLabel("Blood Group:");
		lError = new JLabel("Make sure you've filled required fields before submission.");
		lError.setHorizontalAlignment(SwingConstants.CENTER);
		lTitle = new JLabel("Blood Bank Management");
			//ChildFrame
		lResultMessege = new JLabel("~ Search Results ~");
		lResultGroup = new JLabel();
		lResultGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lDonorCounter = new JLabel();
		lDonorCounter.setHorizontalAlignment(SwingConstants.CENTER);

		//TextBox, TextArea
			//ParentFrame
		tFirstName = new JTextField(50);
		tLastName = new JTextField(50);
		tStreetAddress = new JTextField();
		tStreetAddress = new JTextField();
		tPhoneNumber = new JTextField(50);
		tEmailAddress = new JTextField(50);
		tWeight = new JTextField(4);
			//ChildFrame
		tResult = new TextArea("");
		
		//ComboBox
			//ParentFrame
		jSearchBloodGroup = new JComboBox(searchBloodGroup);
		jCities = new JComboBox(citySelect);
		sMale = new JRadioButton("Male");
		sMale.setActionCommand("male");
		sFemale = new JRadioButton("Female");
		sFemale.setActionCommand("female");
		sOther = new JRadioButton("Other");
		sOther.setActionCommand("other");
		sGroup = new ButtonGroup();
		sGroup.add(sMale);
		sGroup.add(sFemale);
		sGroup.add(sOther); //Radio buttons assigned to a radio group
		jBloodGroup = new JComboBox(bloodGroup);
		jDay = new JComboBox(daySelect);
		jMonth = new JComboBox(monthSelect);
		jYear = new JComboBox(yearSelect);

		//Button
			//ParentFrame
		search = new JButton("Search Donor");
		search.setActionCommand("search");
		submit = new JButton("Submit");
		submit.setActionCommand("submit");
		reset = new JButton("Reset");
		reset.setActionCommand("reset");
		disclaimer = new JButton("Disclaimer");
		disclaimer.setActionCommand("disclaimer");
		about = new JButton("About");
		about.setActionCommand("about");
		feed = new JButton("Feedback");
		feed.setActionCommand("feed");
		help = new JButton("Help");
		help.setActionCommand("help");
		x = new JButton("X");
		x.setActionCommand("x");
		
			//ChildFrame
		back = new JButton("Back");
		back.setActionCommand("back");
		
		//File
			//Write
		file = new File("C:\\", "BloodBankData.txt");
		try {
			save = new BufferedWriter(new FileWriter(file, true)); //To save data in file
		}
		catch(IOException Fucker_1) {
			Fucker_1.printStackTrace();
		}
			//Read
		try {
			read = new BufferedReader(new FileReader(file)); //To read data from file
		}
		catch(IOException Fucker_2) {
			System.out.println("Something's Shitting.");
		}

	 	//Colors and Fonts
	 	lTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
	 	lTitle.setForeground(Color.white);
	 	x.setFont(new Font("Tahoma", Font.PLAIN, 14));

	 	sMale.setBackground(new Color(245, 245, 245));
		sFemale.setBackground(new Color(245, 245, 245));
		sOther.setBackground(new Color(245, 245, 245));
		jBloodGroup.setBackground(Color.white);
		jSearchBloodGroup.setBackground(Color.white);
		jCities.setBackground(Color.white);
		jDay.setBackground(Color.white);
		jMonth.setBackground(Color.white);
		jYear.setBackground(Color.white);
		
		tFirstName.setBackground(Color.white);
		tLastName.setBackground(Color.white);
		tStreetAddress.setBackground(Color.white);
		tStreetAddress.setBackground(Color.white);
		tPhoneNumber.setBackground(Color.white);
		tEmailAddress.setBackground(Color.white);
	
		x.setBackground(Color.lightGray);
		search.setBackground(Color.white);
		submit.setBackground(Color.white);
		reset.setBackground(Color.white);
		back.setBackground(Color.white);
		about.setBackground(new Color(229, 229, 229));
		disclaimer.setBackground(new Color(229, 229, 229));
		help.setBackground(new Color(229, 229, 229));
		feed.setBackground(new Color(229, 229, 229));
		
		parentFrame.setBackground(new Color(245, 245, 245));
	   	childFrame.setBackground(new Color(229, 229, 229));	   	
	   	
	   	pTitle.setBackground(Color.gray);
	   	p1.setBackground(new Color(229, 229, 229));
	   	p3.setBackground(new Color(245, 245, 245));
		p5.setBackground(new Color(245, 245, 245));
		p8.setBackground(new Color(245, 245, 245));
	}
	
	//OnClick ChildFrame
	public void launchChild() {
	 	childFrame.addWindowListener(this);
	 	back.addActionListener(this);
	  	
	 	p7.add(disclaimer);
	 	p7.add(about);
	 	p7.add(help);
	 	p7.add(feed);
		p6.add(lResultMessege);
 		p8.add(lResultGroup);
		p8.add(lDonorCounter);
		p8.add(tResult);
		p8.add(back);
		childFrame.add(pTitle);
		childFrame.add(p7);
		childFrame.add(p6);
		childFrame.add(p8);
		
		pTitle.setBounds(0, 0, 384, 27); // (x, y, width, height)		
		p7.setBounds(0, 27, 384, 23);
		p6.setBounds(0, 50, 384, 29);
		p8.setBounds(0, 79, 384, 800);

	   	feed.setBounds(-1, -3, 98, 28);
	   	about.setBounds(96, -3, 98, 28);
	   	help.setBounds(192, -3, 98, 28);
	   	disclaimer.setBounds(288, -3, 97, 28);

		lResultMessege.setBounds(136, 2, 364, 25);
		lResultGroup.setBounds(10, 80, 364, 25);
		lDonorCounter.setBounds(10, 99, 364, 25);
		tResult.setBounds(10, 126, 364, 466);
 		back.setBounds(10, 603, 364, 25);
 	
	    childFrame.setSize(384, 640);
	    childFrame.setVisible(true);
	}
		
	//MainFrame
	public void launchParent() {
	  	jBloodGroup.addActionListener(this);
	  	jSearchBloodGroup.addActionListener(this);
	  	search.addActionListener(this);
	  	reset.addActionListener(this);
	  	submit.addActionListener(this);
	  	sMale.addActionListener(this);
	  	sFemale.addActionListener(this);
	  	sOther.addActionListener(this);
	  	disclaimer.addActionListener(this);
	  	about.addActionListener(this);
	  	feed.addActionListener(this);
	  	help.addActionListener(this);
	  	x.addActionListener(this);
	  	
	    parentFrame.addWindowListener(this);
	   	parentFrame.setLayout(null);
	   	
	   	pTitle.add(lTitle);
	   	pTitle.add(x);
	   	parentFrame.add(pTitle);
	   	p1.add(disclaimer);
	   	p1.add(about);
	   	p1.add(help);
	   	p1.add(feed);
	   	parentFrame.add(p1);

	   	p2.add(lSearchMessege);
	   	parentFrame.add(p2);
	   	p3.add(lSearch);
	   	p3.add(jSearchBloodGroup);
	   	p3.add(search);
	   	parentFrame.add(p3);
	   	p4.add(lEntryMessege);
	   	parentFrame.add(p4);
	    p5.add(lEntry);
	   	p5.add(lFirstName);
	   	p5.add(lLastName);
	   	p5.add(tFirstName);
	   	p5.add(tLastName);
	   	p5.add(lStreetAddress);
	   	p5.add(tStreetAddress);
	   	p5.add(lCity);
	   	p5.add(jCities);
	   	p5.add(lPhoneNumber);
	   	p5.add(tPhoneNumber);
	    p5.add(lEmailAddress);
	   	p5.add(tEmailAddress);
	   	p5.add(lDateOfBirth);
	   	p5.add(jDay);
	   	p5.add(jMonth);
	   	p5.add(jYear);
	   	p5.add(lWeight);
	   	p5.add(tWeight);
	   	p5.add(lSex);
	   	p5.add(sMale);
	   	p5.add(sFemale);
	   	p5.add(sOther);
	   	p5.add(jBloodGroup);
	   	p5.add(lBloodSelect);
	   	p5.add(lError);
	   	p5.add(reset);
	   	p5.add(submit);
	   	parentFrame.add(p5);
	   	parentFrame.setSize(384, 640);
	   	parentFrame.setVisible(true);
	
	   	pTitle.setBounds(0, 0, 384, 27); // (x, y, width, height)
	   	p1.setBounds(0, 27, 384, 23);
	   	p2.setBounds(0, 50, 384, 29);
	   	p3.setBounds(0, 83, 384, 66);
	   	p4.setBounds(0, 149, 384, 29);
	   	p5.setBounds(0, 178, 384, 700);

	   	x.setBounds(341, 0, 43, 27);
	   	x.setBorderPainted(false);
	   	
	   	feed.setBounds(-1, -3, 98, 28);
	   	feed.setBorderPainted(false);
	   	about.setBounds(96, -3, 98, 28);
	   	about.setBorderPainted(false);
	   	help.setBounds(192, -3, 98, 28);
	   	help.setBorderPainted(false);
	   	disclaimer.setBounds(288, -3, 97, 28);
	   	disclaimer.setBorderPainted(false);	   	
	   	lTitle.setBounds(10, 1, 384, 25);
	   	
	   	lSearchMessege.setBounds(134, 2, 384, 25);
	   	lSearch.setBounds(8, 2, 384, 25);
	   	jSearchBloodGroup.setBounds(10, 27, 100, 25);
	   	search.setBounds(120, 27, 252, 25);
	   	lEntryMessege.setBounds(138, 2, 362, 25);

	   	lEntry.setBounds(10, 2, 362, 23);
	    lFirstName.setBounds(10, 25, 101, 25);
	   	lLastName.setBounds(195, 25, 101, 25);
	   	tFirstName.setBounds(10, 50, 177, 25);
	   	tLastName.setBounds(196, 50, 177, 25);
	    lStreetAddress.setBounds(10, 80, 363, 25);
	   	tStreetAddress.setBounds(10, 105, 363, 25);
	   	lCity.setBounds(10, 135, 40, 25);
	   	jCities.setBounds(51, 135, 320, 25);
	    lPhoneNumber.setBounds(10, 165, 120, 25);
	   	tPhoneNumber.setBounds(10, 190, 363, 25);
	   	lEmailAddress.setBounds(10, 220, 363, 25);
	   	tEmailAddress.setBounds(10, 245, 363, 25);
	    lDateOfBirth.setBounds(10, 275, 202, 25);
	   	jDay.setBounds(110, 275, 70, 25);
	  	jMonth.setBounds(190, 275, 80, 25);
	   	jYear.setBounds(280, 275, 92, 25);
	   	lWeight.setBounds(10, 305, 100, 25);
	   	tWeight.setBounds(110, 305, 263, 25);
	   	lSex.setBounds(10, 335, 70, 25);
	   	sMale.setBounds(107, 335, 70, 25);
	   	sMale.setSelected(false);
	   	sFemale.setBounds(197, 335, 70, 25);
	   	sFemale.setSelected(false);
	   	sOther.setBounds(287, 335, 70, 25);
	   	sOther.setSelected(false);
	   	lBloodSelect.setBounds(10, 365, 100, 25);
	   	jBloodGroup.setBounds(110, 365, 262, 25);
	   	lError.setBounds(10, 395, 360, 25);
	   	reset.setBounds(10, 425, 101, 25);
	   	submit.setBounds(120, 425, 252, 25);
	}
	
	//Search in the File
	public void fileSearch() {
		try {
			int donorCount = 0;
			String stringFromFile = read.readLine(); //Will read lines from file
			String stringFromCombo = (String) jSearchBloodGroup.getItemAt(jSearchBloodGroup.getSelectedIndex());
			while(stringFromFile != null) {
				if(stringFromCombo.equals(stringFromFile)) {
					donorCount++;
					tResult.append("- - - - - - - - - - - - - - - - - - - - - Donor " +donorCount + " - - - - - - - - - - - - - - - - - - - - -");
			   		tResult.append(System.getProperty("line.separator")); //A new line
					tResult.append("Name: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("Street: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("City: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("Phone Number: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("Email: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("Date of Birth: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("Weight: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
			   		tResult.append("Sex: " +read.readLine());
			   		tResult.append(System.getProperty("line.separator")); 
					tResult.append("Blood Group: " +stringFromCombo);
			   		tResult.append(System.getProperty("line.separator"));
					stringFromFile = read.readLine();
					}
				stringFromFile = read.readLine();
				if(donorCount >= 1) lDonorCounter.setText(+donorCount +" donor(s) found!");
				else lDonorCounter.setText("Sorry, no donor found :(");
			}
		}
		catch(FileNotFoundException Fucker_3) {
			System.out.println("Data File Not Found.");
		}
		catch(IOException Fucker_4) {
			System.out.println("Something Shitting.");
		}
	}
	
	//Submission
	public void launchSubmission() {
		try {
	   		save.append("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
	   		save.append(System.getProperty("line.separator"));
	   		save.append((String) jBloodGroup.getItemAt(jBloodGroup.getSelectedIndex()));
	   		save.append(System.getProperty("line.separator"));
	   		save.append(tFirstName.getText() +" " +tLastName.getText());
	   		save.append(System.getProperty("line.separator")); 
	   		save.append(tStreetAddress.getText());
	   		save.append(System.getProperty("line.separator")); 
	   		save.append((String) jCities.getItemAt(jCities.getSelectedIndex()));
	   		save.append(System.getProperty("line.separator"));
	   		save.append(tPhoneNumber.getText());
	   		save.append(System.getProperty("line.separator"));
	   		save.append(tEmailAddress.getText());
	   		save.append(System.getProperty("line.separator"));
	   		save.append(jDay.getSelectedItem() +"-" +jMonth.getSelectedItem() +"-" +jYear.getSelectedItem());
	   		save.append(System.getProperty("line.separator"));
	   		save.append(tWeight.getText()+" KG");
	   		save.append(System.getProperty("line.separator"));
	   		save.append(sex);
	   		save.append(System.getProperty("line.separator"));
	   		save.close();
	   		JOptionPane.showMessageDialog(null , "Information Submission Successful!");
		}
		catch(IOException Fucker_5) {
			Fucker_5.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	   	BloodBank bb = new BloodBank();
	   	bb.launchParent();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "x") {                                  
		    	System.exit(0);
		}
		
		if(e.getActionCommand() == "disclaimer") {                                  
			JOptionPane.showMessageDialog(null , "This is an #OpenSource program.", "Disclaimer", JOptionPane.PLAIN_MESSAGE);
		}
	   	
	   	if(e.getActionCommand() == "about") {
	   		JOptionPane.showMessageDialog(null , "This is an OOP Lab Project.", "About", JOptionPane.PLAIN_MESSAGE);
	   	}
	   	
	   	if(e.getActionCommand() == "help") {
	   		JOptionPane.showMessageDialog(null , "Entry some data with proper information.\nThen select any blood group and click search.", "How To Use", JOptionPane.PLAIN_MESSAGE);
	   	}

	   	if(e.getActionCommand() == "feed") {
	   		try {
	   			Desktop.getDesktop().browse(new URL("https://test.com/test.html").toURI());
	   		}
	   		catch(Exception Fucker_6) {
	   		}
	   	}
	   	
	   	if(e.getActionCommand() == "male") {
	   		sex = "Male";	   	
  		}

	   	if(e.getActionCommand() == "female") {
	   		sex = "Female";	   	
  		}
	   	
	   	if(e.getActionCommand() == "other") {
	   		sex = "Other";	   	
  		}
   			
	   	if(e.getActionCommand() == "search") {
		   	lResultGroup.setText("You're searching: Blood Group " +(String) jSearchBloodGroup.getItemAt(jSearchBloodGroup.getSelectedIndex()));
		   	fileSearch();
		   	launchChild();
		   	parentFrame.dispose();
	   	}
	   	
	    if(e.getActionCommand().equals("submit")) {
	   		if(tFirstName.getText().isEmpty() || tLastName.getText().isEmpty() || tPhoneNumber.getText().isEmpty() || 
	   				jDay.getSelectedItem().equals("dd") || jMonth.getSelectedItem().equals("mmm") ||
	   				jYear.getSelectedItem().equals("yyyy") || tWeight.getText().isEmpty() || sGroup.isSelected(null) ||
	   				jBloodGroup.getSelectedItem().equals("Select") ||
	   				jCities.getSelectedItem().equals("Select")) {
	   			lError.setForeground(Color.red);
	   			lError.setText("You must correctly fill in all of the required fields.");
		   		if(tFirstName.getText().isEmpty()) lFirstName.setForeground(Color.red);
		   		else lFirstName.setForeground(null);
		   		if(tLastName.getText().isEmpty()) lLastName.setForeground(Color.red);
		   		else lLastName.setForeground(null);
		   		if(tPhoneNumber.getText().isEmpty()) lPhoneNumber.setForeground(Color.red);
		   		else lPhoneNumber.setForeground(null);
		   		if(jDay.getSelectedItem().equals("dd") || jMonth.getSelectedItem().equals("mmm") || jYear.getSelectedItem().equals("yyyy")) lDateOfBirth.setForeground(Color.red);
		   		else lDateOfBirth.setForeground(null);
		   		if(jCities.getSelectedItem().equals("Select")) lCity.setForeground(Color.red);
		   		else lCity.setForeground(null);
		   		if(tWeight.getText().isEmpty()) lWeight.setForeground(Color.red);
		   		else lWeight.setForeground(null);
		   		if(sGroup.isSelected(null)) lSex.setForeground(Color.red);
		   		else lSex.setForeground(null);
		   		if(jBloodGroup.getSelectedItem().equals("Select")) lBloodSelect.setForeground(Color.red);
		   		else lBloodSelect.setForeground(null);
	   		}
	   		else {
	    		lError.setText("");
	    		launchSubmission();
	    		parentFrame.dispose();
	    		BloodBank bb = new BloodBank();
	    		bb.launchParent();
	    	}
	    }
	    
	    if(e.getActionCommand().equals("back")) {
	    	BloodBank bb = new BloodBank();
	    	bb.launchParent();
		   	childFrame.dispose();		   	
	   	}
	    
	    if(e.getActionCommand().equals("reset")) {
	    	tFirstName.setText(null);
	    	tLastName.setText(null);
	    	tStreetAddress.setText(null);
	    	jCities.setSelectedIndex(0);
	    	tPhoneNumber.setText(null);
	    	tEmailAddress.setText(null);
	    	jDay.setSelectedIndex(0);
	    	jMonth.setSelectedIndex(0);
	    	jYear.setSelectedIndex(0);
	    	tWeight.setText(null);
	    	sGroup.clearSelection();
	    	jBloodGroup.setSelectedIndex(0);

	    	lFirstName.setForeground(null);
	    	lLastName.setForeground(null);
	    	lCity.setForeground(null);
	    	lPhoneNumber.setForeground(null);
	    	lDateOfBirth.setForeground(null);
	    	lWeight.setForeground(null);
	    	lSex.setForeground(null);
	    	lBloodSelect.setForeground(null);
	    	lError.setText("Make sure you've filled required fields before submission.");
	    	lError.setForeground(null);
	    }
	}
	
	public void windowClosing(WindowEvent e) {    	
		System.exit(0);
	}
	public void windowOpened(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowActivated(WindowEvent e) {
	}
	public void windowDeactivated(WindowEvent e) {
	}
}
