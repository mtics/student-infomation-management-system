package tech.aspi.sims.backend.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.student.dao.StudentRepository;
import tech.aspi.sims.backend.student.model.Student;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class StudentService {

    @Resource
    private StudentRepository studentRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }

    // 删除数据
    @Transactional
    public void deleteById(String stu_id) {
        studentRepository.deleteById(stu_id);
    }

    // 查询数据
    @Transactional
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public Optional<Student> findById(String stu_id){
        return studentRepository.findById(stu_id);
    }
}
