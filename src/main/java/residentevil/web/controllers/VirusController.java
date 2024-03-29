package residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.domain.models.binding.VirusAddBindingModel;
import residentevil.domain.models.binding.VirusEditBindingModel;
import residentevil.domain.models.serviceModel.VirusServiceModel;
import residentevil.domain.models.view.CapitalListViewModel;
import residentevil.domain.models.view.VirusListViewModel;
import residentevil.service.CapitalService;
import residentevil.service.VirusService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final CapitalService capitalService;
    private final ModelMapper modelMapper;
    private final VirusService virusService;

    @Autowired
    public VirusController(CapitalService capitalService, ModelMapper modelMapper, VirusService virusService) {
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
        this.virusService = virusService;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel) {
        modelAndView.addObject("bindingModel", bindingModel);

        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream().map(c -> this.modelMapper.map(c, CapitalListViewModel.class)).collect(Collectors.toList()));

        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusAddBindingModel bindingModel,
                                   BindingResult bindingResult, ModelAndView modelAndView) {

//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("bindingModel", bindingModel);
//
//            modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
//                    .stream().map(c -> this.modelMapper.map(c, CapitalListViewModel.class)).collect(Collectors.toList()));
//
//            return super.view("add-virus", modelAndView);
//        }

        VirusServiceModel virusServiceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);

        this.virusService.addVirus(virusServiceModel);

//        if (virusServiceModel == null) {
//            throw new IllegalArgumentException("Virus do not added!");
//        }

        return super.redirect("/");
    }

    @GetMapping("/show")
    public ModelAndView showViruses(ModelAndView modelAndView) {

        modelAndView.addObject("viruses", this.virusService.finAllViruses()
                .stream().map(v -> this.modelMapper.map(v, VirusListViewModel.class)).collect(Collectors.toList()));

        return super.view("show-virus", modelAndView);
    }


    @GetMapping(path = {"/delete"})
    public ModelAndView deleteVirus(@RequestParam("id") String id, ModelAndView modelAndView) {

        this.virusService.deleteVirusById(id);
        showViruses(modelAndView);

        return super.view("show-virus", modelAndView);
    }


    @GetMapping("/edit/{id}")
    public ModelAndView editVirus(@PathVariable("id") String id, ModelAndView modelAndView, VirusEditBindingModel model) {

        model = this.modelMapper.map(this.virusService.findVirusById(id), VirusEditBindingModel.class);
        modelAndView.addObject("model", model);

        modelAndView.addObject("capitals", this.capitalService.findAllCapitals()
                .stream().map(c -> this.modelMapper.map(c, CapitalListViewModel.class)).collect(Collectors.toList()));

        return super.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView saveEditedVirus(@PathVariable("id") String id,
                                        @Valid @ModelAttribute(name = "bindingModel") VirusEditBindingModel bindingModel,
                                        BindingResult bindingResult, ModelAndView modelAndView) {

        VirusServiceModel virusServiceModel = this.modelMapper.map(bindingModel, VirusServiceModel.class);
        virusServiceModel.setId(id);
        this.virusService.saveEditedVirus(virusServiceModel);

        //todo validation

        if (virusServiceModel == null) {
            throw new IllegalArgumentException("Virus do not saved!");
        }
        return super.redirect("/");
    }
}
