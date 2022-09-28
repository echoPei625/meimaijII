package com.fang.meimaijII.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fang.meimaijII.entities.MailLog;

@Repository
public interface MailLogRepository extends JpaRepository<MailLog, Long>{
}
