package com.hmvss.api.services.interfaces;

import com.hmvss.api.dto.personalDataInfo.LocationDTO;
import com.hmvss.api.persistence.model.Location;

public interface ILocationService {

    Location getLocationById(Long id);

    Location updateLocation(LocationDTO locationDTO);

    Location registerLocation(LocationDTO locationDTO);
}
