package com.fengsong97.spring.demo.repositories;

import com.fengsong97.spring.demo.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/25 17:29
 **/

public interface BookRepository extends JpaRepository<BookEntity, Serializable> {

    @Query(value = " from BookEntity b where b.enable= :enable")
    Page<BookEntity> findQuery(@Param("enable") Boolean enable, Pageable pageable);
}
