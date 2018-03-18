package com.yusufcakal.ecommerce.controller;

import com.yusufcakal.ecommerce.mail.SmtpMailSender;
import com.yusufcakal.ecommerce.model.User;
import com.yusufcakal.ecommerce.repository.UserRepository;
import com.yusufcakal.ecommerce.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SmtpMailSender smtpMailSender;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<?> register(@RequestBody User user) throws EntityNotFoundException {
        boolean userFlag = false;
        List<User> userList = (List<User>) userRepository.findAll();

        if (userList.size() == 0){
            userFlag = true;
        }else{
            for (int i=0; i<userList.size(); i++){
                if (user.getEmail().equals(userList.get(i).getEmail())){
                    userFlag = false; // User already
                    break;
                }else{
                    userFlag= true; // User does not exist
                }
            }
        }

        if (userFlag){
            user.setToken(TokenUtil.generateToken());
            userRepository.save(user);
            try {
                smtpMailSender.send(user);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/verify/{token}")
    public ResponseEntity<HttpStatus> verify(@PathVariable int token) throws EntityNotFoundException {

        List<User> userList = (List<User>) userRepository.findAll();

        for (User user : userList) {
            if (user.getToken() == token){
                user.setVerify(true);
                userRepository.save(user);
                break;
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Integer> login(@RequestBody User user) throws EntityNotFoundException {
        List<User> userList = (List<User>) userRepository.findAll();

        int token = 0;

        for (User userResult : userList) {
          if (userResult.getEmail().equals(user.getEmail()) && userResult.getPassword().equals(user.getPassword())){
              if (userResult.isVerify()){
                  token = userResult.getToken();
                  break;
              }else{
                  token = -1; // User not verify
              }
          }
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
