package tech.aspi.sims.backend.student.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.score.model.Score;
import tech.aspi.sims.backend.student.dao.StudentRepository;
import tech.aspi.sims.backend.student.model.Student;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Resource
    private StudentRepository studentRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Student student){
        studentRepository.save(student);
    }

    // 删除数据
    @Transactional
    public void deleteById(String studentId) {
        studentRepository.deleteById(studentId);
    }

    // 删除数据
    @Transactional
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    // 通过部分内容模糊查询数据
    @Transactional
    public Iterable<Student> findAllByParams(final Student studentParams) {

        Iterable<Student> students = studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                // 如果有studentId，那就把它算上
                if (studentParams.getStudentId() != null && !StringUtils.isEmptyOrWhitespaceOnly(studentParams.getStudentId())) {
                    predicates.add(criteriaBuilder.equal(root.get("studentId").as(String.class), studentParams.getStudentId()));
                }
                // 如果有studentName，那就把它算上
                if (studentParams.getStudentName() != null && !StringUtils.isEmptyOrWhitespaceOnly(studentParams.getStudentName())) {
                    predicates.add(criteriaBuilder.like(root.get("studentName").as(String.class), "%"+studentParams.getStudentName()+"%"));
                }

                // 如果有gender，那就把它算上
                if (studentParams.getGender() != null && !StringUtils.isEmptyOrWhitespaceOnly(studentParams.getGender())) {
                    predicates.add(criteriaBuilder.equal(root.get("gender").as(String.class), studentParams.getGender()));
                }

                // 如果有email，那就把它算上
                if (studentParams.getEmail() != null && !StringUtils.isEmptyOrWhitespaceOnly(studentParams.getEmail())) {
                    predicates.add(criteriaBuilder.equal(root.get("email").as(String.class), studentParams.getEmail()));
                }

                // 如果有majorId，那就把它算上
                if (studentParams.getMajorId() != 0) {
                    predicates.add(criteriaBuilder.equal(root.get("majorId").as(String.class), studentParams.getMajorId()));
                }

                Predicate[] predicateArray = new Predicate[predicates.size()];

                //criteriaQuery.where(predicates.toArray(new Predicate[]));
                return criteriaBuilder.and(predicates.toArray(predicateArray));
            }
        });

        return students;
    }

}
