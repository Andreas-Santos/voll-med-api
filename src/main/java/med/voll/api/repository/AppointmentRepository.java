package med.voll.api.repository;

import med.voll.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(
        """
            SELECT 
                COUNT(a) > 0
            FROM 
                Appointment a
            WHERE 
                a.doctor.id = :doctorId
                AND a.date < :endDate
                AND a.date > :startDate
        """)
    boolean doctorHasAppointment(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    @Query(
        """
            SELECT
                COUNT(a) > 0
            FROM
                Appointment a
            WHERE
                a.patient.id = :patientId
            AND a.date > :startOfDay
            AND a.date < :endOfDay
        """
    )
    boolean patientHasAppointmentThatDay(Long patientId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
