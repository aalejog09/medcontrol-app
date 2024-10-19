package com.hmvss.api.persistence.repository.Function;

import com.hmvss.api.persistence.model.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFunctionRepository extends JpaRepository<Function, Long> {

}
