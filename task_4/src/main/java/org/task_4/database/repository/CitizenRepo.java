package org.task_4.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.task_4.database.model.Citizen;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen, Long> {
    Optional<Citizen> findById(Long id);

    @Query(value = "SELECT c FROM Citizen c WHERE(:lastName is null or c.lastName = :lastName) " +
            "AND (:firstName is null or c.firstName = :firstName) " +
            "AND (:middleName is null or c.middleName = :middleName) " +
            "AND (:birthDate is null or c.birthDate = :birthDate)"
    )
    List<Citizen> findListOfCitizensByOptionalParams(@Param("lastName")String lastName,
                                                     @Param("firstName")String firstName,
                                                     @Param("middleName")String middleName,
                                                     @Param("birthDate")String birthDate
    );
}
