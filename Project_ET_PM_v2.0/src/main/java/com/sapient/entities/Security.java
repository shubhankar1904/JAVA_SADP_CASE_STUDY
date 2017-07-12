package com.sapient.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 10)
@NamedQuery(name = "Security.findById", query = "from Security e where e.id = id")
/**
 * it contains the details of all the securities in the system
 * @author scho20
 *
 */
public class Security {

	@Id
	@Column(name = "Security_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private long id;

	@Column(name = "Sec_Symbol")
	private String secSymbol;

	@Column(name = "Sec_Name")
	private String secName;

	@Column(name = "Last_Price")
	private double lastPrice;

	@Column(name = "Spread")
	private double spread;

	@Column(name = "Exec_No")
	private int execNo;

	@Column(name = "Interval")
	private double interval;

	@Column(name = "Probability")
	private double prob;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSecSymbol() {
		return secSymbol;
	}

	public void setSecSymbol(String secSymbol) {
		this.secSymbol = secSymbol;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getSpread() {
		return spread;
	}

	public void setSpread(double spread) {
		this.spread = spread;
	}

	public int getExecNo() {
		return execNo;
	}

	public void setExecNo(int execNo) {
		this.execNo = execNo;
	}

	public double getInterval() {
		return interval;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	

}
