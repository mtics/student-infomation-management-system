package tech.aspi.sims.backend.teacher.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.teacher.dao.TeacherRepository;
import tech.aspi.sims.backend.teacher.model.Teacher;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }

    // 删除数据
    @Transactional
    public void deleteById(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    // 查询数据
    @Transactional
    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public Optional<Teacher> findById(String teacherId){
        return  teacherRepository.findById(teacherId);
    }
}
