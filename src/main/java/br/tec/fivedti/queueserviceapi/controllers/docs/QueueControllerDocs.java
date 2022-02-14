package br.tec.fivedti.queueserviceapi.controllers.docs;

import br.tec.fivedti.queueserviceapi.dto.request.QueueDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyDeactivatedException;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyNotFoundException;
import br.tec.fivedti.queueserviceapi.excepitions.QueueNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.UUID;

@Api("Manages queues")
public interface QueueControllerDocs {

    @ApiOperation(value = "Queue creation operation.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success queue creation."),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    MessageResponseDto createQueue(QueueDto queueDto) throws CompanyNotFoundException, CompanyDeactivatedException;

    @ApiOperation(value = "Returns a list of all queues registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all queues registered in the system.")
    })
    List<QueueDto> listQueues();

    @ApiOperation(value = "Returns queue by ID registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success queue found in the system."),
            @ApiResponse(code = 404, message = "Queue with given ID not found.")
    })
    QueueDto findById(UUID id) throws QueueNotFoundException;

    @ApiOperation(value = "Update queue by ID registered in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success queue update in the system."),
            @ApiResponse(code = 404, message = "Queue with given ID not found.")
    })
    MessageResponseDto update(UUID id, QueueDto queueDto) throws QueueNotFoundException, CompanyNotFoundException;

    @ApiOperation(value = "Delete a queue found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success queue deleted in the system"),
            @ApiResponse(code = 404, message = "Queue with given ID not found.")
    })
    MessageResponseDto delete(UUID id) throws QueueNotFoundException;
}