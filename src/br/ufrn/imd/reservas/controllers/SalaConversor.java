package br.ufrn.imd.reservas.controllers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.ufrn.imd.reservas.dominio.Sala;
import br.ufrn.imd.reservas.repositorios.SalaRepositorio;

@FacesConverter("salaConversor")
public class SalaConversor implements Converter<Object>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private SalaRepositorio salas;
    
    public SalaConversor() {
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
				return salas.getSala((Integer.valueOf(value)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion error", "sala inválida."));
            }
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object instanceof Sala && object != null){ 
        	Integer id =  (Integer)((Sala) object).getId();
            return id.toString();
        } else {
            return null;
        }
    }
    
}
