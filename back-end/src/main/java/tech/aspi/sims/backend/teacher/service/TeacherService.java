package tech.aspi.sims.backend.teacher.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.teacher.dao.TeacherRepository;
import tech.aspi.sims.backend.teacher.model.Teacher;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Teacher teacher){
        teacherRepository.save(teacher);
    }

    // 删除数据
//    @Transactional
//    public void deleteById(String teacherId) {
//        teacherRepository.deleteById(teacherId);
//    }

    @Transactional
    public void deleteById(String teacherId){
        teacherRepository.deleteById(teacherId);
    }

    // 通过部分内容模糊查询数据
    @Transactional
    public Iterable<Teacher> findAllByParams(final Teacher teacherParams) {

        Iterable<Teacher> teachers = teacherRepository.findAll(new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                // 如果有teacherId，那就把它算上
                if (teacherParams.getTeacherId() != null && !StringUtils.isEmptyOrWhitespaceOnly(teacherParams.getTeacherId())) {
                    predicates.add(criteriaBuilder.equal(root.get("teacherId").as(String.class), teacherParams.getTeacherId()));
                }
                // 如果有teacherName，那就把它算上
                if (teacherParams.getTeacherName() != null && !StringUtils.isEmptyOrWhitespaceOnly(teacherParams.getTeacherName())) {
                    predicates.add(criteriaBuilder.like(root.get("teacherName").as(String.class), "%" + (String) teacherParams.getTeacherName() + "%"));
                }

                // 如果有gender，那就把它算上
                if (teacherParams.getGender() != null && !StringUtils.isEmptyOrWhitespaceOnly(teacherParams.getGender())) {
                    predicates.add(criteriaBuilder.like(root.get("gender").as(String.class), "%" + (String) teacherParams.getGender() + "%"));
                }

                // 如果有email，那就把它算上
                if (teacherParams.getEmail() != null && !StringUtils.isEmptyOrWhitespaceOnly(teacherParams.getEmail())) {
                    predicates.add(criteriaBuilder.equal(root.get("email").as(String.class), teacherParams.getEmail()));
                }

                // 如果有collegeId，那就把它算上
                if (teacherParams.getCollegeId() != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("collegeId").as(String.class), teacherParams.getCollegeId()));
                }

                Predicate[] predicateArray = new Predicate[predicates.size()];

                //criteriaQuery.where(predicates.toArray(new Predicate[]));
                return criteriaBuilder.and(predicates.toArray(predicateArray));
            }
        });

        return teachers;
    }
}
