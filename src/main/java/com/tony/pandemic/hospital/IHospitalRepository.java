package com.tony.pandemic.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHospitalRepository extends JpaRepository<Hospital, Long> {

}
