public class PatientData {
	/*
	 * imie
	 */
	private String patientName;
	/*
	 * nazwisko
	 */
	private String patientLastName;
	/*
	 * pesel
	 */
	private int patientPesel;
	/*
	 * plec
	 */
	private String patientSex;
	/*
	 * ubezpieczenie
	 */
	private String patientInsurance;
	
	public PatientData() {
		super();
	}

	public PatientData(String patientName, String patientLastName, int patientPesel, String patientSex,
			String patientInsurance) {
		super();
		this.patientName = patientName;
		this.patientLastName = patientLastName;
		this.patientPesel = patientPesel;
		this.patientSex = patientSex;
		this.patientInsurance = patientInsurance;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public int getPatientPesel() {
		return patientPesel;
	}

	public void setPatientPesel(int patientPesel) {
		this.patientPesel = patientPesel;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientInsurance() {
		return patientInsurance;
	}

	public void setPatientInsurance(String patientInsurance) {
		this.patientInsurance = patientInsurance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patientInsurance == null) ? 0 : patientInsurance.hashCode());
		result = prime * result + ((patientLastName == null) ? 0 : patientLastName.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime * result + patientPesel;
		result = prime * result + ((patientSex == null) ? 0 : patientSex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientData other = (PatientData) obj;
		if (patientInsurance == null) {
			if (other.patientInsurance != null)
				return false;
		} else if (!patientInsurance.equals(other.patientInsurance))
			return false;
		if (patientLastName == null) {
			if (other.patientLastName != null)
				return false;
		} else if (!patientLastName.equals(other.patientLastName))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		if (patientPesel != other.patientPesel)
			return false;
		if (patientSex == null) {
			if (other.patientSex != null)
				return false;
		} else if (!patientSex.equals(other.patientSex))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient_Data [patientName=" + patientName + ", patientLastName=" + patientLastName + ", patientPesel="
				+ patientPesel + ", patientSex=" + patientSex + ", patientInsurance=" + patientInsurance + "]";
	}	
}