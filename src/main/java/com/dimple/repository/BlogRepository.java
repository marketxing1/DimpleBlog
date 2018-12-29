package com.dimple.repository;

import com.dimple.bean.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : BlogRepository
 * @description :
 * @date : 12/26/18 19:09
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>, JpaSpecificationExecutor<Blog> {

    @Query(value = "select * from blog where if(:title is not null,title like concat('%',:title,'%'),1=1 )" +
            "and if(:startTime is not null,update_time >= :startTime,1=1)" +
            "and if (:endTime is not null ,update_time <= :endTime,1=1)" +
            "and if(:status is not null ,status=:status,1=1) ", nativeQuery = true)
    List<Blog> find(@Param("title") String title, Date startTime, Date endTime, Integer status);

    @Query(value = "select (select count(*) from blog where status=1)as  publish," +
            "(select count(*) from blog where status=2)as drafts," +
            "(select count(*) from  blog where status=3) as dustbin", nativeQuery = true)
    Map<String, Integer> getAllBlogStatusCount();

    Integer countByStatus(Integer status);

    List<Blog> findAllByCategoryId(Integer id);

    List<Blog> findAllBySupportEquals(Boolean support);


}