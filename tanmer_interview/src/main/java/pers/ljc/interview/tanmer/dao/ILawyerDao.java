package pers.ljc.interview.tanmer.dao;

import org.springframework.stereotype.Repository;
import pers.ljc.interview.tanmer.domain.Lawyer;

import java.util.List;

/**
 * Created by liujicheng on 2019/5/28.
 */
@Repository
public interface ILawyerDao {
    List<Lawyer>  selectAll();
    void insert(Lawyer domain);
}
