import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlData {
	/*
	 * przechowuje dane pacjenta wraz z kluczem(pesel)
	 */
	private Map<Integer, PatientData> patientDataMap = new HashMap<Integer, PatientData>();
	/*
	 * przechowuje wyniki bada� pacjent�w wraz z kluczem(pesel)
	 */
	private Map<Integer, Test> patientTestMap = new HashMap<Integer, Test>();
	/*
	 * przechowuje klucze pacjent�w 
	 */
	private List<Integer> patientIdList = new ArrayList<Integer>();
	/*
	 * reprezentuje badanie pacjentow
	 */
	private Test patientTest;
	/*
	 * reprezentuje dane pacjenta
	 */
	private PatientData patientData ;	
	
	/*
	 * usuwa pacjenta z listy
	 */
	public void deletePatient( int id)
	{
		int patientId = Integer.valueOf(id);
		patientTestMap.values().remove(patientId);
		patientDataMap.values().remove(patientId);		
	}
	
	/*
	 * aktualizuje dane pacjenta
	 */
	public void updatePatientData(String name, String lastname, int pesel, String sex, String insurence, int patientId)
	{
		System.out.println("updatePatientData patientId: "+patientId);
		
		patientDataMap.remove(patientId);
		patientIdList.remove((Object)patientId);
		
		patientData = new PatientData();		
		patientData.setPatientName(name);
		patientData.setPatientLastName(lastname);
		patientData.setPatientPesel(pesel);
		patientData.setPatientSex(sex);
		patientData.setPatientInsurance(insurence);
		
		patientDataMap.put(patientId, patientData);
		patientIdList.add(pesel);
		
	}
	
	/*
	 * ustawia dane pacjenta
	 */
	public void setPatientData(String name, String lastname, int pesel, String sex, String insurence, int patientId)
	{
		patientData = new PatientData();
		
		patientData.setPatientName(name);
		patientData.setPatientLastName(lastname);
		patientData.setPatientPesel(pesel);
		patientData.setPatientSex(sex);
		patientData.setPatientInsurance(insurence);
		
		patientDataMap.put(patientId, patientData);
		patientIdList.add(pesel);
		System.out.println("setPatientData patientId length: "+patientIdList.size());
		
	}
	
	/*
	 * usuwa pacjenta z listy pacjent�w
	 */
	public void delteRow(int pesel)
	{
		patientDataMap.remove((Object)pesel);
		patientIdList.remove((Object)pesel);
		patientTestMap.remove((Object)pesel);
	}
	
	/*
	 * zwraca dane pacjenta
	 */
	public PatientData getPatientData()
	{
		return patientData;
	}	
	
	/*
	 * aktualizuje wyniki bada� pacjenta
	 */
	public void updateTestData(String data, float concentrationCK,float concentrationK,float concentrationTn,int patientId)
	{
		patientTestMap.remove(patientId);
		patientIdList.remove((Object)patientId);
		
		patientTest = new Test();
		
		patientTest.setData(data);
		patientTest.setConcentrationCK(concentrationCK);
		patientTest.setConcentrationK(concentrationK);
		patientTest.setConcentrationTn(concentrationTn);
		
		patientTestMap.put(patientId, patientTest);
		patientIdList.add(patientId);
	}
	
	/*
	 * ustawia wyniki bada� pacjenta
	 */
	public void setTestData(String data, float concentrationCK,float concentrationK,float concentrationTn,int patientId)
	{
		patientTest = new Test();
		
		patientTest.setData(data);
		patientTest.setConcentrationCK(concentrationCK);
		patientTest.setConcentrationK(concentrationK);
		patientTest.setConcentrationTn(concentrationTn);
		
		patientTestMap.put(patientId, patientTest);
	}
	
	/*
	 * zwraca obiekt reprezantuj�cy dane badanie
	 */
	public Test getTestData()
	{
		return patientTest;
	}
	
	/*
	 * zwraca mape z danymi pacjent�w
	 */
	public Map<Integer, PatientData> getPatientDataMap()
	{
		return patientDataMap;
	}
	
	/*
	 * zwraca mape z wynikami bada� pacjent�w
	 */
	public Map<Integer, Test> getTestMap()
	{
		return patientTestMap;
	}
	
	/*
	 * zwraca liste z kluczami pacjent�w
	 */
	public List<Integer> getPatientIdList()
	{
		return patientIdList;
	}	
}