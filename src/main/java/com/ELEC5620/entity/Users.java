package com.ELEC5620.entity;

//import com.alibaba.fastjson.JSON;
//import lombok.*;
//
//import java.util.Date;
//import java.util.List;
//
//// 映射SQL中表的entity类
//@Data // equal @Getter @Setter @ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Users {
//    private int id;
//    private int pmId;
//    private int companyId;
//    private int type;           // 0- Project Manager; 1- Project Member; 2- Client;
//    private int status;         // 0- Active; 1-  Deleted; We dont actually delete data, the deleted data can be used in data science .
//    private String companyName;
//    private String password;
//    private String firstName;
//    private String lastName;
//    private String department;
//    private String position;
//    private String email;
//    private String otherContactName;
//    private String otherContactNumber;
//    private String landline;
//    private String mobile;
//    private String address;
//    private Date createTime;    // this data can be used in data science
//}
import javax.persistence.*;

@Entity
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lsatName;

    @Column(name = "acc")
    private String account;

    @Column(name = "pwd")
    private String password;

    @Column(name = "role")
    //1 -- teacher  0 -- students
    private int role;

    @Column(name = "gender")
    //1 -- male     0 -- female
    private int gender;

    public Users(){}

    public Users(String firstName, String lsatName, String account, String password, int role, int gender) {
        this.firstName = firstName;
        this.lsatName = lsatName;
        this.account = account;
        this.password = password;
        this.role = role;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLsatName() {
        return lsatName;
    }

    public void setLsatName(String lsatName) {
        this.lsatName = lsatName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
