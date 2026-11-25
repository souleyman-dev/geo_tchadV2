package td.geo.geo_tchad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProvinceRequest(
        @NotBlank(message = "Le code de la province est obligatoire")
        @Size(max = 10, message = "Le code ne doit pas dépasser 10 caractères")
        String code,

        @NotBlank(message = "Le nom de la province est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String nom,

        @NotBlank(message = "Le chef-lieu est obligatoire")
        @Size(max = 100, message = "Le chef-lieu ne doit pas dépasser 100 caractères")
        String chefLieu
) {}