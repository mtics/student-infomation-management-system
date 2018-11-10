package tech.aspi.sims.major.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.major.model.Major;
import tech.aspi.sims.major.service.MajorService;

import javax.annotation.Resource;


@RestController
@RequestMapping("/major")
public class MajorController {

    @Resource
    private MajorService majorService;

    //保存数据
    @PostMapping("/save")
    public String save(Major major){
        majorService.save(major);
        return "SUCCESS";
    }

    @PostMapping("/delete")
    public String delete(int majorId){
        majorService.delete(majorId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Major> findAll(){
        return majorService.findAll();
    }

    @GetMapping("/findbyid")
    public Major findOne(int majorId){
        return majorService.findOne(majorId);
    }
}
