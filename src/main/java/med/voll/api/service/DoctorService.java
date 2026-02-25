package med.voll.api.service;

import med.voll.api.dto.Doctor.DoctorDTO;
import med.voll.api.dto.Doctor.DoctorDetailDTO;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.request.Doctor.DoctorRequest;
import med.voll.api.request.Doctor.UpdateDoctorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public void registerDoctor(DoctorRequest request) {
        Doctor doctor = new Doctor(request);

        doctorRepository.save(doctor);
    }

    public List<DoctorDetailDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();

        List<DoctorDetailDTO> doctorDTOs = doctors.stream()
                .map(DoctorDetailDTO::new).toList();

        return doctorDTOs;
    }

    public List<DoctorDTO> getTop10DoctorsOrderedByNameAsc() {
        List<Doctor> doctors = doctorRepository.findTop10ByOrderByNameAsc();

        List<DoctorDTO> doctorDTOS = doctors.stream()
                .map(DoctorDTO::new).toList();

        return doctorDTOS;
    }

    public void updateDoctor(Long id, UpdateDoctorRequest request) throws Exception {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        if(doctorOptional.isEmpty()) {
            throw new Exception("Não existe doutor com esse id!");
        }

        Doctor doctor = doctorOptional.get();

        doctor.setName(request.name());
        doctor.setPhone(request.phone());
        doctor.setAddress(request.address());

        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) throws Exception {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        if(doctorOptional.isEmpty()) {
            throw new Exception("Não existe doutor com esse id!");
        }

        Doctor doctor = doctorOptional.get();

        doctorRepository.delete(doctor);
    }
}
