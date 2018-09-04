package com.app.office.service.web;

import com.app.office.service.api.ServiceService;
import com.app.office.service.api.dto.ServiceDTO;
import com.app.office.service.api.dto.ServiceSearchDTO;
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
import java.util.List;

@Controller
public class ServiceController {
    private static final String BASE_URL = "/service";

    private final ServiceService serviceService;
    private final SecurityService securityService;

    @Autowired
    public ServiceController(ServiceService serviceService,
                             SecurityService securityService) {
        this.serviceService = serviceService;
        this.securityService = securityService;
    }

    @GetMapping(BASE_URL + "/list")
    public ModelAndView getList(ServiceSearchDTO serviceSearchDTO, ModelAndView mav) {
        List<ServiceDTO> services = serviceService.find(serviceSearchDTO);

        mav.addObject("services", services);
        mav.setViewName("service/list");
        return mav;
    }

    @GetMapping(BASE_URL + "/my-list")
    public ModelAndView getMyList(ServiceSearchDTO serviceSearchDTO, ModelAndView mav) {
        serviceSearchDTO.setOwnerId(securityService.getCurrentUser().getUserId());

        List<ServiceDTO> services = serviceService.find(serviceSearchDTO);

        mav.addObject("services", services);
        mav.setViewName("service/my-list");
        return mav;
    }

    @GetMapping(BASE_URL + "/{id}")
    public ModelAndView getDetails(@PathVariable("id") Long serviceId, ModelAndView mav) {
        ServiceDTO serviceDTO = serviceService.findById(serviceId);

        mav.addObject("service", serviceDTO);
        mav.setViewName("service/details");
        return mav;
    }

    @GetMapping(BASE_URL + "/{id}/edit")
    public ModelAndView getEdit(@PathVariable("id") Long serviceId, ModelAndView mav) {
        ServiceDTO serviceDTO = serviceService.findById(serviceId);
        ServiceCommand serviceCommand = serviceCommandFromServiceDTO(serviceDTO);

        mav.addObject("service", serviceCommand);
        mav.setViewName("service/create");
        return mav;
    }

    @GetMapping(BASE_URL + "/create")
    public ModelAndView getCreate(ModelAndView mav) {
        ServiceCommand serviceCommand = new ServiceCommand();
        serviceCommand.setOwnerId(securityService.getCurrentUser().getUserId());

        mav.addObject("service", serviceCommand);
        mav.setViewName("service/create");
        return mav;
    }

    @PostMapping(BASE_URL + "/save")
    public String save(@Valid @ModelAttribute(value = "service") ServiceCommand serviceCommand) {
        final ServiceDTO serviceDTO = serviceDTOFromServiceCommand(serviceCommand);
        serviceService.save(serviceDTO);
        return "redirect:/service/my-list";
    }

    @PostMapping(BASE_URL + "/{id}/delete")
    public String delete(@PathVariable("id") Long serviceId) {
        serviceService.delete(serviceId);
        return "redirect:/service/my-list";
    }

    private static final class ServiceCommand {
        private Long id;
        private String name;
        private Long ownerId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
        }
    }

    private ServiceDTO serviceDTOFromServiceCommand(ServiceCommand serviceCommand) {
        final ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(serviceCommand.getId());
        serviceDTO.setName(serviceCommand.getName());
        serviceDTO.setOwner(new NameIdDTO("", serviceCommand.getOwnerId()));
        return serviceDTO;
    }

    private ServiceCommand serviceCommandFromServiceDTO(ServiceDTO serviceDTO) {
        final ServiceCommand serviceCommand = new ServiceCommand();
        serviceCommand.setId(serviceDTO.getId());
        serviceCommand.setName(serviceDTO.getName());
        serviceCommand.setOwnerId(serviceDTO.getOwner().getId());
        return serviceCommand;
    }
}
