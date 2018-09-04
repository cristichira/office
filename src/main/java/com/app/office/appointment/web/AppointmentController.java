package com.app.office.appointment.web;

import com.app.office.appointment.api.AppointmentService;
import com.app.office.appointment.api.dto.AppointmentDTO;
import com.app.office.appointment.api.dto.AppointmentSearchDTO;
import com.app.office.appointment.api.enumeration.AppointmentState;
import com.app.office.service.api.ServiceService;
import com.app.office.shared.api.dto.NameIdDTO;
import com.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {
    private static final String BASE_URL = "/appointment";

    private final SecurityService securityService;
    private final AppointmentService appointmentService;
    private final ServiceService serviceService;

    @Autowired
    public AppointmentController(SecurityService securityService,
                                 AppointmentService appointmentService,
                                 ServiceService serviceService) {
        this.securityService = securityService;
        this.appointmentService = appointmentService;
        this.serviceService = serviceService;
    }

    @GetMapping(BASE_URL + "/provided")
    public ModelAndView listAsProvider(AppointmentSearchDTO appointmentSearchDTO, ModelAndView mav) {
        appointmentSearchDTO.setServiceIds(serviceService.findServiceIdsOfUserId(securityService.getCurrentUser().getUserId()));
        List<AppointmentDTO> appointments = appointmentService.find(appointmentSearchDTO);

        mav.addObject("appointments", appointments);
        mav.setViewName("appointment/list");
        return mav;
    }

    @GetMapping(BASE_URL + "/scheduled")
    public ModelAndView listAsClient(AppointmentSearchDTO appointmentSearchDTO, ModelAndView mav) {
        appointmentSearchDTO.setUserId(securityService.getCurrentUser().getUserId());
        List<AppointmentDTO> appointments = appointmentService.find(appointmentSearchDTO);

        mav.addObject("appointments", appointments);
        mav.setViewName("appointment/list");
        return mav;
    }

    @GetMapping(BASE_URL + "/{serviceId}/create")
    public ModelAndView getCreate(@PathVariable("serviceId") Long serviceId, ModelAndView mav) {
        AppointmentCommand appointmentCommand = new AppointmentCommand();
        appointmentCommand.setServiceId(serviceId);

        mav.addObject("appointment", appointmentCommand);
        mav.setViewName("appointment/create");
        return mav;
    }

    @PostMapping(BASE_URL + "/create")
    public String create(@Valid @ModelAttribute(value = "appointment") AppointmentCommand appointmentCommand) {
        final AppointmentDTO appointmentDTO = appointmentDTOFromAppointmentCommand(appointmentCommand);
        appointmentService.save(appointmentDTO);
        return "redirect:/appointment/scheduled";
    }

    public static final class AppointmentCommand {
        private Long serviceId;
        private Date date;

        public Long getServiceId() {
            return serviceId;
        }

        public void setServiceId(Long serviceId) {
            this.serviceId = serviceId;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    private AppointmentDTO appointmentDTOFromAppointmentCommand(AppointmentCommand appointmentCommand) {
        final AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setService(new NameIdDTO("", appointmentCommand.getServiceId()));
        appointmentDTO.setScheduledDate(appointmentCommand.getDate());
        appointmentDTO.setUser(securityService.getCurrentUserAsNameIdDTO());
        appointmentDTO.setState(AppointmentState.PENDING);
        return appointmentDTO;
    }
}
