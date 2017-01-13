package mx.clickfactura.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.clickfactura.domain.TipoCambio;
import mx.clickfactura.exception.CustomBadRequestException;
import mx.clickfactura.exception.CustomNotFoundException;
import mx.clickfactura.util.TipoCambioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by SISTEMAS03 on 02/03/2016.
 */
@Api(basePath = "/api/tipodecambio", value = "TipoDeCambio", description = "Obtecion del tipo de cambio del diario oficial", produces = "application/json")
@RestController
@RequestMapping(value = "/api/tipodecambio")
public class TipoDeCambioRestController {

    @Autowired
    private TipoCambioUtil tipoCambioUtil;


    /**
     * Obtener tipo de cambio del diario oficial
     *
     * @param fecha en formato yyyy-MM-dd
     * @return
     */
    @ApiOperation(value = "Obtener tipo de cambio", notes = "Obtener tipo de cambio por fecha (yyyy-MM-dd)")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fecha incorrecta."),
            @ApiResponse(code = 404, message = "No hay tipo de cambio en el día indicado."),
            @ApiResponse(code = 200, message = "", response = TipoCambio.class)})
    @GetMapping(value = {"/{fecha}"}, produces = "application/json")
    public ResponseEntity<Object> registra(@PathVariable(name = "fecha", required = true) String fecha) throws CustomBadRequestException, CustomNotFoundException, Exception {

        TipoCambio t = new TipoCambio();

            t.setValor(tipoCambioUtil.getTipoCambio(fecha));

        t.setSuccess(true);


        return ResponseEntity.ok().body(new HashMap<String, Object>() {{
            put("TipoCambio", t);
        }});


    }


}
