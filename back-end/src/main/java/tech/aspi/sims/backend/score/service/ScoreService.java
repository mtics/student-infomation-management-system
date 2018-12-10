package tech.aspi.sims.backend.score.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.score.dao.ScoreRepository;
import tech.aspi.sims.backend.score.model.Score;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void deleteById(int score_id) {
        scoreRepository.deleteById(score_id);
    }

    // 查询数据
    @Transactional
    public Iterable<Score> findAllByParams(final Score scoreParams) {

        Iterable<Score> scores = scoreRepository.findAll(new Specification<Score>() {
            @Override
            public Predicate toPredicate(Root<Score> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                // 如果有scoreId，那就把它算上
                if (scoreParams.getScoreId() != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("scoreId").as(String.class), scoreParams.getScoreId()));
                }
                // 如果有scoreName，那就把它算上
                if (scoreParams.getSubjectId() != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("subjectId").as(String.class), scoreParams.getSubjectId() ));
                }

                // 如果有majorId，那就把它算上
                if (scoreParams.getStudentId()  != null && !StringUtils.isEmptyOrWhitespaceOnly(scoreParams.getStudentId())) {
                    predicates.add(criteriaBuilder.equal(root.get("studentId").as(String.class), scoreParams.getStudentId()));
                }

                Predicate[] predicateArray = new Predicate[predicates.size()];

                //criteriaQuery.where(predicates.toArray(new Predicate[]));
                return criteriaBuilder.and(predicates.toArray(predicateArray));
            }
        });

        return scores;
    }
}
