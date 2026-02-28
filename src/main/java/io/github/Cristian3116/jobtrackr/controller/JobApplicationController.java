package io.github.Cristian3116.jobtrackr.controller;

import io.github.Cristian3116.jobtrackr.model.JobApplication;
import io.github.Cristian3116.jobtrackr.model.User;
import io.github.Cristian3116.jobtrackr.service.JobApplicationService;
import io.github.Cristian3116.jobtrackr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobApplicationController {

    private final JobApplicationService jobService;
    private final UserService userService;

    @GetMapping
    public String listJobs(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("jobs", jobService.getAllForUser(user));
        return "jobs/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("job", new JobApplication());
        return "jobs/add";
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute JobApplication job, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        job.setUser(user);
        job.setAppliedDate(LocalDate.now());
        jobService.save(job);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getById(id));
        return "jobs/edit";
    }

    @PostMapping("/edit/{id}")
    public String editJob(@PathVariable Long id, @ModelAttribute JobApplication job) {
        JobApplication existing = jobService.getById(id);
        if (existing != null) {
            existing.setCompany(job.getCompany());
            existing.setPosition(job.getPosition());
            existing.setStatus(job.getStatus());
            existing.setNotes(job.getNotes());
            jobService.save(existing);
        }
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return "redirect:/jobs";
    }
}