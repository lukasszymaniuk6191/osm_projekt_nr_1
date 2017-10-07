public class Test {
	/*
	 * data
	 */
	private String data;
	/*
	 * st�enie CK
	 */
	private float concentrationCK;
	/*
	 * st�enie K
	 */
	private float concentrationK;
	/*
	 * ste�enie Tn
	 */
	private float concentrationTn;
	
	public Test() {
		super();
	}

	public Test(String data, float concentrationCK, float concentrationK, float concentrationTn) {
		super();
		this.data = data;
		this.concentrationCK = concentrationCK;
		this.concentrationK = concentrationK;
		this.concentrationTn = concentrationTn;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public float getConcentrationCK() {
		return concentrationCK;
	}

	public void setConcentrationCK(float concentrationCK) {
		this.concentrationCK = concentrationCK;
	}

	public float getConcentrationK() {
		return concentrationK;
	}

	public void setConcentrationK(float concentrationK) {
		this.concentrationK = concentrationK;
	}

	public float getConcentrationTn() {
		return concentrationTn;
	}

	public void setConcentrationTn(float concentrationTn) {
		this.concentrationTn = concentrationTn;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(concentrationCK);
		result = prime * result + Float.floatToIntBits(concentrationK);
		result = prime * result + Float.floatToIntBits(concentrationTn);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		Test other = (Test) obj;
		if (Float.floatToIntBits(concentrationCK) != Float.floatToIntBits(other.concentrationCK))
			return false;
		if (Float.floatToIntBits(concentrationK) != Float.floatToIntBits(other.concentrationK))
			return false;
		if (Float.floatToIntBits(concentrationTn) != Float.floatToIntBits(other.concentrationTn))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Test [data=" + data + ", concentrationCK=" + concentrationCK + ", concentrationK=" + concentrationK
				+ ", concentrationTn=" + concentrationTn + "]";
	}	
}