package tech.aspi.sims.backend.bulletin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.bulletin.model.Bulletin;
import tech.aspi.sims.backend.bulletin.service.BulletinService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bulletin")
public class BulletinController {

    @Resource
    private BulletinService bulletinService;

    //保存数据
    @PostMapping("/save")
    public String save(Bulletin bulletin){
        bulletinService.save(bulletin);
        return "SUCCESS";
    }

    @PostMapping("/deletebyid")
    public String deleteById(int bullId){
        bulletinService.deleteById(bullId);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public List<Bulletin> findAllByParams(Bulletin bulletin){
        return bulletinService.findAllByParams(bulletin);
    }
}
