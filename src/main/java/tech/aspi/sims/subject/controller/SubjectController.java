package tech.aspi.sims.subject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.subject.model.Subject;
import tech.aspi.sims.subject.service.SubjectService;

import javax.annotation.Resource;


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

    @PostMapping("/delete")
    public String delete(Long sub_id){
        subjectService.delete(sub_id);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Subject> findAll(){
        return subjectService.findAll();
    }

    @GetMapping("/findbyid")
    public Subject findOne(long sub_id){
        return  subjectService.findOne(sub_id);
    }
}
