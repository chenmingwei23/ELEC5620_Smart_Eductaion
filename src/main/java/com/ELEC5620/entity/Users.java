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
    private String lastName;

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

    @Column(name = "major")
    private String major;

    @Column(name = "Email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "post")
    private String post;

    @Column(name = "country")
    private String country;

    @Column(name = "intro")
    private String intro;

    @Column(name = "changPwd", columnDefinition="tinyint default 0")
    private int changPwd;

    public Users(){}

    public Users(String firstName, String lastName, String account, String password, int role, int gender) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        return lastName;
    }

    public void setLastName(String lsatName) {
        this.lastName = lastName;
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
