package cl.acn.lab.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import cl.acn.lab.demo.dto.VideoDTO;
import cl.acn.lab.demo.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/video")
@Api(value="Demo_Controller", description="Demo Controller")
@Log4j
public class VideoController {
	
	    @Autowired
	    @Qualifier("videoService")
	    private VideoService videoService;

	    /**GET - ALL**/
	    @GetMapping(value = "example", produces = {MediaType.APPLICATION_JSON_VALUE})
	    @ApiOperation(value = "Ejemplo de operación GET - ALL", response = VideoDTO.class, responseContainer = "List<>", notes = "Returns a List<ExampleDTO>")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success return a list of ExampleDTO object", response = VideoDTO.class ),
	            @ApiResponse(code = 204, message = "No content, without content for the query", response = String.class ),
	            @ApiResponse(code = 500, message = "Internal Server Error, Exception message")
	    })
	    public ResponseEntity<List<VideoDTO>> exampleGetAll() {
	        log.info("######  ENTRADA A GET ALL EXAMPLE");
	        ResponseEntity<List<VideoDTO>> response =null;
	        List<VideoDTO> responseData = this.videoService.getAll();
	        try {
	            if(responseData!=null) {
	                response =  new ResponseEntity<>(responseData, HttpStatus.OK);
	            }else {
	                response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	            }
	        }catch (Exception e) {
	            response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        log.info("######  SALIDA DE GET ALL EXAMPLE");
	        return response;
	    }
	    
	    /**POST**/
	    @PostMapping(value = "example", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	    @ApiOperation(value = "Ejemplo de operación POST", response = Boolean.class, responseContainer = "", notes = "Returns true for success and false for error operation")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success return a Boolean Object", response = Boolean.class ),
	            @ApiResponse(code = 204, message = "No content, without content for the query"),
	            @ApiResponse(code = 500, message = "Internal Server Error, Exception message")
	    })
	    public ResponseEntity<?> examplePost(@ApiParam(name = "requestIN", value = "List of ExampleDTO Object", required = true) @RequestBody List<VideoDTO> requestIN) {
	        log.info("######  ENTRADA A POST EXAMPLE  | JSON REQUEST : "  +  new Gson().toJson(requestIN));

	        ResponseEntity<?> response =null;
	        boolean responseData = this.videoService.save(requestIN);
	        try {
	            if(responseData) {
	                response =  new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	            }else {
	                response = new ResponseEntity<>(Boolean.FALSE, HttpStatus.NO_CONTENT);
	            }
	        } catch (Exception e) {
	            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	        }
	        log.info("######  SALIDA DE POST EXAMPLE");
	        return response;
	    }

}
