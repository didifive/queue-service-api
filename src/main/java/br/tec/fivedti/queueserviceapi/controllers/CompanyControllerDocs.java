package br.tec.fivedti.queueserviceapi.controllers;

import br.tec.fivedti.queueserviceapi.dto.CompanyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

@Api("Manages companies")
public interface CompanyControllerDocs {

    @ApiOperation(value = "Company creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success company creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    CompanyDto createCompany(CompanyDto companyDto);

 /*   @ApiOperation(value = "Returns beer found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success beer found in the system"),
            @ApiResponse(code = 404, message = "Beer with given name not found.")
    })
    BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException;

    @ApiOperation(value = "Returns a list of all beers registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all beers registered in the system"),
    })
    List<BeerDTO> listBeers();

    @ApiOperation(value = "Delete a beer found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success beer deleted in the system"),
            @ApiResponse(code = 404, message = "Beer with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws BeerNotFoundException;*/
}