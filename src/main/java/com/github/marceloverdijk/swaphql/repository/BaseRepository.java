package com.github.marceloverdijk.swaphql.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

}
