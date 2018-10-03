package com.pleshkov.data.repozitory;

import com.pleshkov.data.entity.LoadInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pleshkov on 03.10.2018.
 */
@Repository
public interface LoadInfoRepository extends CrudRepository<LoadInfo, Long> {
    List<LoadInfo> findByFileName(String fileName);

}
