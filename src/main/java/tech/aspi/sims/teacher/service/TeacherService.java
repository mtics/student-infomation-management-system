package tech.aspi.sims.teacher.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.teacher.dao.TeacherRepository;
import tech.aspi.sims.teacher.model.Teacher;

import javax.annotation.Resource;
import java.util.List;

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
    public void delete(String teacherId) {
        teacherRepository.delete(teacherId);
    }

    // 查询数据
    @Transactional
    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public Teacher findOne(String teacherId){
        return  teacherRepository.findOne(teacherId);
    }
}
