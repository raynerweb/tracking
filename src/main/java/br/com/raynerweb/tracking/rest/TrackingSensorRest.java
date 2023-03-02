package br.com.raynerweb.tracking.rest;

import br.com.raynerweb.tracking.dto.error.ResponseValidationError;
import br.com.raynerweb.tracking.dto.location.ResponseTrackingLocationDto;
import br.com.raynerweb.tracking.dto.sensor.RequestTrackingSensorDto;
import br.com.raynerweb.tracking.dto.sensor.ResponseTrackingSensorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name = "Tracking Sensor", description = "Tracking Sensor Endpoint")
public interface TrackingSensorRest {
    @Operation(summary = "Fetch Tracking Sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch Tracking Sensor",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseTrackingLocationDto[].class))})
    })
    List<ResponseTrackingSensorDto> findAll();

    @Operation(summary = "Save Tracking Sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save Tracking Sensor"),
            @ApiResponse(responseCode = "400", description = "Input Validation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseValidationError.class)))})
    void save(@Valid RequestTrackingSensorDto dto);
}
