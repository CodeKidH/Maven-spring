package logic;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer carNo;
	
	private String carName;

	public Integer getCarNo() {
		return carNo;
	}

	public void setCarNo(Integer carNo) {
		this.carNo = carNo;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	
	
	

	
	
}
