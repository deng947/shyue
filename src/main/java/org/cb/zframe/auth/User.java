package org.cb.zframe.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * 用户
 * @author pesome
 * @date 2009-2-10
 */
@Entity
@Table(name = "T_USER")
@NamedQueries( {
		@NamedQuery(name = "user.findUserByUserName", query = "select p from User p where p.name = :name "),
		@NamedQuery(name = "user.findUserByRealNameStart", query = "select p from User p where p.realName like :name "),
		@NamedQuery(name = "user.findUserByDeptAndRole", query = "select u from User u where u.dept= :dept and :role in elements(u.roles)"),
		@NamedQuery(name = "user.findUserByRole", query = "select u from User u where :role in elements(u.roles)"),
		@NamedQuery(name = "user.findUserByRoleAndOrg", query = "select u from User u where :role in elements(u.roles) and u.dept.org= :org") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, length = 40)
	private String realName;

	private String email;

	private String phone;

	private String mobile;

	private String position;

	@Column(nullable = false, length = 20, unique = true)
	private String name; // 唯一标识，本项目问员工号

	@Column(nullable = false, length = 100)
	private String password;

	@ManyToOne()
	private Dept dept;

	@ManyToMany(targetEntity = Role.class)
	private Collection<Role> roles;

	public Collection<Resource> getResources() {
		Collection<Resource> functions = new HashSet<Resource>();
		for (Role role : roles) {
			functions.addAll(role.getResources());
		}
		return functions;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}

	public void removeRole(Role role) {
		roles.remove(role);
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public User() {
	}

	public User(String name) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		final User user = (User) o;

		return !(name != null ? !name.equals(user.getName())
				: user.getName() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}
}
