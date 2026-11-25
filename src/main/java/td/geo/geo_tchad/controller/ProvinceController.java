package td.geo.geo_tchad.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import td.geo.geo_tchad.dto.CreateProvinceRequest;
import td.geo.geo_tchad.dto.ProvinceDetailDto;
import td.geo.geo_tchad.dto.ProvinceDto;
import td.geo.geo_tchad.service.DepartementService;
import td.geo.geo_tchad.service.ProvinceService;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping ("/api/provinces")
@Tag(name = "Province", description = "API pour gérer les provinces")
public class ProvinceController {
    private final ProvinceService provinceService;
    private final DepartementService departementService;

    public ProvinceController(ProvinceService provinceService, DepartementService departementService) {
        this.provinceService = provinceService;
        this.departementService = departementService;
    }

    @GetMapping
    @Operation (
        summary = "Récupérer toutes les provinces", 
        description = "Cette opération permet de récupérer toutes les provinces avec pagination.")
    @ApiResponse (responseCode = "200", description = "Liste des provinces récupérée avec succès")
    
    public Page<ProvinceDto> getProvinces(@ParameterObject Pageable pageable) {
        return provinceService.getAllProvinces(pageable);
    }

    @GetMapping("/{id}")
    @Operation (
        summary = "Récupérer une province par son ID", 
        description = "Cette opération permet de récupérer une province spécifique en utilisant son ID.")
    public ProvinceDetailDto getProvinceById(@PathVariable Long id) {
        return provinceService.getProvinceById(id);
    }

    @Operation (
        summary = "Créer une nouvelle province", 
        description = "Cette opération permet de créer une nouvelle province.")
    @ApiResponse (responseCode = "201", description = "Province créée avec succès")
    @ApiResponse (responseCode = "400", description = "Requête invalide, les données de la province sont incorrectes")
    @ApiResponse (responseCode = "409", description = "Conflit, la province existe déjà ou viole une contrainte d'unicité")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProvinceDto createProvince(@Valid @RequestBody CreateProvinceRequest request) {
        return provinceService.createProvince(request);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une province existante")
    public ProvinceDto updateProvince(
            @PathVariable Long id,
            @Valid @RequestBody CreateProvinceRequest request
    ) {
        return provinceService.updateProvince(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une province")
    public void deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
    }
    
    
}
