package residentevil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.domain.entities.Virus;
import residentevil.domain.models.serviceModel.VirusServiceModel;
import residentevil.repository.VirusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VirusServiceModel addVirus(VirusServiceModel virusServiceModel) {
        Virus virus = this.modelMapper.map(virusServiceModel, Virus.class);
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


}
