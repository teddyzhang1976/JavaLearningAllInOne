package com.apdplat.module.security.model;

import com.apdplat.platform.annotation.ModelAttr;
import com.apdplat.platform.annotation.ModelCollRef;
import com.apdplat.platform.generator.ActionGenerator;
import com.apdplat.platform.model.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Scope("prototype")
@Component
@Table(name = "UserGroup",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userGroupName"})})
@XmlRootElement
@XmlType(name = "UserGroup")
public class UserGroup extends Model {
    @Column(length=40)
    @ModelAttr("用户组名称")
    protected String userGroupName;
    @ModelAttr("备注")
    protected String des;
    
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "userGroup_role", joinColumns = {
    @JoinColumn(name = "userGroupID")}, inverseJoinColumns = {
    @JoinColumn(name = "roleID")})
    @OrderBy("id")
    @ModelAttr("用户组拥有的角色列表")
    @ModelCollRef("roleName")
    protected List<Role> roles = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "userGroups", fetch = FetchType.LAZY)
    protected List<User> users=new ArrayList<>();

    public String getModuleCommandStr(){
        StringBuilder ids=new StringBuilder();
        for(Role role : roles){
            ids.append(role.getModuleCommandStr());
        }
        return ids.toString();
    }
    public String getRoleStrs(){
        if(this.roles==null || this.roles.isEmpty()) {
            return "";
        }
        StringBuilder result=new StringBuilder();
        for(Role role : this.roles){
            result.append("role-").append(role.getId()).append(",");
        }
        result=result.deleteCharAt(result.length()-1);
        return result.toString();
    }
    /**
     * 获取授予用户组的权利
     * @return
     */
    public List<String> getAuthorities() {
        List<String> result = new ArrayList<>();        
        for(Role role : roles){
            result.addAll(role.getAuthorities());
        }
        return result;
    }

    @XmlAttribute
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @XmlAttribute
    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role){
        this.roles.add(role);
    }
    
    public void removeRole(Role role){
        this.roles.remove(role);
    }
    
    public void clearRole(){
        this.roles.clear();
    }
    
    @XmlTransient
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public String getMetaData() {
        return "用户组信息";
    }

    public static void main(String[] args){
        UserGroup obj=new UserGroup();
        //生成Action
        ActionGenerator.generate(obj.getClass());
    }
}
