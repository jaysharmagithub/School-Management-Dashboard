package com.educloud.assessment.service;

import com.educloud.assessment.dto.ParentDTO;
import com.educloud.assessment.dto.StudentDTO;
import com.educloud.assessment.entity.Student;
import com.educloud.assessment.exceptions.ParentNotFoundException;
import com.educloud.assessment.exceptions.StudentAlreadyExistException;
import com.educloud.assessment.exceptions.StudentNotFoundException;
import com.educloud.assessment.repository.StudentRepository;
import com.educloud.assessment.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    public PageResponse<StudentDTO> getStudents(int page, int size, String sortBy, String sortDir) throws SQLException {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentDTO> dtoList = studentPage.getContent().stream()
                .map(this::mapToDTO)
                .toList();

        return PageResponse.<StudentDTO>builder()
                .content(dtoList)
                .page(studentPage.getNumber())
                .size(studentPage.getSize())
                .totalElements(studentPage.getTotalElements())
                .totalPages(studentPage.getTotalPages())
                .last(studentPage.isLast())
                .build();
    }

    private StudentDTO mapToDTO(Student student) {
        return new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getRollNumber(),
                student.getDateOfBirth(),
                student.getGender(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getGrade(),
                student.getSection(),
                getParentName(student),   // derived
                getRelation(student)      // derived
        );
    }

    // ⚡ Handle relationships properly
    private String getParentName(Student student) {
        if (student.getParents() != null && !student.getParents().isEmpty()) {
            return student.getParents().get(0).getFirstName();
        }
        return "N/A";
    }

    private String getRelation(Student student) {
        if (student.getParents() != null && !student.getParents().isEmpty()) {
            return student.getParents().get(0).getRelation();
        }
        return "N/A";
    }

//    public List<StudentDTO> getStudents() throws SQLException{
//        return Arrays.asList(new StudentDTO("Aarav", "Sharma", "S001", LocalDate.of(2012, 5, 10),
//                        "MALE", "aarav.sharma@example.com", "9876543210",
//                        "123 MG Road, Pune", "6", "A", "Ramesh Sharma", "Father"),
//
//                new StudentDTO("Diya", "Patel", "S002", LocalDate.of(2011, 8, 22),
//                        "FEMALE", "diya.patel@example.com", "9123456780",
//                        "45 Flower Street, Mumbai", "7", "B", "Sanjay Patel", "Father"),
//
//                new StudentDTO("Rohan", "Gupta", "S003", LocalDate.of(2013, 1, 5),
//                        "MALE", "rohan.gupta@example.com", "9988776655",
//                        "12 Lakeview Apartments, Delhi", "5", "C", "Meera Gupta", "Mother"),
//
//                new StudentDTO("Ananya", "Kumar", "S004", LocalDate.of(2010, 12, 30),
//                        "FEMALE", "ananya.kumar@example.com", "9876123450",
//                        "78 Park Lane, Bangalore", "8", "A", "Vikram Kumar", "Father"),
//
//                new StudentDTO("Vivaan", "Singh", "S005", LocalDate.of(2012, 3, 15),
//                        "MALE", "vivaan.singh@example.com", "9876501234",
//                        "22 Rosewood, Chennai", "6", "B", "Anjali Singh", "Mother"),
//
//                new StudentDTO("Isha", "Mehta", "S006", LocalDate.of(2011, 7, 18),
//                        "FEMALE", "isha.mehta@example.com", "9012345678",
//                        "56 Green Valley, Hyderabad", "7", "C", "Raj Mehta", "Father"),
//
//                new StudentDTO("Arjun", "Desai", "S007", LocalDate.of(2010, 11, 2),
//                        "MALE", "arjun.desai@example.com", "9876541122",
//                        "11 Ocean Drive, Goa", "8", "A", "Sunita Desai", "Mother"),
//
//                new StudentDTO("Saanvi", "Chopra", "S008", LocalDate.of(2013, 4, 9),
//                        "FEMALE", "saanvi.chopra@example.com", "9123459988",
//                        "34 Hilltop, Jaipur", "5", "B", "Anil Chopra", "Father"),
//
//                new StudentDTO("Kabir", "Rao", "S009", LocalDate.of(2012, 9, 27),
//                        "MALE", "kabir.rao@example.com", "9988771122",
//                        "90 Maple Street, Kolkata", "6", "C", "Priya Rao", "Mother"),
//
//                new StudentDTO("Tara", "Verma", "S010", LocalDate.of(2011, 2, 14),
//                        "FEMALE", "tara.verma@example.com", "9012348899",
//                        "15 Sunset Blvd, Lucknow", "7", "A", "Rakesh Verma", "Father")
//        );
////        return studentRepository.findAll();
//
//    }

    public void save(Student student) throws SQLException  {
        studentRepository.save(student);
    }

    public void updateStudent(Long id,StudentDTO studentDTO) throws SQLException, StudentNotFoundException {
       Student s =  studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found by given Id"));
       Student studentToSaved = StudentDTO.toStudent(studentDTO);
       studentToSaved.setId(s.getId());
       studentRepository.save(studentToSaved);
    }

    public void saveStudent(StudentDTO studentDTO) throws SQLException, StudentAlreadyExistException{

        if(studentRepository.existsByRollNumber(studentDTO.getRollNumber())) throw new StudentAlreadyExistException("Student already Present By given roll No");
        studentRepository.save(StudentDTO.toStudent(studentDTO));
    }

    @Override
    public void deleteStudent(Long id) {
     studentRepository.deleteById(id);
    }
}
