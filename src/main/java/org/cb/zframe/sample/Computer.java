package org.cb.zframe.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.cb.zframe.annotation.ClassView;
import org.cb.zframe.annotation.FieldView;

@Entity
@ClassView(displayName = "电脑", displayField = "name")
public class Computer {
	@FieldView(displayName = "编号")
	private long id;

	@FieldView(displayName = "名称", size = 30)
	private String name;

	private Person person;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Person.class)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
