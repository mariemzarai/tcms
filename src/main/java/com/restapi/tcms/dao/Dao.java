package com.restapi.tcms.dao;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
      Optional<T> getById(Long id);
      List<T> getAll();
      Optional<T> create(T t);
      void delete(long id) throws EntityNotFoundException;
//      void  update(T t);
}
