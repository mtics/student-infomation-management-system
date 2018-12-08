package tech.aspi.sims.backend.student.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.student.model.Student;
import tech.aspi.sims.backend.student.service.StudentService;

import javax.annotation.Resource;
import java.util.List;
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
    public String deleteById(String studentId){
        studentService.deleteById(studentId);
        return "SUCCESS";
    }

    @PostMapping("/delete")
    public String deleteById(Student student){
        studentService.delete(student);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/findbyid")
    public Optional<Student> findById(String studentId){
        return studentService.findById(studentId);
    }

    @GetMapping("/findallbyparams")
    public List<Student> findAllByParams(Student student){
        return studentService.findAllByParams(student);
    }
}
