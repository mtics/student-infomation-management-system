package tech.aspi.sims.backend.score.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aspi.sims.backend.score.model.Score;
import tech.aspi.sims.backend.score.service.ScoreService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


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

    @PostMapping("/deletebyid")
    public String deleteById(int score_id){
        scoreService.deleteById(score_id);
        return "SUCCESS";
    }

    @GetMapping("/findall")
    public Iterable<Score> findAllByParams(Score scoreParams){
        return scoreService.findAllByParams(scoreParams);
    }
}
