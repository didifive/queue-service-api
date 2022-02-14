package br.tec.fivedti.queueserviceapi.controllers.docs;

import br.tec.fivedti.queueserviceapi.dto.request.NumberDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.excepitions.NumberNotFoundException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueDeactivatedException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.UUID;

@Api("Manages numbers")
public interface NumberControllerDocs {

    @ApiOperation(value = "Number creation operation.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success number creation."),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    MessageResponseDto createNumber(NumberDto numberDto) throws QueueNotFoundException, QueueDeactivatedException;

    @ApiOperation(value = "Returns a list of all numbers registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all numbers registered in the system.")
    })
    List<NumberDto> listNumbers();

    @ApiOperation(value = "Returns number by ID registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success number found in the system."),
            @ApiResponse(code = 404, message = "Number with given ID not found.")
    })
    NumberDto findById(UUID id) throws NumberNotFoundException;

    @ApiOperation(value = "Update number by ID registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success number update in the system."),
            @ApiResponse(code = 404, message = "Number with given ID not found.")
    })
    MessageResponseDto update(UUID id, NumberDto numberDto) throws NumberNotFoundException, QueueNotFoundException;

    @ApiOperation(value = "Delete a number found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success number deleted in the system"),
            @ApiResponse(code = 404, message = "Number with given ID not found.")
    })
    MessageResponseDto delete(UUID id) throws NumberNotFoundException;
}