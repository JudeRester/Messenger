package messenger.common;

public class MemberDTO {
	private String id, passwd, name, alias, loc, sex, birth, phone;

	public MemberDTO(String id, String name, String alias) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
	}

	public MemberDTO() {
		
	}
	
	public MemberDTO(String id, String passwd, String name, String loc, String sex, String birth, String phone) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.loc = loc;
		this.sex = sex;
		this.birth = birth;
		this.phone = phone;
	}

	public MemberDTO(String id, String passwd, String name, String alias, String loc, String sex, String birth,
			String phone) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.alias = alias;
		this.loc = loc;
		this.sex = sex;
		this.birth = birth;
		this.phone = phone;
	}
	public MemberDTO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
