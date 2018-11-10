package tech.aspi.sims.college.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.college.dao.CollegeRepository;
import tech.aspi.sims.college.model.College;

import javax.annotation.Resource;

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
    public void delete(int collegeId) {
        collegeRepository.delete(collegeId);
    }

    // 查询数据
    @Transactional
    public Iterable<College> findAll() {
        return collegeRepository.findAll();
    }

    @Transactional
    public College findOne(int collegeId){
        return  collegeRepository.findOne(collegeId);
    }
}
