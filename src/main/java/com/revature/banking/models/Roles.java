package com.revature.banking.models;
/*
* This Enum will contain all the roles a user can have
*
* */
public enum Roles {

    ADMIN ("Admin"),
    MANAGER ("Manager"),
    INACTIVE ("Inactive"),
    ACTIVE ("Active"),
    GOLD ("Gold"),
    LOCKED ("Locked");

    private String roleName;

    // enum constructors are implicitly private
    Roles(String name){
        this.roleName = name;
    }

    public static Roles getByName(String name){

        for (Roles role : Roles.values()){
            if (role.roleName.equals(name));{
                return role;
            }
        }

        return LOCKED;
    }

    @Override
    public String toString() {
        return roleName;
    }

}
