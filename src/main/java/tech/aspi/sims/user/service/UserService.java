package tech.aspi.sims.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.user.dao.UserRepository;
import tech.aspi.sims.user.model.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    // 删除数据
    @Transactional
    public void delete(String user_id) {
        userRepository.delete(user_id);
    }

    // 查询数据
    @Transactional
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findOne(String user_id){
        return  userRepository.findOne(user_id);
    }

    @Transactional
    public List<User> queryByUserLevel(int user_level){
        return userRepository.queryByUserLevel(user_level);
    }
}
