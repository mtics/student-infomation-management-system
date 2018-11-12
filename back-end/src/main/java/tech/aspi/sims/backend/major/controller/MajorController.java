package tech.aspi.sims.backend.major.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.major.model.Major;
import tech.aspi.sims.backend.major.service.MajorService;

import javax.annotation.Resource;
import java.util.Optional;


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

    @PostMapping("/deletebyid")
    public String deleteById(int majorId){
        majorService.deleteById(majorId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Major> findAll(){
        return majorService.findAll();
    }

    @GetMapping("/findbyid")
    public Optional<Major> findById(int majorId){
        return majorService.findById(majorId);
    }
}
