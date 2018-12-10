package tech.aspi.sims.backend.subject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.subject.model.Subject;
import tech.aspi.sims.backend.subject.service.SubjectService;

import javax.annotation.Resource;
import java.util.Optional;


@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    //保存数据
    @PostMapping("/save")
    public String save(Subject subject){
        subjectService.save(subject);
        return "SUCCESS";
    }

    @PostMapping("/deletebyid")
    public String deleteById(int subjectId){
        subjectService.deleteById(subjectId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Subject> findAllByParams(Subject subject){
        return  subjectService.findAllByParams(subject);
    }
}
