package com.example.spring_data.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDtoRepository extends CrudRepository<UserDto, Long> {
}
