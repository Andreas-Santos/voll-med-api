package med.voll.api.service;

import med.voll.api.dto.Doctor.DoctorDTO;
import med.voll.api.dto.Doctor.DoctorDetailDTO;
import med.voll.api.exception.DoctorInactiveException;
import med.voll.api.exception.DoctorNotFoundException;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.dto.Doctor.DoctorRequest;
import med.voll.api.dto.Doctor.UpdateDoctorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor registerDoctor(DoctorRequest request) {
        Doctor doctor = new Doctor(request);

        doctorRepository.save(doctor);

        return doctor;
    }

    public Page<DoctorDTO> getActiveDoctors(Pageable pagination) {
        Page<Doctor> doctors = doctorRepository.findDoctorByActiveTrue(pagination);

        return doctors.map(DoctorDTO::new);
    }

    public Page<DoctorDTO> getAllDoctors(Pageable pagination) {
        Page<Doctor> doctors = doctorRepository.findAll(pagination);

        return doctors.map(DoctorDTO::new);
    }

    public DoctorDetailDTO getDoctorDetail(Long id) {
        Doctor doctor = doctorRepository
                .findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Não existe doutor com esse id!"));

        return new DoctorDetailDTO(doctor);
    }

    public Doctor updateDoctor(Long id, UpdateDoctorRequest request) throws Exception {
        Doctor doctor = doctorRepository
                .findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Não existe médico com esse id!"));

        if(doctor.getActive() == false) {
            throw new DoctorInactiveException("Esse médico já foi excluído e não pode ser alterado!");
        }

        doctor.updateDoctor(request);

        doctorRepository.save(doctor);

        return doctor;
    }

    public void deleteDoctor(Long id) throws Exception {
        Doctor doctor = doctorRepository
                .findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Não existe doutor com esse id!"));

        if(doctor.getActive() == false) {
            throw new DoctorInactiveException("Esse médico já foi excluído!");
        }

        doctor.inactivateDoctor();
    }
}
