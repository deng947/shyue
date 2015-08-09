package org.cb.zframe.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.cb.zframe.annotation.ClassView;
import org.cb.zframe.annotation.FieldView;

/**
 * 组织机构，目前指公司，下面直接挂一级部门，如山东移动，日照移动
 * 
 * @author pesome
 * @date Nov 26, 2008
 */
@Entity
@ClassView(displayName="公司",displayField="spell")
@Table(name = "T_ORG")
@NamedQueries( { @NamedQuery(name = "org.findByOrgBizId", query = "select o from Org o where o.orgId = :orgId ") })
public class Org {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@FieldView(displayName="编号")
	private long id;

	@Column(nullable = false, length = 200, unique = true)
	@FieldView(displayName="公司名称")
	private String name;

	@Column(nullable = false, length = 160, unique = true)
	@FieldView(displayName="公司拼音")
	private String spell;

	@Column(nullable = false, length = 10, unique = true)
	private long orgId; // 业务id，统一编号

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

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
}
