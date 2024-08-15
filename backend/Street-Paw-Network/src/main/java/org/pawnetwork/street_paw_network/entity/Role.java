package org.pawnetwork.street_paw_network.entity;

public class Role {
    private long id;
    private String roleName;
    private String roleDescription;

    public Role() {
    }

    public Role(long id, String roleName, String roleDescription) {
        this.id = id;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDescription() {
        return roleDescription;
    }
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "role [id=" + id + ", roleName=" + roleName + ", roleDescription=" + roleDescription + "]";
    }

}
