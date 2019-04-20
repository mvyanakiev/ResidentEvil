package residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.Capital;
import residentevil.domain.entities.Virus;
import residentevil.domain.models.serviceModel.CapitalServiceModel;
import residentevil.domain.models.serviceModel.VirusServiceModel;
import residentevil.repository.VirusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;
    private final CapitalService capitalService;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper, CapitalService capitalService) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
        this.capitalService = capitalService;
    }

    @Override
    public VirusServiceModel addVirus(VirusServiceModel virusServiceModel) {
        Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
        virus.getCapitals().clear();

        for (String capitalId : virusServiceModel.getCapitals() ) {
            virus.getCapitals().add(this.modelMapper.map(this.capitalService.findCapitalByID(capitalId), Capital.class));
        }


        try {
            this.virusRepository.saveAndFlush(virus);
            return this.modelMapper.map(virus, VirusServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    public List<VirusServiceModel> finAllViruses() {
        return this.virusRepository.findAll().stream()
                .map(v -> this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVirusById(String id) {

        try {
            this.virusRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public VirusServiceModel findVirusById(String id) {

        try {
            Virus tempVirus = this.virusRepository.findById(id).orElse(null);
            return this.modelMapper.map(tempVirus, VirusServiceModel.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveEditedVirus(VirusServiceModel virusServiceModel) {

        Virus virus = this.virusRepository.findById(virusServiceModel.getId()).orElse(null);

        virus.setName(virusServiceModel.getName());
        //todo all other fields

        try {
            this.virusRepository.saveAndFlush(virus);
//            return this.modelMapper.map(virus, VirusServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
//            return null;
        }

    }
}
