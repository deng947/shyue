package org.cb.zframe.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.cb.zframe.annotation.ClassView;
import org.cb.zframe.annotation.FieldView;

@ClassView(displayName="人员",displayField="name")
@Entity
@NamedQueries( {
		@NamedQuery(name = "findByName", query = "select p from Person p where p.name = :name "),
		@NamedQuery(name = "findByNameStart", query = "select p from Person p where p.name like :name ") })
public class Person {
	@FieldView(displayName="编号")
	private long id;
	@FieldView(displayName="姓名",size=30)
	private String name;
	private String email;
	private String address;
	private int age;
	// private float fla;
	// private double dou;
	private boolean checked;

	// private Dept dept;
	// private Float ffff;
	// private Double dooo;
	// private Boolean boofd;
	// private List<Dept> depts;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Person(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Person() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
