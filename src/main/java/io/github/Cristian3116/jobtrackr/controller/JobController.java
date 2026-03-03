package io.github.Cristian3116.jobtrackr.controller;

import io.github.Cristian3116.jobtrackr.model.Job;
import io.github.Cristian3116.jobtrackr.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("jobs", service.findAll());
        return "jobs/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobs/form";
    }

    @PostMapping
    public String save(@ModelAttribute Job job) {
        service.save(job);
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/jobs";
    }
}