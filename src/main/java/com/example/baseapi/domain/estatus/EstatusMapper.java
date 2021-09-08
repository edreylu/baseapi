package com.example.baseapi.domain.estatus;

import com.example.baseapi.persistence.entity.Estatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EstatusMapper {

    public static Estatus toEstatus(EstatusDTO estatusDTO) {

        if (Objects.isNull(estatusDTO)) {
            return null;
        }

        Estatus estatus = new Estatus();
        estatus.setIdEstatus(estatusDTO.getIdEstatus());
        estatus.setClave(estatusDTO.getClave());
        estatus.setColorhex(estatusDTO.getColorhex());
        estatus.setDescripcion(estatusDTO.getDescripcion());
        estatus.setExplicacion(estatusDTO.getExplicacion());
        return estatus;

    }

    public static EstatusDTO toEstatusDTO(Estatus estatus) {

        if (Objects.isNull(estatus)) {
            return null;
        }
        EstatusDTO estatusDTO = new EstatusDTO();
        estatusDTO.setIdEstatus(estatus.getIdEstatus());
        estatusDTO.setClave(estatus.getClave());
        estatusDTO.setColorhex(estatus.getColorhex());
        estatusDTO.setDescripcion(estatus.getDescripcion());
        estatusDTO.setExplicacion(estatus.getExplicacion());
        return estatusDTO;

    }

    public static List<EstatusDTO> toEstatusDTOs(List<Estatus> estatus) {
        if (Objects.isNull(estatus)) {
            return null;
        }
        List<EstatusDTO> list = new ArrayList<>(estatus.size());
        for (Estatus estatusr : estatus) {
            list.add(toEstatusDTO(estatusr));
        }
        return list;
    }

}
