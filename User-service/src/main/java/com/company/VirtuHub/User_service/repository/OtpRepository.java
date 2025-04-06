package com.company.VirtuHub.User_service.repository;

import com.company.VirtuHub.User_service.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity,Long> {


    Optional<OtpEntity> findByEmail(String email);
}
