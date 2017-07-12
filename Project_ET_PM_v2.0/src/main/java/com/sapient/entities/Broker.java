package com.sapient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BROKER_1")
/**
 * Contains details about the broker entity like userName and password 
 * @author scho20
 *
 */
public class Broker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int brId;
	public String userName;
	private String password;

	public Broker() {

	}

	public int getbrId() {
		return brId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return userName;
	}

	public void setUname(String uname) {
		this.userName = uname;
	}

	public Broker(String password, String fname, String lname, int role) {
		this.password = password;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brId;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Broker other = (Broker) obj;
		if (brId != other.brId)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
