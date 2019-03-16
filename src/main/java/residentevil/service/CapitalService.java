package residentevil.service;

import residentevil.domain.models.serviceModel.CapitalServiceModel;

import java.util.List;

public interface CapitalService {

    List<CapitalServiceModel> findAllCapitals();

}
