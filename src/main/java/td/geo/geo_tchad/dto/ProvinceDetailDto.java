package td.geo.geo_tchad.dto;

import java.util.List;

public record ProvinceDetailDto(
        Long id,
        String code,
        String nom,
        String chefLieu,
        List<DepartementDto> departements
) {}