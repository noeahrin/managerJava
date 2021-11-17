package com.git.manager.itemlsit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemlistRepository extends JpaRepository<Itemlist, Long> {

}
