package tech.aspi.sims.backend.teacher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.student.model.Student;
import tech.aspi.sims.backend.teacher.model.Teacher;
import tech.aspi.sims.backend.teacher.service.TeacherService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    //保存数据
    @PostMapping("/save")
    public String save(Teacher teacher){
        teacherService.save(teacher);
        return "SUCCESS";
    }

    @PostMapping("/deletebyid")
    public String deleteById(String teacherId){
        teacherService.deleteById(teacherId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Teacher> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/findbyid")
    public Optional<Teacher> findById(String teacherId){
        return teacherService.findById(teacherId);
    }

    @GetMapping("/findallbyparams")
    public List<Teacher> findAllByParams(Teacher teacher){
        return teacherService.findAllByParams(teacher);
    }
}
