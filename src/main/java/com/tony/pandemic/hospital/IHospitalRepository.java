package com.tony.pandemic.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IHospitalRepository extends JpaRepository<Hospital, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Hospital h SET h.percentageOfOccupation=?1 WHERE h.id=?2")
    void updateOccupation(Double PercentageOfOccupation, long id);

}
