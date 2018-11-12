package tech.aspi.sims.backend.bulletin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.aspi.sims.backend.bulletin.dao.BulletinRepository;
import tech.aspi.sims.backend.bulletin.model.Bulletin;

import javax.annotation.Resource;
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
        bulletinRepository.save(bulletin);
    }

    // 删除数据
    @Transactional
    public void deleteById(int bull_id) {
        bulletinRepository.deleteById(bull_id);
    }

    // 查询数据
    @Transactional
    public Iterable<Bulletin> findAll() {
        return bulletinRepository.findAll();
    }

    @Transactional
    public Optional<Bulletin> findById(int bull_id){
        return bulletinRepository.findById(bull_id);
    }
}
