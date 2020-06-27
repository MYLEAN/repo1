package com.zq.blog.dao;

import com.zq.blog.po.Blog;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.reconmmend=true ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByquery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.view=b.view+1 where b.id=?1")
    Integer updateView(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by " +
            "function('date_format',b.updateTime,'%Y') order by year asc")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y')=?1 ")
    List<Blog> findByYear(String year);
}
