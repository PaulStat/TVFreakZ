/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvfreakz.model.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
