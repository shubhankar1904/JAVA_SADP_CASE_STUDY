package com.sapient.moneytree.portfolioManager.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * class is portfolio manager and table is Users
 * 
 * @author Ankush Tyagi
 *
 */
@Entity
@Table(name = "Users")
public class PortfolioManager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pMId;
	@Column(unique = true)

	private String uname;
	private String password;
	private String fname;
	private String lname;
	private int role;

	public PortfolioManager() {

	}

	public int getpMId() {
		return pMId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public PortfolioManager(String password, String fname, String lname, int role) {
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.role = role;

	}

}
