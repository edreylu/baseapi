package com.example.baseapi.domain.unidadResponsable;

import com.example.baseapi.domain.estatus.EstatusMapper;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

public class UnidadResponsableMapper {

    public static UnidadResponsableDTO toUnidadResponsableDTO(UnidadResponsable unidadResponsable) {
        if (Objects.isNull(unidadResponsable)) {
            return null;
        }

        UnidadResponsableDTO unidadResponsableDTO = new UnidadResponsableDTO();
        unidadResponsableDTO.setClaveUr(unidadResponsable.getClaveUr());
        unidadResponsableDTO.setDescripcion(unidadResponsable.getDescripcion());
        unidadResponsableDTO.setFechaInicio(unidadResponsable.getFechaInicio());
        unidadResponsableDTO.setFechaFinal(unidadResponsable.getFechaFinal());
        unidadResponsableDTO.setEstatus(EstatusMapper.toEstatusDTO(unidadResponsable.getEstatus()));

        return unidadResponsableDTO;
    }

    public static List<UnidadResponsableDTO> toUnidadResponsableDTOs(List<UnidadResponsable> unidadesResponsables) {
        if (Objects.isNull(unidadesResponsables)) {
            return null;
        }
        List<UnidadResponsableDTO> list = new ArrayList<>(unidadesResponsables.size());
        for (UnidadResponsable unidad : unidadesResponsables) {
            list.add(toUnidadResponsableDTO(unidad));
        }
        return list;
    }

    public static UnidadResponsable toUnidadResponsable(UnidadResponsableDTO unidadResponsableDTO) {
        if (Objects.isNull(unidadResponsableDTO)) {
            return null;
        }

        UnidadResponsable unidadResponsable = new UnidadResponsable();
        unidadResponsable.setClaveUr(unidadResponsableDTO.getClaveUr());
        unidadResponsable.setDescripcion(unidadResponsableDTO.getDescripcion());
        unidadResponsable.setFechaInicio(unidadResponsableDTO.getFechaInicio());
        unidadResponsable.setFechaFinal(unidadResponsableDTO.getFechaFinal());
        unidadResponsable.setEstatus(EstatusMapper.toEstatus(unidadResponsableDTO.getEstatus()));

        return unidadResponsable;
    }
}
