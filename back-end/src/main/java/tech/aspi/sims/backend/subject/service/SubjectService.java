package tech.aspi.sims.backend.subject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.subject.dao.SubjectRepository;
import tech.aspi.sims.backend.subject.model.Subject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class SubjectService {

    @Resource
    private SubjectRepository subjectRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    // 删除数据
    @Transactional
    public void deleteById(int sub_id) {
        subjectRepository.deleteById(sub_id);
    }

    // 查询数据
    @Transactional
    public Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional
    public Optional<Subject> findById(int sub_id){
        return  subjectRepository.findById(sub_id);
    }
}
