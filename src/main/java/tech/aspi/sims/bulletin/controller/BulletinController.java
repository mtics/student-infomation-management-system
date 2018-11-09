package tech.aspi.sims.bulletin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.bulletin.model.Bulletin;
import tech.aspi.sims.bulletin.service.BulletinService;

import javax.annotation.Resource;


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

    @PostMapping("/delete")
    public String delete(int bull_id){
        bulletinService.delete(bull_id);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Bulletin> findAll(){
        return bulletinService.findAll();
    }

    @GetMapping("/findbyid")
    public Bulletin findOne(int bull_id){
        return bulletinService.findOne(bull_id);
    }
}
