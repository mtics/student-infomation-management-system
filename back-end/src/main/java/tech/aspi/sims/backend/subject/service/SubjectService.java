package tech.aspi.sims.backend.subject.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.subject.model.Subject;
import tech.aspi.sims.backend.subject.dao.SubjectRepository;
import tech.aspi.sims.backend.subject.model.Subject;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Resource
    private SubjectRepository subjectRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    // 删除数据
    @Transactional
    public void deleteById(int subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    // 通过部分内容模糊查询数据
    @Transactional
    public Iterable<Subject> findAllByParams(final Subject subjectParams) {

        Iterable<Subject> subjects = subjectRepository.findAll(new Specification<Subject>() {
            @Override
            public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                // 如果有subjectId，那就把它算上
                if (subjectParams.getSubjectId() != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("subjectId").as(String.class), subjectParams.getSubjectId()));
                }
                // 如果有subjectName，那就把它算上
                if (subjectParams.getSubjectName() != null && !StringUtils.isEmptyOrWhitespaceOnly(subjectParams.getSubjectName())) {
                    predicates.add(criteriaBuilder.like(root.get("subjectName").as(String.class), "%" + subjectParams.getSubjectName() + "%"));
                }

                // 如果有majorId，那就把它算上
                if (subjectParams.getMajorId() != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("majorId").as(String.class), subjectParams.getMajorId()));
                }

                Predicate[] predicateArray = new Predicate[predicates.size()];

                //criteriaQuery.where(predicates.toArray(new Predicate[]));
                return criteriaBuilder.and(predicates.toArray(predicateArray));
            }
        });

        return subjects;
    }
}
