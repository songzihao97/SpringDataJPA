package com.szh.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;
    @Column(name="role_name")
    private String roleName;
    @Column(name="role_memo")
    private String roleMemo;

    //多对多关系映射
    /*@ManyToMany(targetEntity = User.class)
    @JoinTable(name = "sys_user_role",//中间表名
            //当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")},
            //对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")})*/
    @ManyToMany(mappedBy = "roles")//放弃关系维护
    private Set<User> users=new HashSet<>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
