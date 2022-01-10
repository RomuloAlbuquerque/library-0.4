package com.library04.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long idLog;
	private String emailLog;
	private String profileLog;
	
	public Profile() {
		
	}

	public Profile(Long idLog, String emailLog, String profileLog) {
		this.idLog = idLog;
		this.emailLog = emailLog;
		this.profileLog = profileLog;
	}

	public Long getIdLog() {
		return idLog;
	}

	public void setIdLog(Long idLog) {
		this.idLog = idLog;
	}

	public String getEmailLog() {
		return emailLog;
	}

	public void setEmailLog(String emailLog) {
		this.emailLog = emailLog;
	}

	public String getProfileLog() {
		return profileLog;
	}

	public void setProfileLog(String profileLog) {
		this.profileLog = profileLog;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLog == null) ? 0 : idLog.hashCode());
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
		Profile other = (Profile) obj;
		if (idLog == null) {
			if (other.idLog != null)
				return false;
		} else if (!idLog.equals(other.idLog))
			return false;
		return true;
	}

}
