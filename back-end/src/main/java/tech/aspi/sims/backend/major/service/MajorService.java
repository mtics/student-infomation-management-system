package tech.aspi.sims.backend.major.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.major.dao.MajorRepository;
import tech.aspi.sims.backend.major.model.Major;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class MajorService {

    @Resource
    private MajorRepository majorRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Major major){
        majorRepository.save(major);
    }

    // 删除数据
    @Transactional
    public void deleteById(int majorId) {
        majorRepository.deleteById(majorId);
    }

    // 查询数据
    @Transactional
    public Iterable<Major> findAll() {
        return majorRepository.findAll();
    }

    @Transactional
    public Optional<Major> findById(int majorId){
        return  majorRepository.findById(majorId);
    }
}
