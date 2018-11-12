package tech.aspi.sims.backend.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.student.model.Student;
import tech.aspi.sims.backend.student.service.StudentService;

import javax.annotation.Resource;
import java.util.Optional;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    //保存数据
    @PostMapping("/save")
    public String save(Student student){
        studentService.save(student);
        return "SUCCESS";
    }

    @PostMapping("/deletebyid")
    public String deleteById(String stu_id){
        studentService.deleteById(stu_id);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/findbyid")
    public Optional<Student> findById(String stu_id){
        return studentService.findById(stu_id);
    }
}
