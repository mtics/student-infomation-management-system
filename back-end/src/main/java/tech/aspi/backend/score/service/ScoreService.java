package tech.aspi.sims.score.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.score.dao.ScoreRepository;
import tech.aspi.sims.score.model.Score;

import javax.annotation.Resource;

@Service
public class ScoreService {

    @Resource
    private ScoreRepository scoreRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Score score){
        scoreRepository.save(score);
    }

    // 删除数据
    @Transactional
    public void delete(Long score_id) {
        scoreRepository.delete(score_id);
    }

    // 查询数据
    @Transactional
    public Iterable<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Transactional
    public Score findOne(long score_id){
        return scoreRepository.findOne(score_id);
    }
}
