package td.geo.geo_tchad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import td.geo.geo_tchad.dto.CreateDepartementRequest;
import td.geo.geo_tchad.dto.DepartementDto;
import td.geo.geo_tchad.service.DepartementService;

@RestController
@RequestMapping("/api/departements")
@Tag(name = "Départements", description = "Gestion des 95 départements du Tchad")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    @Operation(summary = "Lister les départements avec pagination")
    public Page<DepartementDto> getDepartements(@ParameterObject Pageable pageable) {
        return departementService.getAllDepartements(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un département par son identifiant")
    public DepartementDto getDepartementById(@PathVariable Long id) {
        return departementService.getDepartementById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un département rattaché à une province")
    public DepartementDto createDepartement(@Valid @RequestBody CreateDepartementRequest request) {
        return departementService.createDepartement(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un département existant")
    public DepartementDto updateDepartement(
            @PathVariable Long id,
            @Valid @RequestBody CreateDepartementRequest request
    ) {
        return departementService.updateDepartement(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un département")
    public void deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
    }
}