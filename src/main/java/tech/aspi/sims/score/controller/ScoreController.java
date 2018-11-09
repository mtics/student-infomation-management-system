package tech.aspi.sims.score.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.score.model.Score;
import tech.aspi.sims.score.service.ScoreService;

import javax.annotation.Resource;


@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    //保存数据
    @PostMapping("/save")
    public String save(Score score){
        scoreService.save(score);
        return "SUCCESS";
    }

    @PostMapping("/delete")
    public String delete(Long score_id){
        scoreService.delete(score_id);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Score> findAll(){
        return scoreService.findAll();
    }

    @GetMapping("/findbyid")
    public Score findOne(long score_id){
        return scoreService.findOne(score_id);
    }
}
