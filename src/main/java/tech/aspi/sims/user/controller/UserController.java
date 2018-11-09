package tech.aspi.sims.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.user.model.User;
import tech.aspi.sims.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;


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

    @PostMapping("/delete")
    public String delete(String user_id){
        userService.delete(user_id);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findbyid")
    public User findOne(String user_id){
        return userService.findOne(user_id);
    }

    @GetMapping("/findbylevel")
    public List<User> queryByUserLevel(int user_level){
        return  userService.queryByUserLevel(user_level);
    }
}
