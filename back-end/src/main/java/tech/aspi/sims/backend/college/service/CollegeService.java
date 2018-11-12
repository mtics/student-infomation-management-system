package tech.aspi.sims.backend.college.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.college.dao.CollegeRepository;
import tech.aspi.sims.backend.college.model.College;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CollegeService {

    @Resource
    private CollegeRepository collegeRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(College college){
        collegeRepository.save(college);
    }

    // 删除数据
    @Transactional
    public void deleteById(int collegeId) {
        collegeRepository.deleteById(collegeId);
    }

    // 查询数据
    @Transactional
    public Iterable<College> findAll() {
        return collegeRepository.findAll();
    }

    @Transactional
    public Optional<College> findById(int collegeId){
        return  collegeRepository.findById(collegeId);
    }
}
