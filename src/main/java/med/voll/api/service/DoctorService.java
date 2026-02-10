package med.voll.api.service;

import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.request.DoctorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public void registerDoctor(DoctorRequest request) {
        Doctor doctor = new Doctor(request);

        doctorRepository.save(doctor);
    }
}
