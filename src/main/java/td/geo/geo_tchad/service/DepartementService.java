package td.geo.geo_tchad.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import td.geo.geo_tchad.dto.CreateDepartementRequest;
import td.geo.geo_tchad.dto.DepartementDto;
import td.geo.geo_tchad.entity.Departement;
import td.geo.geo_tchad.entity.Province;
import td.geo.geo_tchad.exception.ResourceNotFoundException;
import td.geo.geo_tchad.repository.DepartementRepository;
import td.geo.geo_tchad.repository.ProvinceRepository;

@Service
@Transactional(readOnly = true)
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final ProvinceRepository provinceRepository;

    public DepartementService(
            DepartementRepository departementRepository,
            ProvinceRepository provinceRepository
    ) {
        this.departementRepository = departementRepository;
        this.provinceRepository = provinceRepository;
    }

    public Page<DepartementDto> getAllDepartements(Pageable pageable) {
        return departementRepository.findAll(pageable).map(this::toDto);
    }

    public DepartementDto getDepartementById(Long id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'id : " + id));
        return toDto(departement);
    }

    public List<DepartementDto> getDepartementsByProvinceId(Long provinceId) {
        if (!provinceRepository.existsById(provinceId)) {
            throw new ResourceNotFoundException("Province introuvable avec l'id : " + provinceId);
        }
        return departementRepository.findByProvinceId(provinceId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public DepartementDto createDepartement(CreateDepartementRequest request) {
        Province province = provinceRepository.findById(request.provinceId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Province introuvable avec l'id : " + request.provinceId()));

        Departement departement = new Departement(
                request.code(), request.nom(), request.chefLieu(), province
        );
        return toDto(departementRepository.save(departement));
    }

    @Transactional
    public DepartementDto updateDepartement(Long id, CreateDepartementRequest request) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'id : " + id));

        Province province = provinceRepository.findById(request.provinceId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Province introuvable avec l'id : " + request.provinceId()));

        departement.setCode(request.code());
        departement.setNom(request.nom());
        departement.setChefLieu(request.chefLieu());
        departement.setProvince(province);

        return toDto(departement);
    }

    @Transactional
    public void deleteDepartement(Long id) {
        if (!departementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Département introuvable avec l'id : " + id);
        }
        departementRepository.deleteById(id);
    }

    private DepartementDto toDto(Departement departement) {
        return new DepartementDto(
                departement.getId(),
                departement.getCode(),
                departement.getNom(),
                departement.getChefLieu(),
                departement.getProvince().getId()
        );
    }
}