package mx.clickfactura.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by SISTEMAS03 on 02/03/2016.
 */
@Data
@XmlRootElement(name = "Cambio")
public class TipoCambio {

    private Boolean success;
    private String valor;

}
