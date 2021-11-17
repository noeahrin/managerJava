package com.git.manager.applying;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyingRepository extends JpaRepository<Applying, Long> {

}