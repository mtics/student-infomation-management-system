package tech.aspi.sims.teacher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.teacher.model.Teacher;
import tech.aspi.sims.teacher.service.TeacherService;

import javax.annotation.Resource;


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

    @PostMapping("/delete")
    public String delete(String teacherId){
        teacherService.delete(teacherId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Teacher> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/findbyid")
    public Teacher findOne(String teacherId){
        return teacherService.findOne(teacherId);
    }
}
