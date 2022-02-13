package br.tec.fivedti.queueserviceapi.controllers.docs;

import br.tec.fivedti.queueserviceapi.dto.request.CompanyDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.UUID;

@Api("Manages companies")
public interface CompanyControllerDocs {

    @ApiOperation(value = "Company creation operation.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success company creation."),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    MessageResponseDto createCompany(CompanyDto companyDto);

    @ApiOperation(value = "Returns a list of all companies registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all companies registered in the system.")
    })
    List<CompanyDto> listCompanies();

    @ApiOperation(value = "Returns company by ID registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success company found in the system."),
            @ApiResponse(code = 404, message = "Company with given ID not found.")
    })
    CompanyDto findById(UUID id) throws CompanyNotFoundException;

    @ApiOperation(value = "Update company by ID registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success company update in the system."),
            @ApiResponse(code = 404, message = "Company with given ID not found.")
    })
    MessageResponseDto update(UUID id, CompanyDto companyDto) throws CompanyNotFoundException;

    @ApiOperation(value = "Delete a company found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success company deleted in the system"),
            @ApiResponse(code = 404, message = "Company with given ID not found.")
    })
    MessageResponseDto delete(UUID id) throws CompanyNotFoundException;
}