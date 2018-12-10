package tech.aspi.sims.backend.bulletin.service;

import com.mysql.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.bulletin.dao.BulletinRepository;
import tech.aspi.sims.backend.bulletin.model.Bulletin;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BulletinService {

    @Resource
    private BulletinRepository bulletinRepository;

    /**
     * save, update, delete方法需要绑定事务
     * 使用@Transactional进行事务绑定
     */

    // 保存数据
    @Transactional
    public void save(Bulletin bulletin){

        bulletin.setPublishedDate(new Date());

        bulletinRepository.save(bulletin);
    }

    // 删除数据
    @Transactional
    public void deleteById(int bull_id) {
        bulletinRepository.deleteById(bull_id);
    }

    // 通过部分内容模糊查询数据
    @Transactional
    public List<Bulletin> findAllByParams(final Bulletin bulletinParams) {

        List<Bulletin> bulletins = bulletinRepository.findAll(new Specification<Bulletin>(){
            @Override
            public Predicate toPredicate(Root<Bulletin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                // 如果有bulletinId，那就把它算上
                if(bulletinParams.getBulletinId()!=0){
                    predicates.add(criteriaBuilder.equal(root.get("bulletinId").as(String.class), bulletinParams.getBulletinId()));
                }
                // 如果有bulletinTitle，那就把它算上
                if(bulletinParams.getBulletinTitle()!=null&&!StringUtils.isEmptyOrWhitespaceOnly(bulletinParams.getBulletinTitle())){
                    predicates.add(criteriaBuilder.like(root.get("bulletinTitle").as(String.class), "%"+(String)bulletinParams.getBulletinTitle()+"%"));
                }

                // 如果有bulletinContext，那就把它算上
                if(bulletinParams.getBulletinContext()!=null&&!StringUtils.isEmptyOrWhitespaceOnly(bulletinParams.getBulletinContext())){
                    predicates.add(criteriaBuilder.like(root.get("bulletinContext").as(String.class), "%"+(String)bulletinParams.getBulletinContext()+"%"));
                }

                // 如果有userId，那就把它算上
                if(bulletinParams.getUserId()!=null&&!StringUtils.isEmptyOrWhitespaceOnly(bulletinParams.getUserId())){
                    predicates.add(criteriaBuilder.equal(root.get("userId").as(String .class), bulletinParams.getUserId()));
                }

                Predicate[] predicateArray = new Predicate[predicates.size()];

                //criteriaQuery.where(predicates.toArray(new Predicate[]));
                return criteriaBuilder.and(predicates.toArray(predicateArray));
            }
        });

        return bulletins;
    }
}
