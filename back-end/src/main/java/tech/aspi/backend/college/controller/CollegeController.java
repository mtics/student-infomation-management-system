package tech.aspi.sims.college.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.college.model.College;
import tech.aspi.sims.college.service.CollegeService;

import javax.annotation.Resource;


@RestController
@RequestMapping("/college")
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    //保存数据
    @PostMapping("/save")
    public String save(College college){
        collegeService.save(college);
        return "SUCCESS";
    }

    @PostMapping("/delete")
    public String delete(int collegeId){
        collegeService.delete(collegeId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<College> findAll(){
        return collegeService.findAll();
    }

    @GetMapping("/findbyid")
    public College findOne(int collegeId){
        return collegeService.findOne(collegeId);
    }
}
