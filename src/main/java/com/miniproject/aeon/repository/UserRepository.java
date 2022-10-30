package com.miniproject.aeon.repository;

import com.miniproject.aeon.domain.dao.Users;
import org.apache.catalina.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<Users,Long> {

    Users   getDistinctTopByUsername(String username);
    Boolean existsByUsername(String username);
}
