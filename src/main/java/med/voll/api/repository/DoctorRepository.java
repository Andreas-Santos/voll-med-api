package med.voll.api.repository;

import med.voll.api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(
        """
            SELECT 
                d FROM Doctor d
            WHERE 
                d.active = true
                AND d.id NOT IN (
                    SELECT 
                        a.doctor.id 
                    FROM 
                        Appointment a
                    WHERE 
                            a.doctor.id = d.id
                        AND a.date < :endDate
                        AND a.date > :startDate
                )
        """)
    List<Doctor> findFirstAvailableDoctor(LocalDateTime startDate, LocalDateTime endDate);
}
