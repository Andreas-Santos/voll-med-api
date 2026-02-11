package med.voll.api.service;

import med.voll.api.dto.DoctorDTO;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.request.DoctorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public void registerDoctor(DoctorRequest request) {
        Doctor doctor = new Doctor(request);

        doctorRepository.save(doctor);
    }

    public List<DoctorDTO> getTop10DoctorsOrderedByNameAsc() {
        List<Doctor> doctors = doctorRepository.findTop10ByOrderByNameAsc();

        List<DoctorDTO> doctorDTOS = doctors.stream()
                .map(d -> new DoctorDTO(d.getName(), d.getEmail(), d.getCrm(), d.getSpecialty()))
                .collect(Collectors.toList());

        return doctorDTOS;
    }
}
