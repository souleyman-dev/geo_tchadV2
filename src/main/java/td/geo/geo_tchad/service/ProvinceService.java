package td.geo.geo_tchad.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import td.geo.geo_tchad.dto.CreateProvinceRequest;
import td.geo.geo_tchad.dto.DepartementDto;
import td.geo.geo_tchad.dto.ProvinceDetailDto;
import td.geo.geo_tchad.dto.ProvinceDto;
import td.geo.geo_tchad.entity.Province;
import td.geo.geo_tchad.exception.ResourceNotFoundException;
import td.geo.geo_tchad.repository.ProvinceRepository;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public Page<ProvinceDto> getAllProvinces(Pageable pageable) {
        return provinceRepository.findAll(pageable).map(this::toDto);
    }

    public ProvinceDto createProvince(CreateProvinceRequest request) {
        Province province = new Province(request.code(), request.nom(), request.chefLieu());
        Province saved = provinceRepository.save(province);
        return toDto(saved);
    }

    @Transactional 
    public ProvinceDto updateProvince(Long id, CreateProvinceRequest request) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province introuvable avec l'id : " + id));

        province.setCode(request.code());
        province.setNom(request.nom());
        province.setChefLieu(request.chefLieu());

        return toDto(province);
    }

    @Transactional
    public void deleteProvince(Long id) {
        if (!provinceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Province introuvable avec l'id : " + id);
        }
        provinceRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProvinceDetailDto getProvinceById(Long id) {
        Province province = provinceRepository.findWithDepartementsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province introuvable avec l'id : " + id));

        List<DepartementDto> departements = province.getDepartements().stream()
                .map(d -> new DepartementDto(
                        d.getId(), d.getCode(), d.getNom(), d.getChefLieu(), province.getId()
                ))
                .toList();

        return new ProvinceDetailDto(
                province.getId(),
                province.getCode(),
                province.getNom(),
                province.getChefLieu(),
                departements
        );
    }

    // Eviter de dupliquer le code de mapping entre Province et ProvinceDto
    private ProvinceDto toDto(Province province) {
        return new ProvinceDto(
                province.getId(),
                province.getCode(),
                province.getNom(),
                province.getChefLieu()
        );
    }
}