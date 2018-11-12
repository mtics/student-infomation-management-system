package tech.aspi.sims.backend.college.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.college.model.College;
import tech.aspi.sims.backend.college.service.CollegeService;

import javax.annotation.Resource;
import java.util.Optional;


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

    @PostMapping("/deletebyid")
    public String deleteById(int collegeId){
        collegeService.deleteById(collegeId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<College> findAll(){
        return collegeService.findAll();
    }

    @GetMapping("/findbyid")
    public Optional<College> findById(int collegeId){
        return collegeService.findById(collegeId);
    }
}
