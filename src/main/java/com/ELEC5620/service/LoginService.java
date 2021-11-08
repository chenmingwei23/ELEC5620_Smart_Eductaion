package com.ELEC5620.service;

import com.ELEC5620.dao.LoginTicketMapper;
import com.ELEC5620.dao.LoginMapper;
import com.ELEC5620.entity.LoginTicket;
import com.ELEC5620.entity.Users;
//import com.ELEC5620.util.EmailClient;
import com.ELEC5620.util.NineyardsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ELEC5620.util.NineyardConstants.TYPE_Client;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

//    @Autowired
//    private EmailClient emailClient;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    public Users find(int id) {
        return loginMapper.selectById(id);
    }

    public Map<String, Object> register(Users users) {
        Map<String, Object> map = new HashMap<>();
        //空值处理
        if (users == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (StringUtils.isBlank(users.getFirstName())) {
            map.put("usernameMsg", "first name is null");
            return map;
        }
        if (StringUtils.isBlank(users.getLsatName())) {
            map.put("usernameMsg", "second name is null");
            return map;
        }
        if (StringUtils.isBlank(users.getPassword())) {
            map.put("passwordMsg", "password is null");
            return map;
        }
        if (StringUtils.isBlank(users.getAccount())) {
            map.put("emailMsg", "email is null");
            return map;
        }
//        if (StringUtils.isBlank((user.getMobile()))) {
//            map.put("mobileMsg", "mobile is null");
//            return map;
//        }

        Users u = loginMapper.selectByEmail(users.getAccount());
        if (u != null) {
            map.put("emailMsg", "email is already taken");
            return map;
        }
        insertUser(users);
        return map;
    }

    public Map<String, Object> login(String email, String password, int expiredSeconds) {
        Map<String, Object> map = new HashMap<>();

        Users users = loginMapper.selectByEmail(email);
        if (users == null) {
            map.put("userEmailMsg", "account is not exist");
            return map;
        }

        if (!users.getPassword().equals(password)) {
            map.put("passwordMsg", "incorrect password");
            return map;
        }

        // 生成登陆凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(users.getId());
        loginTicket.setTicket(NineyardsUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
        loginTicketMapper.insertLoginTicket(loginTicket);

        map.put("ticket", loginTicket.getTicket());

        return map;
    }

    public void logout(String ticket) {
        loginTicketMapper.updateStatus(ticket, 1);
    }

    public Map<String, Object> resetPassword(String code, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "Password can't be empty!");
            return map;
        }
        Users users = loginMapper.selectById(decodeUserId(code));
        users.setPassword(password);
        loginMapper.updateUser(users);
        return map;
    }

    public int decodeUserId(String code){
        return Integer.valueOf(code.substring(5, code.length() - 5));
    }

    public String encodeUserId(int userId) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        sb.append(userId);
        for (int i = 0; i < 5; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public Users findById(int userId) {
        return loginMapper.selectById(userId);
    }

    public Users findByEmail(String email) {
        return loginMapper.selectByEmail(email);
    }

    public List<Users> selectSpecificTypeUser(int companyId, int type) {
        List<Users> pendingToFilter = loginMapper.selectByCompany(companyId);
        List<Users> resUsers = new ArrayList<>();
        for (Users users : pendingToFilter) {
            if (users.getRole() == type) {
                resUsers.add(users);
            }
        }
        return resUsers;
    }

    public List<Users> selectClient(int pmId){
        List<Users> users = loginMapper.selectUserByPMId(pmId);
        List<Users> clients = new ArrayList<>();
        for (Users user: users){
            if (user.getRole() == TYPE_Client){
                clients.add(user);
            }
        }
        return clients;
    }

    public void insertUser(Users users) {
        loginMapper.insertUser(users);
    }

    public int deleteUser(int id) {
        return loginMapper.deleteById(id);
    }

    public void updateUser(Users users) {
        loginMapper.updateUser(users);
    }
}
