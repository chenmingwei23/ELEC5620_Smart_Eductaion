package com.ELEC5620.service;

import com.ELEC5620.dao.LoginTicketMapper;
import com.ELEC5620.dao.UserMapper;
import com.ELEC5620.entity.LoginTicket;
import com.ELEC5620.entity.User;
import com.ELEC5620.util.EmailClient;
import com.ELEC5620.util.NineyardsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ELEC5620.util.NineyardConstants.TYPE_Client;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    public User find(int id) {
        return userMapper.selectById(id);
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        //空值处理
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (StringUtils.isBlank(user.getFirstName())) {
            map.put("usernameMsg", "first name is null");
            return map;
        }
        if (StringUtils.isBlank(user.getLastName())) {
            map.put("usernameMsg", "second name is null");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "password is null");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "email is null");
            return map;
        }
//        if (StringUtils.isBlank((user.getMobile()))) {
//            map.put("mobileMsg", "mobile is null");
//            return map;
//        }

        User u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "email is already taken");
            return map;
        }
        insertUser(user);
        return map;
    }

    public Map<String, Object> login(String email, String password, int expiredSeconds) {
        Map<String, Object> map = new HashMap<>();

        User user = userMapper.selectByEmail(email);
        if (user == null) {
            map.put("userEmailMsg", "account is not exist");
            return map;
        }

        if (!user.getPassword().equals(password)) {
            map.put("passwordMsg", "incorrect password");
            return map;
        }

        // 生成登陆凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
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
        User user = userMapper.selectById(decodeUserId(code));
        user.setPassword(password);
        userMapper.updateUser(user);
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

    public User findById(int userId) {
        return userMapper.selectById(userId);
    }

    public User findByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public List<User> selectSpecificTypeUser(int companyId, int type) {
        List<User> pendingToFilter = userMapper.selectByCompany(companyId);
        List<User> resUser = new ArrayList<>();
        for (User user : pendingToFilter) {
            if (user.getType() == type) {
                resUser.add(user);
            }
        }
        return resUser;
    }

    public List<User> selectClient(int pmId){
        List<User> users = userMapper.selectUserByPMId(pmId);
        List<User> clients = new ArrayList<>();
        for (User user: users){
            if (user.getType() == TYPE_Client){
                clients.add(user);
            }
        }
        return clients;
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public int deleteUser(int id) {
        return userMapper.deleteById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
