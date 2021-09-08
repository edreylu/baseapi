package com.example.baseapi.domain.datosPersonales;

import com.example.baseapi.persistence.entity.DatosPersonales;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class DatosPersonalesMapper {

    public static DatosPersonalesDTO toPersonalDTO(DatosPersonales personal) {
        if (Objects.isNull(personal)) {
            return null;
        }

        DatosPersonalesDTO datosPersonalesDTO = new DatosPersonalesDTO();

        datosPersonalesDTO.setNoPersonal(personal.getNoPersonal());
        datosPersonalesDTO.setApellidoPaterno(personal.getApellidoPaterno());
        datosPersonalesDTO.setApellidoMaterno(personal.getApellidoMaterno());
        datosPersonalesDTO.setNombre(personal.getNombre());
        datosPersonalesDTO.setCorreo(personal.getCorreo());
        datosPersonalesDTO.setTelefonoFijo(personal.getTelefonoFijo());
        datosPersonalesDTO.setTelefonoMovil(personal.getTelefonoMovil());
        datosPersonalesDTO.setNombreCompleto(getNombreCompleto.apply(datosPersonalesDTO));

        return datosPersonalesDTO;
    }

    public static DatosPersonales toPersonal(DatosPersonalesDTO datosPersonalesDTO) {
        if (Objects.isNull(datosPersonalesDTO)) {
            return null;
        }

        DatosPersonales datosPersonales = new DatosPersonales();

        datosPersonales.setNoPersonal(datosPersonalesDTO.getNoPersonal());
        datosPersonales.setApellidoPaterno(datosPersonalesDTO.getApellidoPaterno());
        datosPersonales.setApellidoMaterno(datosPersonalesDTO.getApellidoMaterno());
        datosPersonales.setNombre(datosPersonalesDTO.getNombre());
        datosPersonales.setCorreo(datosPersonalesDTO.getCorreo());
        datosPersonales.setTelefonoFijo(datosPersonalesDTO.getTelefonoFijo());
        datosPersonales.setTelefonoMovil(datosPersonalesDTO.getTelefonoMovil());

        return datosPersonales;
    }

    public static List<DatosPersonalesDTO> toUnidadResponsableDTOs(List<DatosPersonales> personales) {
        if (Objects.isNull(personales)) {
            return null;
        }
        List<DatosPersonalesDTO> list = new ArrayList<>(personales.size());
        for (DatosPersonales personal : personales) {
            list.add(toPersonalDTO(personal));
        }
        return list;
    }

    private static Function<DatosPersonalesDTO, String> getNombreCompleto = personal -> String.format("%s %s %s",
            personal.getNombre(), personal.getApellidoPaterno(), personal.getApellidoMaterno());
}
