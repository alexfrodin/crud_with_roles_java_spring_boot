package se.sti.javasti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import se.sti.javasti.model.Student;
import se.sti.javasti.services.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/")
    public String homePageView(Model model) {
        List<Student> studentList = studentService.listAllStudents();
        model.addAttribute("studentList", studentList);
        return "index";
    }

    @RequestMapping("/new")
    public String registerNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute(student);
        return "new_student";
    }

    @RequestMapping("/save")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.createStudent(student);
        return "redirect:/";
    }

    @RequestMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable(name = "studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/";
    }

    @RequestMapping("/edit/{studentId}")
    public ModelAndView editStudent(@PathVariable(name = "studentId") Long studentId) {
        ModelAndView modelAndView = new ModelAndView("edit_student");
        Student student = studentService.getStudent(studentId);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

}
