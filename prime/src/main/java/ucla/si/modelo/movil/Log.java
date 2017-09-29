package ucla.si.modelo.movil;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	Integer id;

	String message;

	Date date;

	public Log() {
		super();
	}
	
	public Log(Integer id, String message, Date date) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Log other = (Log)obj;
		if(id == null) {
			if(other.id != null)
				return false;
		} else if(!id.equals(other.id))
			return false;
		return true;
	}

}
