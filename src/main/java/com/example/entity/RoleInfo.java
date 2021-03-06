package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author zhaolei
 * Create: 2020/2/25 12:12
 * Modified By:
 * Description: 权限表
 */
@Entity
@Table(name = "role_info")
@DynamicUpdate
@DynamicInsert
public class RoleInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "remark")
	private String remark;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "sys_user_role_rel",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "sys_user_id")}
	)
	private Set<SysUserInfo> sysUserInfos;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "role_permission_rel",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "permission_id")}
	)
	private Set<PermissionInfo> permissionInfos;

	public RoleInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<SysUserInfo> getSysUserInfos() {
		return sysUserInfos;
	}

	public void setSysUserInfos(Set<SysUserInfo> sysUserInfos) {
		this.sysUserInfos = sysUserInfos;
	}

	public Set<PermissionInfo> getPermissionInfos() {
		return permissionInfos;
	}

	public void setPermissionInfos(Set<PermissionInfo> permissionInfos) {
		this.permissionInfos = permissionInfos;
	}

	@Override
	public String toString() {
		return "RoleInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
