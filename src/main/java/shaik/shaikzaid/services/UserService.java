package shaik.shaikzaid.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shaik.shaikzaid.domain.User;
import shaik.shaikzaid.exceptions.UsernameAlreadyExistsException;
import shaik.shaikzaid.exceptions.UsernameAlreadyExistsResponse;
import shaik.shaikzaid.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
           newUser.setUsername(newUser.getUsername());
            //Usernaem has to be unique
            //Make sure that password and confirmPassword match
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("UserName"+ newUser.getUsername()+"already exists");
        }

    }

}
