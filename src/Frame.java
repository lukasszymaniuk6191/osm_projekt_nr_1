import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame implements FocusListener 
{

	private static final int width = 1200;
	private static final int height = 750;
	
	/*
	 * przycisk Zamknij ALT+F4
	 */
	private static JButton closeAltF4 = null;	
	
	private JMenuBar menuBar = null;
	private JMenu aplicationMenu = null;
	private JMenuItem menuCloseAplication = null;	

	/*
	 * imie
	 */
	private JLabel patientName = null;
	private JTextField patientNameTextField = null;
	private JLabel stringErrorLabelNameTextField = null;
	
	/*
	 * nazwisko
	 */
	private JLabel patientLastName = null;
	private JTextField patientLastNameTextField = null;
	private JLabel stringErrorLabelLastNameTextField = null;
	
	/*
	 * pesel
	 */
	private JLabel patientPesel = null;
	private JTextField patientPeselTextField = null;
	private JLabel numberErrorLabelPeselTextField = null;
	
	/*
	 * p�e�
	 */
	private JLabel patientSex = null;
	private JRadioButton patientManRadioButton = new JRadioButton("M�czyzna");
	private JRadioButton patientWomanRadioButton = new JRadioButton("Kobieta");
	private ButtonGroup group = new ButtonGroup();
	
	/*
	 * ubezpieczenie
	 */
	private JLabel insurance= null;
    private JComboBox patientInsuranceList = null;
	
    /*
     * panel g��wny
     */
	private JPanel dataPanel;
	
	/*
	 * przyciski zapisz i anuluj dla danych pacjenta
	 */
	private JButton patientSaveButton = null;
	private JButton patientDeleteButton = null;
	
	/*
	 * zmienna pomocnicza dla pola wyboru
	 */
	private String manOrWoman = null;
		
	/*
	 * obecna data
	 */
	private JLabel currentDate = null;
	private JTextField currentDateTextField = null;
	private JButton addCurrentDateButton = null;
	 
	/*
	 * St�enie Ck
	 */
	private JLabel valueCK = null;
	private JTextField valueCKTextField = null;
	private JLabel numberErrorLabelValueCKTextField = null;
	 
	/*
	 * St�enie K
	 */
	private JLabel valueK = null;
	private JTextField valueKTextField = null;
	private JLabel numberErrorLabelValueKTextField = null;
	 
	/*
	 * St�enie Tn
	 */
	private JLabel valueTn = null;
	private JTextField valueTnTextField = null;
	private JLabel numberErrorLabelValueTnTextField = null;
	 
	/*
	 * przyciski zapisz i anuluj dla testu
	 */
	private JButton testSaveButton = null;
	private JButton testDeleteButton = null;
	 
	/*
	 * nazwy kolumn
	 */
	private String[] columnNames = {"Imie i nazwisko", "P�e�", "PESEL", "Ubezpieczenie","Badanie"};
	
	/*
	 * warto�ci umieszczone w tabeli
	 */
	private Object[][] cells = {{"ala","ma","�adnego","kota"},{"�ukasz Szymaniuk","M�czyzna","94033008892","Prywatne","Tak"},{"c","c","c","c","c"}};
	 		
	private JButton patientListAddButton = null;
	private JButton patientListDeleteButton = null;	 
	private ControlData controlData = new ControlData();
	private int patientId = 0;
	private boolean updatePersonData = false;	 
	
	public Frame() 
	{
		setSize(width,height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(0,0);
		setTitle("Moja aplikacja");	
		
		 dataPanel = new JPanel() 
		 {
			 @Override
			 public void paintComponent(Graphics g) {
				 super.paintComponent(g);	          
				 g.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));	    		  
				 g.drawRect (50, 50, 450, 300);  
				 g.drawString("Dane pacjenta", 70, 45);	    		  
				 g.drawRect (50, 400, 450, 250); 
				 g.drawString("Badanie", 70, 395);	    		  
				 g.drawRect (550, 50, 600, 600); 
				 g.drawString("Lista pacjent�w", 570, 45);	                		              
            }
		 };			
		 dataPanel.setLayout(null);			 
		 menuBar = new JMenuBar();
		 aplicationMenu = new JMenu("Aplikacja");
		 
		 Action menuCloseAplication = new AbstractAction("Zamknij Alt+F4")
		 {
	 		public void actionPerformed(ActionEvent event)
	 		{
	 			if (JOptionPane.showConfirmDialog( 
	                    null, "Czy chcesz op�ci� program?", "Czy zamkn��?", 
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			    		{
		                    System.exit(0);
		                }
		 		}
			 };			 
			 aplicationMenu.add(menuCloseAplication);
			 menuBar.add(aplicationMenu);
			 this.setJMenuBar(menuBar);
 
			 MyTableModel model = new MyTableModel();
			 Object[] row = new Object[5];

			 JTable table = new JTable(model);
			 JScrollPane scrollPane = new JScrollPane(table);
			 scrollPane.setLocation(600, 70);
			 scrollPane.setSize(500, 400);
			 dataPanel.add(scrollPane, BorderLayout.CENTER);		
			 
			 ///////////////////////////////////////////////////////////////////////////////////////
		 ///////////////////////////////////////////////////////////////////////////////////////
		 ///////////////////////////////////////////////////////////////////////////////////////
		 
		 /*
		  * imie pacjenta etykieta
		  */
		 patientName = new JLabel("Imie: ");
		 patientName.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientName.setLocation(90, 70);
		 patientName.setSize(220,20);			 
		 dataPanel.add(patientName);
		 
		 /*
		  * imie pacjenta pole tekstowe
		  */
		 patientNameTextField = new JTextField(20);
		 patientNameTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientNameTextField.setLocation(200,70);
		 patientNameTextField.setSize(220, 20);
		 patientNameTextField.setEditable(false);
		 patientNameTextField.addFocusListener(this);
		 
		 stringErrorLabelNameTextField=new JLabel("* Wstaw tylko litery!");
		 stringErrorLabelNameTextField.setBounds(200, 92, 220, 12);
		 stringErrorLabelNameTextField.setFont(new Font("Courier New", Font.BOLD, 10));
		 stringErrorLabelNameTextField.setVisible(false);
		 
		 dataPanel.add(stringErrorLabelNameTextField);		
		 dataPanel.add(patientNameTextField);
		
		 /*
		  * nazwisko pacjenta etykieta
		  */
		 patientLastName = new JLabel("Nazwisko: ");
		 patientLastName.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientLastName.setLocation(90, 110);
		 patientLastName.setSize(220,20);			 
		 dataPanel.add(patientLastName);
		 
		 /*
		  * nazwisko pacjenta pole tekstowe
		  */
		 patientLastNameTextField = new JTextField(20);
		 patientLastNameTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientLastNameTextField.setLocation(200,110);
		 patientLastNameTextField.setSize(220, 20);
		 patientLastNameTextField.setEditable(false);
		 patientLastNameTextField.addFocusListener(this);			 
		 
		 stringErrorLabelLastNameTextField=new JLabel("* Wstaw tylko litery!");
		 stringErrorLabelLastNameTextField.setBounds(200, 132, 220, 12);
		 stringErrorLabelLastNameTextField.setFont(new Font("Courier New", Font.BOLD, 10));
		 stringErrorLabelLastNameTextField.setVisible(false);
		 
		 dataPanel.add(stringErrorLabelLastNameTextField);			 
		 dataPanel.add(patientLastNameTextField);		 
		 
		 /*
		  * pesel etykieta
		  */
		 patientPesel = new JLabel("PESEL: ");
		 patientPesel.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientPesel.setLocation(90, 150);
		 patientPesel.setSize(220,20);			 
		 dataPanel.add(patientPesel);
		 
		 /*
		  * pesel pole tekstowe
		  */
		 patientPeselTextField = new JTextField(20);
		 patientPeselTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientPeselTextField.setLocation(200,150);
		 patientPeselTextField.setSize(220, 20);
		 patientPeselTextField.setEditable(false);
		 patientPeselTextField.addFocusListener(this);			 
		 
		 numberErrorLabelPeselTextField=new JLabel("* Wstaw tylko liczby!");
		 numberErrorLabelPeselTextField.setBounds(200, 172, 220, 12);
		 numberErrorLabelPeselTextField.setFont(new Font("Courier New", Font.BOLD, 10));
		 numberErrorLabelPeselTextField.setVisible(false);
		 
		 dataPanel.add(numberErrorLabelPeselTextField);			 
		 dataPanel.add(patientPeselTextField);		 			 
		 
		 /*
		  * plec etykieta
		  */
		 patientSex = new JLabel("P�E�: ");
		 patientSex.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 patientSex.setLocation(90, 200);
		 patientSex.setSize(100,20);			 
		 dataPanel.add(patientSex);
		 
		 /*
		  * p�e� pole wyboru
		  */
		 patientManRadioButton.setMnemonic(KeyEvent.VK_C);
		 patientWomanRadioButton.setMnemonic(KeyEvent.VK_M);
		 
		 patientManRadioButton.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {         
	        	 manOrWoman = "M";
	         }           
	      });
		 
		 patientWomanRadioButton.addItemListener(new ItemListener() {
	         public void itemStateChanged(ItemEvent e) {         
	        	 manOrWoman = "K";
	         }           
	      });
		 
		 
		 group.add(patientManRadioButton);
		 patientManRadioButton.setLocation(200, 200);
		 patientManRadioButton.setSize(120, 20);
		 patientManRadioButton.setEnabled(false);			 
		 dataPanel.add(patientManRadioButton);
		 
		 group.add(patientWomanRadioButton);
		 patientWomanRadioButton.setLocation(350, 200);
		 patientWomanRadioButton.setSize(100, 20);
		 patientWomanRadioButton.setEnabled(false);			 
		 dataPanel.add(patientWomanRadioButton);			 
		 
		 /*
		  * ubezpieczenie etykieta
		  */			 
		 insurance = new JLabel("Ubezpieczenie: ");
		 insurance.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		 insurance.setLocation(90, 240);
		 insurance.setSize(100,20);			 
		 dataPanel.add(insurance);
		 
		 /*
		  * ubezpieczenie lista rozwijana
		  */
		 String typeOfInsurance[] ={"NFZ", "Prywatne", "Brak"}; 
		 patientInsuranceList =new JComboBox(typeOfInsurance);  
		 patientInsuranceList.setLocation(200, 240);
		 patientInsuranceList.setSize(220, 20);
		 patientInsuranceList.setEnabled(false);			
		 dataPanel.add(patientInsuranceList);		 			 
		 
		 /*
		  * przycisk zapisz
		  */
		 patientSaveButton=new JButton("Zapisz");
		 patientSaveButton.setLocation(90, 300);
		 patientSaveButton.setSize(170, 35);
		 patientSaveButton.setEnabled(false);			 
		 dataPanel.add(patientSaveButton);
		 
		 /*
		  * przycisk usu�
		  */
		 patientDeleteButton=new JButton("Usu�");
		 patientDeleteButton.setLocation(285, 300);
		 patientDeleteButton.setSize(170, 35);
		 patientDeleteButton.setEnabled(false);			 
		 dataPanel.add(patientDeleteButton);
		 
		 patientDeleteButton.addActionListener(new ActionListener() 
		 {  
			public void actionPerformed(ActionEvent e) 
			{
				patientNameTextField.setText("");
				patientLastNameTextField.setText("");
				patientPeselTextField.setText("");
				patientManRadioButton.setSelected(false);
				patientWomanRadioButton.setSelected(false);
				patientInsuranceList.setSelectedItem(typeOfInsurance[1]);					 			
			}
		 });
		 
		 /*
		  * akcja przycisku zapisz
		  */
		 patientSaveButton.addActionListener(new ActionListener() 
		 {  
	        public void actionPerformed(ActionEvent e) 
	        {   			        
	        	String stringPatientName = patientNameTextField.getText();
	        	String stringPatientLastname = patientLastNameTextField.getText();			        	
	        	int integerPatientPesel = 0;			        	
	        	try {
	        		integerPatientPesel    = Integer.parseInt(patientPeselTextField.getText());
	        		patientId = integerPatientPesel;		        		  
	        	} catch (NumberFormatException e1) {
	        		System.out.println("not a number"); 
	        	} 			        	
	        	String stringPatientSex = manOrWoman;
	        	String stringPatientInsurence = (String) patientInsuranceList.getItemAt(patientInsuranceList.getSelectedIndex());
	        
	        	boolean addDataToTable = true;			        	
	        	for(int i=0; i<controlData.getPatientIdList().size(); i++)
	        	{
	        		if(patientId == controlData.getPatientIdList().get(i))
	        		{
	        			addDataToTable = false;
	        		}
	        	}	
	        	
	        	if(addDataToTable==true && updatePersonData == false)
	        	{
	        		controlData.setPatientData(stringPatientName, stringPatientLastname, integerPatientPesel, stringPatientSex, stringPatientInsurence, patientId);
	        	}
	        	else if (addDataToTable == false && updatePersonData == true)
	        	{
	        		controlData.updatePatientData(stringPatientName, stringPatientLastname, integerPatientPesel, stringPatientSex, stringPatientInsurence, patientId);
	        	}
	        	else if(addDataToTable == false && updatePersonData == false)
	        	{
	        		System.out.println("Nie mo�na doda� dw�ch u�ytkownik�w o identycznyych numerach pesel");
	        		 JOptionPane.showMessageDialog(null, "Nie mo�na doda� dw�ch "
	        		 		+ "u�ytkownik�w o identycznyych numerach pesel. Podaj inny numer pesel.");
	        	}
	        	
				if (model.getRowCount() > 0) 
				{
					for (int i = model.getRowCount() - 1; i > -1; i--) {
						model.removeRow(i);						       
				    }
				}
	        	
	        	for(int i=0; i < controlData.getPatientIdList().size(); i++)
	        	{
	        		int patientIdAddToList = controlData.getPatientIdList().get(i);
	        		PatientData patientData=controlData.getPatientDataMap().get(patientIdAddToList);
	        		
	        		row[0] = patientData.getPatientName() + patientData.getPatientLastName();
	        		row[1] = patientData.getPatientSex();
	        		row[2] = patientData.getPatientPesel();
	        		row[3] = patientData.getPatientInsurance();		        		
	        		
	        		Test patientTest = new Test();
	        		patientTest = controlData.getTestMap().get(patientIdAddToList);
	        		
	        		if(patientTest == null)
	        			row[4] = false;
	        		if(patientTest != null)
	        			row[4] = true;	        		
	        		
	        		model.addRow(row);	        			
	        	}		        
        		patientNameTextField.setText("");
        		patientLastNameTextField.setText("");
        		patientPeselTextField.setText("");
        		patientManRadioButton.setSelected(false);
        		patientWomanRadioButton.setSelected(false);
        		patientInsuranceList.setSelectedItem(typeOfInsurance[1]);
        	 
        	 	patientNameTextField.setEditable(false);
	        	patientLastNameTextField.setEditable(false);
	        	patientPeselTextField.setEditable(false);
	        	patientManRadioButton.setEnabled(false);
	        	patientWomanRadioButton.setEnabled(false);
	        	patientInsuranceList.setEnabled(false);;
	        	patientSaveButton.setEnabled(false);
	        	patientDeleteButton.setEnabled(false);
	        }
		});         
		 
		
		table.addMouseListener(new MouseAdapter()
		{			       
			public void mouseClicked(MouseEvent e)
			{			           
				int i = table.getSelectedRow();
				
		        updatePersonData = true;
		        System.out.println("updatePersonData: "+updatePersonData);
		            
		        patientId = (int) model.getValueAt(i, 2);
		        		  
		        PatientData patientDataMouseListener = new PatientData();
		        patientDataMouseListener = controlData.getPatientDataMap().get(patientId);
		            
		        patientNameTextField.setText(patientDataMouseListener.getPatientName());
		        patientLastNameTextField.setText(patientDataMouseListener.getPatientLastName());
		        patientPeselTextField.setText(String.valueOf(patientDataMouseListener.getPatientPesel()));
		        	
		        if(patientDataMouseListener.getPatientSex()=="M")
		        {
		        	patientManRadioButton.setSelected(true);
		        	patientWomanRadioButton.setSelected(false);
		        }
		        if(patientDataMouseListener.getPatientSex()=="K")
		        {
		        	patientManRadioButton.setSelected(false);
		        	patientWomanRadioButton.setSelected(true);
		        }
		            
		        patientNameTextField.setEditable(true);
		        patientLastNameTextField.setEditable(true);
		        patientPeselTextField.setEditable(true);
	        	patientManRadioButton.setEnabled(true);
	        	patientWomanRadioButton.setEnabled(true);
	        	patientInsuranceList.setEnabled(true);
	        	patientSaveButton.setEnabled(true);
	        	patientDeleteButton.setEnabled(true);     	

	        	Test patientTest = new Test();
	        	patientTest = controlData.getTestMap().get(patientId);
		        				        				        				      
	        	if(patientTest == null)
		        {
	        		currentDateTextField.setText("");    
			        valueCKTextField.setText("");
			        valueKTextField.setText("");
			        valueTnTextField.setText("");				       
	        	}
	        	else
	        	{
	        		currentDateTextField.setText(patientTest.getData());    
	        		valueCKTextField.setText(String.valueOf(patientTest.getConcentrationCK()));
	        		valueKTextField.setText(String.valueOf(patientTest.getConcentrationK()));
	        		valueTnTextField.setText(String.valueOf(patientTest.getConcentrationTn()));
	        	}
		        
	        	currentDateTextField.setEditable(true);    
		        valueCKTextField.setEditable(true);
		        valueKTextField.setEditable(true);
		        valueTnTextField.setEditable(true);
		        testSaveButton.setEnabled(true);
	        	testDeleteButton.setEnabled(true);
	        	addCurrentDateButton.setEnabled(true);		        			        	
	        }
        });
		
		/*
		 * data etykieta
		 */
		currentDate = new JLabel("Data: ");
		currentDate.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		currentDate.setLocation(90, 420);
		currentDate.setSize(250,20);			
		dataPanel.add(currentDate);
		 
		/*
		 * data pole tekstowe
		 */			
		currentDateTextField = new JTextField(20);
		currentDateTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		currentDateTextField.setLocation(150,420);
		currentDateTextField.setSize(140, 20);
		currentDateTextField.setEditable(false);
		dataPanel.add(currentDateTextField);
	
		/*
		 * przycisk wstawia aktualn� dat�
		 */
		addCurrentDateButton = new JButton("Dzisiejsza data");
		addCurrentDateButton.setLocation(300,420);
		addCurrentDateButton.setSize(150,20);
		addCurrentDateButton.setEnabled(false);
	  
		addCurrentDateButton.addActionListener(new ActionListener() 
		{  
			public void actionPerformed(ActionEvent e) 
	        {   
				DateFormat df = new SimpleDateFormat("dd/MM/yy");
	        	Calendar calobj = Calendar.getInstance();
	        	currentDateTextField.setText(df.format(calobj.getTime()));		        	
	        }
		});			 
		dataPanel.add(addCurrentDateButton);			 
	 
		/*
		 * st�enie CK etykieta
		 */
		valueCK = new JLabel("St�enie CK [IU/I]: ");
		valueCK.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		valueCK.setLocation(90, 460);
		valueCK.setSize(200,20);			
		dataPanel.add(valueCK);
		 
		/*
		 * st�enia CK pole tekstowe
		 */
		valueCKTextField = new JTextField(20);
		valueCKTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		valueCKTextField.setLocation(310,460);
		valueCKTextField.setSize(140, 20);
		valueCKTextField.setEditable(false);
		valueCKTextField.addFocusListener(this);
		 
		numberErrorLabelValueCKTextField=new JLabel("* Wstaw tylko liczby!");
		numberErrorLabelValueCKTextField.setBounds(310, 482, 220, 12);
		numberErrorLabelValueCKTextField.setFont(new Font("Courier New", Font.BOLD, 10));
		numberErrorLabelValueCKTextField.setVisible(false);			 
		dataPanel.add(numberErrorLabelValueCKTextField);		
		dataPanel.add(valueCKTextField);			 
		
		/*
		 * st�enie K etykieta
		 */
		valueK = new JLabel("St�enie K [mmol/l]: ");
		valueK.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		valueK.setLocation(90, 500);
		valueK.setSize(250,20);			
		dataPanel.add(valueK);
		 
		/*
		 * st�enia K pole tekstowe
		 */
		valueKTextField = new JTextField(20);
		valueKTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		valueKTextField.setLocation(310,500);
		valueKTextField.setSize(140, 20);
		valueKTextField.setEditable(false);
		valueKTextField.addFocusListener(this);
		 
		numberErrorLabelValueKTextField=new JLabel("* Wstaw tylko liczby!");
		numberErrorLabelValueKTextField.setBounds(310, 522, 220, 12);
		numberErrorLabelValueKTextField.setFont(new Font("Courier New", Font.BOLD, 10));
		numberErrorLabelValueKTextField.setVisible(false);			 
		dataPanel.add(numberErrorLabelValueKTextField);		
		dataPanel.add(valueKTextField);
		 
		
		/*
		 * st�enie Tn etykieta
		 */
		valueTn = new JLabel("St�enie K [ng/ml]: ");
		valueTn.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		valueTn.setLocation(90, 540);
		valueTn.setSize(250,20);			
		dataPanel.add(valueTn);
		 
		/*
		 * st�enia Tn pole tekstowe
		 */
		valueTnTextField = new JTextField(20);
		valueTnTextField.setFont(new Font("Courier New", Font.HANGING_BASELINE, 20));
		valueTnTextField.setLocation(310,540);
		valueTnTextField.setSize(140, 20);
		valueTnTextField.setEditable(false);
		valueTnTextField.addFocusListener(this);
		 
		numberErrorLabelValueTnTextField=new JLabel("* Wstaw tylko liczby!");
		numberErrorLabelValueTnTextField.setBounds(310, 562, 220, 12);
		numberErrorLabelValueTnTextField.setFont(new Font("Courier New", Font.BOLD, 10));
		numberErrorLabelValueTnTextField.setVisible(false);
		
		dataPanel.add(numberErrorLabelValueTnTextField);
		dataPanel.add(valueTnTextField);
		
		/*
		 * przycisk zapisz test
		 */
		testSaveButton=new JButton("Zapisz");
		testSaveButton.setLocation(90, 580);
		testSaveButton.setSize(170, 35);
		testSaveButton.setEnabled(false);			
		dataPanel.add(testSaveButton);
		 
		/*
		 * przycisk usu� test
		 */
		testDeleteButton=new JButton("Usu�");
		testDeleteButton.setLocation(285, 580);
		testDeleteButton.setSize(170, 35);
		testDeleteButton.setEnabled(false);
		 
		dataPanel.add(testDeleteButton);			
		testDeleteButton.addActionListener(new ActionListener() 
		{  
			public void actionPerformed(ActionEvent e) 
		    {  
				currentDateTextField.setText("");;
		        valueCKTextField.setText("");
		        valueKTextField.setText("");
		        valueTnTextField.setText("");;
		    }
		});
	 
		/*
		 * akcja dla przycisku zapisz dla testu
		 */
		testSaveButton.addActionListener(new ActionListener() 
		{  
			public void actionPerformed(ActionEvent e) 
		    {  			    
				int integerPatientPesel = 0;
		        try {
		        	integerPatientPesel = Integer.parseInt(patientPeselTextField.getText());
		        	patientId = integerPatientPesel;			        		  
		        } catch (NumberFormatException e1) {
		        	System.out.println("not a number"); 
		        } 
		        				        
		        String testCurrentDate = currentDateTextField.getText();
		        float testValueCK =  Float.parseFloat(valueCKTextField.getText());
		        float testValueK = Float.parseFloat(valueKTextField.getText());
		        float testValueTn = Float.parseFloat(valueTnTextField.getText());			        
		        	
	        	boolean addDataToTable = true;			        	
	        	for(int i=0; i < controlData.getPatientIdList().size(); i++)
		        {
	        		if(patientId == controlData.getPatientIdList().get(i))
		        	{
	        			addDataToTable = false;
		        	}
		        }	
		        	
		        if(addDataToTable==true)
		        {
		        	controlData.setTestData(testCurrentDate, testValueCK , testValueK,testValueTn, patientId);
		        }
		        else
		        {
		        	controlData.updateTestData(testCurrentDate, testValueCK , testValueK,testValueTn, patientId);
		        }

		        if (model.getRowCount() > 0) 
		    	{
		        	for (int i = model.getRowCount() - 1; i > -1; i--) {
		        		model.removeRow(i);			    			       
    			    }
		    	}
		        	
		        for(int i=0; i<controlData.getPatientIdList().size(); i++)
		        {
		        	int patientIdAddToList = controlData.getPatientIdList().get(i);
		        	PatientData patientData=controlData.getPatientDataMap().get(patientIdAddToList);
		        		
		        	row[0] = patientData.getPatientName() + patientData.getPatientLastName();
		        	row[1] = patientData.getPatientSex();
		        	row[2] = patientData.getPatientPesel();
		        	row[3] = patientData.getPatientInsurance();			        					        
		        	Test patientTest = new Test();
		        	patientTest = controlData.getTestMap().get(patientIdAddToList);
		        		
		        	if(patientTest == null)
		        		row[4] = false;
		        	if(patientTest != null)
		        		row[4] = true;			        					        
	        		model.addRow(row);			        			
	        	}
		        	
	        	currentDateTextField.setText("");
		        valueCKTextField.setText("");
		        valueKTextField.setText("");
		        valueTnTextField.setText("");
		        	
		        currentDateTextField.setEditable(false);
		        valueCKTextField.setEditable(false);
		        valueKTextField.setEditable(false);
		        valueTnTextField.setEditable(false);			        
		        
		      	patientNameTextField.setEditable(false);			        		
	        	patientLastNameTextField.setEditable(false);
	        	patientPeselTextField.setEditable(false);
	        	patientManRadioButton.setEnabled(false);
	        	patientWomanRadioButton.setEnabled(false);
	        	patientInsuranceList.setEnabled(false);;
	        	patientSaveButton.setEnabled(false);
	        	patientDeleteButton.setEnabled(false);
	        }  
		});  
	 			 
		/*
		 * przycisk zapisz lista pacjent�w
		 */
		patientListAddButton=new JButton("Dodaj");
		patientListAddButton.setLocation(670, 580);
		patientListAddButton.setSize(170, 35);		
		dataPanel.add(patientListAddButton);
		 
		patientListAddButton.addActionListener(new ActionListener() 
		{  
			public void actionPerformed(ActionEvent e) 
		    {   
				updatePersonData = false;
		        System.out.println("updatePersonData: "+updatePersonData);
		        	
		        patientNameTextField.setEditable(true);
		        patientLastNameTextField.setEditable(true);
		        patientPeselTextField.setEditable(true);
		        patientManRadioButton.setEnabled(true);
	        	patientWomanRadioButton.setEnabled(true);
	        	patientInsuranceList.setEnabled(true);;
	        	patientSaveButton.setEnabled(true);
	        	patientDeleteButton.setEnabled(true);

	        	currentDateTextField.setEditable(true);
	        	valueCKTextField.setEditable(true);
	        	valueKTextField.setEditable(true);
	        	valueTnTextField.setEditable(true);
	        	testSaveButton.setEnabled(true);
	        	testDeleteButton.setEnabled(true);
	        	addCurrentDateButton.setEnabled(true);			        
	        }  
		});  
		 
		/*
		 * przycisk usu� lista pacjent�w
		 */
		patientListDeleteButton=new JButton("Usu�");
		patientListDeleteButton.setLocation(870, 580);
		patientListDeleteButton.setSize(170, 35);			
		dataPanel.add(patientListDeleteButton);

		/*
		 * akcja przycisku usu� lista pacjent�w
		 */
		patientListDeleteButton.addActionListener(new ActionListener() 
		{  
			public void actionPerformed(ActionEvent e) 
		    {       
				System.out.println("Frame(): patientId: "+patientId);
		        controlData.delteRow(patientId);;			        
	        	if (model.getRowCount() > 0) 
	        	{
	        		for (int i = model.getRowCount() - 1; i > -1; i--) {
	        			model.removeRow(i);			    			       
    			    }
	        	}			        	
		        	
		        for(int i=0; i<controlData.getPatientIdList().size(); i++)
		        {
		        	int patientIdAddToList = controlData.getPatientIdList().get(i);
		        	PatientData patientData=controlData.getPatientDataMap().get(patientIdAddToList);
		        		
		        	row[0] = patientData.getPatientName() + patientData.getPatientLastName();
		        	row[1] = patientData.getPatientSex();
		        	row[2] = patientData.getPatientPesel();
		        	row[3] = patientData.getPatientInsurance();			        		
		        		
		        	Test patientTest = new Test();
		        	patientTest = controlData.getTestMap().get(patientIdAddToList);
		        	
		        	if(patientTest == null)
		        		row[4] = false;
		        	if(patientTest != null)
		        		row[4] = true;
		        					        		
		        	model.addRow(row);
		        	
		        	patientNameTextField.setEditable(false);			        		
		        	patientLastNameTextField.setEditable(false);
		        	patientPeselTextField.setEditable(false);
		        	patientManRadioButton.setEnabled(false);
		        	patientWomanRadioButton.setEnabled(false);
		        	patientInsuranceList.setEnabled(false);;
		        	patientSaveButton.setEnabled(false);
		        	patientDeleteButton.setEnabled(false);

		        	currentDateTextField.setEditable(false);
		        	addCurrentDateButton.setEnabled(false);
		        	valueCKTextField.setEditable(false);
		        	valueKTextField.setEditable(false);
		        	valueTnTextField.setEditable(false);
		        	testSaveButton.setEnabled(false);
		        	testDeleteButton.setEnabled(false);			        		
		        		
		        	patientNameTextField.setText("");
		        	patientLastNameTextField.setText("");
		        	patientPeselTextField.setText("");
		        	patientManRadioButton.setSelected(false);
		        	patientWomanRadioButton.setSelected(false);
		        	patientInsuranceList.setSelectedItem(typeOfInsurance[1]);
		        	
		        	currentDateTextField.setText("");
		        	valueCKTextField.setText("");
		        	valueKTextField.setText("");
		        	valueTnTextField.setText("");				
	        	}			        				      
	        }
		});
	 	setContentPane(dataPanel);
		setVisible(true);
	}
	
	public void focusGained(FocusEvent e) 
	{
		
	}
	
	public void focusLost(FocusEvent e)
	{   
		if(e.getSource().equals(patientNameTextField))
		{
			validationNameTextField(patientNameTextField);
		} 
		else if(e.getSource().equals(patientLastNameTextField))
		{
			validationLastNameTextField(patientLastNameTextField);
		}
		else if(e.getSource().equals(patientPeselTextField))
		{
			validationPeselTextField(patientPeselTextField);
		}
		else if(e.getSource().equals(valueCKTextField))
		{
			validationValueCKTextField(valueCKTextField);
		}
		else if(e.getSource().equals(valueKTextField))
		{
			validationValueKTextField(valueKTextField);
		}
		else if(e.getSource().equals(valueTnTextField))
		{
			validationValueTnTextField(valueTnTextField);
		}		
	}
		
	public void validationNameTextField(JTextComponent comp)
	{
		String text=comp.getText().trim();
		if(text.matches("^[a-z,A-Z]+"))
		{
			stringErrorLabelNameTextField.setVisible(false);
		}
		else
		{
			stringErrorLabelNameTextField.setVisible(true);
		}
	}
	
	public void validationLastNameTextField(JTextComponent comp)
	{
		String text=comp.getText().trim();
		if(text.matches("^[a-z,A-Z]+"))
		{
			stringErrorLabelLastNameTextField.setVisible(false); 
		}
		else
		{
			stringErrorLabelLastNameTextField.setVisible(true);
		}
	}
	
	public void validationPeselTextField(JTextComponent comp)
	{
		String text=comp.getText().trim();
		if(text.matches("^[0-9]+"))
		{
			numberErrorLabelPeselTextField.setVisible(false);
		}
		else
		{
			numberErrorLabelPeselTextField.setVisible(true);
		}
	}
	
	public void validationValueCKTextField(JTextComponent comp)
	{
		String text=comp.getText().trim();
		if(text.matches("^[0-9]+"))
		{
			numberErrorLabelValueCKTextField.setVisible(false);
		}
		else
		{
			numberErrorLabelValueCKTextField.setVisible(true);
		}
	}
	
	public void validationValueKTextField(JTextComponent comp)
	{
		String text=comp.getText().trim();
		if(text.matches("^[0-9]+"))
		{
			numberErrorLabelValueKTextField.setVisible(false);
		}
		else
		{
			numberErrorLabelValueKTextField.setVisible(true);
		}
	}
	
	public void validationValueTnTextField(JTextComponent comp)
	{
		String text=comp.getText().trim();
		if(text.matches("^[0-9]+"))
		{
			numberErrorLabelValueTnTextField.setVisible(false);
		}
		else
		{
			numberErrorLabelValueTnTextField.setVisible(true);
		}
	}
}