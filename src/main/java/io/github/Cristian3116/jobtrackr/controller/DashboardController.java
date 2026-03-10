package io.github.Cristian3116.jobtrackr.controller;

import io.github.Cristian3116.jobtrackr.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/dashboard")
    public String showDashboard(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        String username = principal.getName();
        Map<String, Long> stats = jobApplicationService.getStatsForUser(username);

        model.addAttribute("totalJobs", stats.get("total"));
        model.addAttribute("pendingJobs", stats.get("pending"));
        model.addAttribute("interviewJobs", stats.get("interview"));

        return "dashboard";
    }
}