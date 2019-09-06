package DTO;

public class DD {
	private String id;
	private String name;
	private String phone;
	private String email;
	private String adr;
	private String note;
private String city;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
public String getCity() {
		return city;
	}
	public void setCity(String id) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
		
	
@Override
public String toString() {
	// TODO Auto-generated method stub
	return name ;
			
}
	
}
