/*
 * 
 */
package org.cb.zframe.auth;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 角色
 * 
 * @author pesome
 * @date 2009-2-10
 */
@Entity
@Table(name = "T_ROLE")
@NamedQueries( {
		@NamedQuery(name = "role.findByRoleName", query = "select p from Role p where p.name = :name "),
		@NamedQuery(name = "role.findByRoleNameStart", query = "select p from Role p where p.name like :name ") })
public class Role {
	public static String ROLE_ADMIN = "ROLE_ADMIN";// 系统管理员

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, length = 20, unique = true)
	private String name;

	private String description;

	@ManyToMany(targetEntity = Resource.class, fetch = FetchType.EAGER)
	private Collection<Resource> resources;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public void addResource(Resource resource) {
		if (resources == null) {
			resources = new ArrayList<Resource>();
		}
		resources.add(resource);
	}

	public void removeResource(Resource resource) {
		this.resources.remove(resource);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Resource> getResources() {
		return resources;
	}

	public void setResources(Collection<Resource> resources) {
		this.resources = resources;
	}

}
