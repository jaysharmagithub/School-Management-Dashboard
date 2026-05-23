//package com.educloud.assessment.controller;
//
//import com.educloud.assessment.dto.ParentDTO;
//import com.educloud.assessment.dto.StudentDTO;
//import com.educloud.assessment.dto.TeacherDTO;
//import com.educloud.assessment.entity.Parent;
//import com.educloud.assessment.entity.Student;
//import com.educloud.assessment.entity.Teacher;
//import com.educloud.assessment.request.FieldMeta;
//import com.educloud.assessment.service.ParentService;
//import com.educloud.assessment.service.StudentService;
//import com.educloud.assessment.service.TeacherService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.lang.reflect.Field;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Controller
//public class HomeController {
//
//    private final ParentService parentService;
//    private final TeacherService teacherService;
//    private final StudentService studentService;
//
//    @GetMapping("/test")
//    public String testPage(Model model) {
//        model.addAttribute("message", "Thymeleaf is working 🚀");
//        return "test"; // refers to test.html
//    }
//
//    @GetMapping("/dashboard")
//    public String dashboard(Model model) throws SQLException {
//
//        // STUDENTS
////        List<StudentDTO> students = studentService.getStudents();
//        List<FieldMeta> studentCols = getFieldMeta(StudentDTO.class);
//
////        model.addAttribute("studentData", students);
//        model.addAttribute("studentCols", studentCols);
//
////        // TEACHERS
////        List<TeacherDTO> teachers = teacherService.findAll();
////        List<FieldMeta> teacherCols = getFieldMeta(TeacherDTO.class);
////        model.addAttribute("teacherData", teachers);
////        model.addAttribute("teacherCols", teacherCols);
////
////        // PARENTS
////        List<ParentDTO> parents = parentService.getParents().stream().map(p -> new ParentDTO());
////        List<FieldMeta> parentCols = getFieldMeta(ParentDTO.class);
////        model.addAttribute("parentData", parents);
////        model.addAttribute("parentCols", parentCols);
//
//        return "dashboard";
//    }
//
//    private List<FieldMeta> getFieldMeta(Class<?> dtoClass) {
//        List<FieldMeta> metaList = new ArrayList<>();
//        for (Field field : dtoClass.getDeclaredFields()) {
//            field.setAccessible(true); // in case fields are private
//            String fieldName = field.getName();
//            String label = Arrays.stream(fieldName.split("(?=[A-Z])"))
//                    .map(String::toUpperCase)
//                    .collect(Collectors.joining(" "));
//            metaList.add(new FieldMeta(fieldName, label));
//        }
//        return metaList;
//    }
//
//    @GetMapping("/api/student")
//    public String students(Model model) throws SQLException {
////        model.addAttribute("data", studentService.getStudents());
//        return "fragments/table :: tableFragment";
//    }
//
//    @GetMapping("/api/teacher")
//    public String teachers(Model model) throws SQLException {
////        model.addAttribute("data", teacherService.getTeachers());
//        return "fragments/table :: tableFragment";
//    }
//
//    @GetMapping("/api/parent")
//    public String parents(Model model) throws SQLException {
////        model.addAttribute("data", parentService.getParents());
//        return "fragments/table :: tableFragment";
//    }
//
//    @GetMapping("/form")
//    public String getForm(@RequestParam String type, Model model) {
//
//        Class<?> clazz;
//        switch (type.toLowerCase()) {
//            case "student": clazz = StudentDTO.class; break;
//            case "teacher": clazz = TeacherDTO.class; break;
//            case "parent": clazz = ParentDTO.class; break;
//            default: throw new RuntimeException("Invalid type");
//        }
//
//        List<FieldMeta> fields = new ArrayList<>();
//        for (Field field : clazz.getDeclaredFields()) {
//            fields.add(new FieldMeta(field.getName(), mapToHtmlType(field.getType())));
//        }
//
//        model.addAttribute("fields", fields);
//        model.addAttribute("type", type);
//
//        return "dynamic-form";  // Thymeleaf template
//    }
//
//    // --- Save form ---
//    @PostMapping("/save")
//    public String save(@RequestParam Map<String, String> data) throws SQLException {
//
//        String type = data.get("type").toLowerCase();
//
//        switch (type) {
//            case "student":
//                Student student = new Student();
//                student.setFirstName(data.get("firstName"));
//                student.setRollNumber(data.get("rollNo"));
//                studentService.save(student);
//                break;
//
//            case "teacher":
//                Teacher teacher = new Teacher();
//                teacher.setFirstName(data.get("firstName"));
//                teacher.setSubject(data.get("subject"));
//                teacherService.save(teacher);
//                break;
//
//            case "parent":
//                Parent parent = new Parent();
//                parent.setFirstName(data.get("firstName"));
////                parent.setChildName(data.get("childName"));
//                parentService.save(parent);
//                break;
//
//            default:
//                throw new RuntimeException("Invalid type");
//        }
//
//        return "redirect:/dashboard";
//    }
//
//    // Map Java type → HTML input type
//    private String mapToHtmlType(Class<?> clazz) {
//        if (clazz == String.class) return "text";
//        if (clazz == int.class || clazz == Integer.class) return "number";
//        return "text";
//    }
//}
//
//
//
