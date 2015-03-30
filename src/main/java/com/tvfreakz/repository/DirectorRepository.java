/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvfreakz.model.entity.Director;

@Repository("directorRepository")
public interface DirectorRepository extends JpaRepository<Director, Long> {

    Director findByDirectorId(Long directorId);

}
