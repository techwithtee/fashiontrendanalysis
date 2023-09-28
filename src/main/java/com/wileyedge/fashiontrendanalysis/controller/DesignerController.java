package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.model.Product;
import com.wileyedge.fashiontrendanalysis.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/designers")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DesignerController {

    private final DesignerService designerService;

    @Autowired
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    /**
     * Fetches all designers.
     *
     * @return A list of all designers.
     * @apiEndpoint GET http://localhost:6363/api/designers
     */
    @GetMapping
    public ResponseEntity<List<Designer>> getAllDesigners() {
        List<Designer> designers = designerService.getAllDesigners();
        return new ResponseEntity<>(designers, HttpStatus.OK);
    }

    /**
     * Fetches a designer by their ID.
     *
     * @param id The ID of the designer to fetch.
     * @return The designer with the given ID, or 404 Not Found if not present.
     * @apiEndpoint GET http://localhost:6363/api/designers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Designer> getDesignerById(@PathVariable Long id) {
        Designer designer = designerService.getDesignerById(id);
        if (designer != null) {
            return new ResponseEntity<>(designer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new designer.
     *
     * @param designer The designer entity to add.
     * @return The ID of the newly added designer.
     * @apiEndpoint POST http://localhost:6363/api/designers
     */
    @PostMapping
    public ResponseEntity<Long> addDesigner(@RequestBody Designer designer) {
        Long designerId = designerService.addDesigner(designer);
        return new ResponseEntity<>(designerId, HttpStatus.CREATED);
    }

    /**
     * Updates a designer by their ID.
     *
     * @param id The ID of the designer to update.
     * @param designer The updated designer entity.
     * @return A response indicating the success of the operation.
     * @apiEndpoint PUT http://localhost:6363/api/designers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDesigner(@PathVariable Long id, @RequestBody Designer designer) {
        boolean updated = designerService.updateDesigner(id, designer);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a designer by their ID.
     *
     * @param id The ID of the designer to delete.
     * @return A response indicating the success of the operation.
     * @apiEndpoint DELETE http://localhost:6363/api/designers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesigner(@PathVariable Long id) {
        boolean deleted = designerService.deleteDesigner(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fetches designers based on their location.
     *
     * @param location The location to filter designers by.
     * @return A list of designers from the specified location.
     * @apiEndpoint GET http://localhost:6363/api/designers/location/{location}
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Designer>> getDesignersByLocation(@PathVariable String location) {
        List<Designer> designers = designerService.getDesignersByLocation(location);
        return new ResponseEntity<>(designers, HttpStatus.OK);
    }

    /**
     * Fetches the trend count for a designer.
     *
     * @param id The ID of the designer.
     * @return The trend count for the specified designer.
     * @apiEndpoint GET http://localhost:6363/api/designers/{id}/trendCount
     */
    @GetMapping("/{id}/trendCount")
    public ResponseEntity<Integer> getDesignerTrendCount(@PathVariable Long id) {
        Integer trendCount = designerService.getDesignerTrendCount(id);
        if (trendCount != null) {
            return new ResponseEntity<>(trendCount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fetches the popularity score for a designer.
     *
     * @param id The ID of the designer.
     * @return The popularity score for the specified designer.
     * @apiEndpoint GET http://localhost:6363/api/designers/{id}/popularityScore
     */
    @GetMapping("/{id}/popularityScore")
    public ResponseEntity<Integer> getDesignerPopularityScore(@PathVariable Long id) {
        Integer popularityScore = designerService.getDesignerPopularityScore(id);
        if (popularityScore != null) {
            return new ResponseEntity<>(popularityScore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fetches products associated with a specific designer.
     *
     * @param designerId The ID of the designer.
     * @return A list of products for the specified designer.
     * @apiEndpoint GET http://localhost:6363/api/designers/{designerId}/products
     */
    @GetMapping("/{designerId}/products")
    public ResponseEntity<List<Product>> getProductsForDesigner(@PathVariable Long designerId) {
        // Logic to get products for a designer
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
