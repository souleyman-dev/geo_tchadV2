package td.geo.geo_tchad.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import td.geo.geo_tchad.dto.CreateProvinceRequest;
import td.geo.geo_tchad.dto.ProvinceDetailDto;
import td.geo.geo_tchad.dto.ProvinceDto;
import td.geo.geo_tchad.entity.Province;
import td.geo.geo_tchad.exception.ResourceNotFoundException;
import td.geo.geo_tchad.repository.ProvinceRepository;

@ExtendWith(MockitoExtension.class)
class ProvinceServiceTest {

    @Mock
    private ProvinceRepository provinceRepository;

    @InjectMocks
    private ProvinceService provinceService;

    @Test
    void getProvinceById_shouldReturnDetail_whenExists() {
        
        Province province = new Province("NDJ", "N'Djamena", "N'Djamena");
        when(provinceRepository.findWithDepartementsById(1L)).thenReturn(Optional.of(province));

        ProvinceDetailDto result = provinceService.getProvinceById(1L);

        assertThat(result.code()).isEqualTo("NDJ");
        assertThat(result.nom()).isEqualTo("N'Djamena");
        assertThat(result.departements()).isEmpty();
    }

    @Test
    void getProvinceById_shouldThrowNotFound_whenMissing() {
        when(provinceRepository.findWithDepartementsById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> provinceService.getProvinceById(999L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("999");
    }

    @Test
    void createProvince_shouldSaveAndReturnDto() {
        CreateProvinceRequest request = new CreateProvinceRequest("OUA", "Ouaddaï", "Abéché");
        Province saved = new Province("OUA", "Ouaddaï", "Abéché");
        when(provinceRepository.save(any(Province.class))).thenReturn(saved);

        ProvinceDto result = provinceService.createProvince(request);

        assertThat(result.code()).isEqualTo("OUA");
        assertThat(result.chefLieu()).isEqualTo("Abéché");
        verify(provinceRepository).save(any(Province.class));
    }
}