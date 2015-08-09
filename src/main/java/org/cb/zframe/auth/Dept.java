package org.cb.zframe.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.cb.zframe.annotation.ClassView;
import org.cb.zframe.annotation.FieldView;
/**
 * 部门
 * @author pesome
 * @date 2009-2-10
 */
@Entity
@ClassView(displayName="部门")
@Table(name = "T_DEPT")
@NamedQueries( {
		@NamedQuery(name = "dept.findDeptByNameAndOrg", query = "select p from Dept p where p.name = :name and p.org=:org"),
		@NamedQuery(name = "dept.findDeptByOrg", query = "select p from Dept p where p.org=:org"),
		@NamedQuery(name = "dept.findDeptByNameStart", query = "select p from Dept p where p.name like :name ") })
public class Dept {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@FieldView(displayName="部门编号")
	private long id;

	@Column(nullable = false, length = 30)
	@FieldView(displayName="部门名称")
	private String name;

	@Column(nullable = false, length = 50)
	@FieldView(displayName="部门拼音")
	private String spell; // 拼音,方便自动完成
	
	@FieldView(displayName="测试")
    private String test;

	@ManyToOne(targetEntity = Org.class)
	private Org org;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Dept() {
	}

	public Dept(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
}
