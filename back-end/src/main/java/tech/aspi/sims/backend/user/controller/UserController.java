package tech.aspi.sims.backend.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.user.model.User;
import tech.aspi.sims.backend.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //保存数据
    @PostMapping("/save")
    public String save(User user){
        userService.save(user);
        return "SUCCESS";
    }

    @PostMapping("/deletebyid")
    public String deleteById(String userId){
        userService.deleteById(userId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findbyid")
    public Optional<User> findById(String userId){
        return userService.findById(userId);
    }

    @GetMapping("/findbylevel")
    public Iterable<User> queryByUserLevel(int userLevel){
        return  userService.queryByUserLevel(userLevel);
    }
}
