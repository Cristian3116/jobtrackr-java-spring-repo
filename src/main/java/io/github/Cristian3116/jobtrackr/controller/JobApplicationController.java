package io.github.Cristian3116.jobtrackr.controller;

import io.github.Cristian3116.jobtrackr.model.JobApplication;
import io.github.Cristian3116.jobtrackr.model.User;
import io.github.Cristian3116.jobtrackr.service.JobApplicationService;
import io.github.Cristian3116.jobtrackr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobApplicationController {

    // All fields are now final so @RequiredArgsConstructor works perfectly
    private final JobApplicationService jobApplicationService;
    private final UserService userService;

    /**
     * Lists only the jobs belonging to the currently logged-in user.
     */
    @GetMapping
    public String list(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<JobApplication> jobs = jobApplicationService.getAllForUser(user);

        model.addAttribute("jobs", jobs);
        return "jobs/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("job", new JobApplication());
        return "jobs/form";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("job") JobApplication job, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());

        job.setUser(user);
        if (job.getAppliedDate() == null) {
            job.setAppliedDate(LocalDate.now());
        }

        jobApplicationService.save(job);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobApplicationService.getById(id));
        return "jobs/form";
    }

    @PostMapping("/edit/{id}")
    public String editJob(@PathVariable Long id, @ModelAttribute("job") JobApplication job) {
        JobApplication existing = jobApplicationService.getById(id);
        if (existing != null) {
            existing.setCompany(job.getCompany());
            existing.setPosition(job.getPosition());
            existing.setStatus(job.getStatus());
            existing.setNotes(job.getNotes());
            jobApplicationService.save(existing);
        }
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobApplicationService.delete(id);
        return "redirect:/jobs";
    }
}