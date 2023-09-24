package com.wileyedge.fashiontrendanalysis.controller;

import com.wileyedge.fashiontrendanalysis.model.Designer;
import com.wileyedge.fashiontrendanalysis.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designers")
public class DesignerController {

    private final DesignerService designerService;

    @Autowired
    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    @GetMapping
    public ResponseEntity<List<Designer>> getAllDesigners() {
        List<Designer> designers = designerService.getAllDesigners();
        return new ResponseEntity<>(designers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designer> getDesignerById(@PathVariable Long id) {
        Designer designer = designerService.getDesignerById(id);
        if (designer != null) {
            return new ResponseEntity<>(designer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Long> addDesigner(@RequestBody Designer designer) {
        Long designerId = designerService.addDesigner(designer);
        return new ResponseEntity<>(designerId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDesigner(@PathVariable Long id, @RequestBody Designer designer) {
        boolean updated = designerService.updateDesigner(id, designer);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesigner(@PathVariable Long id) {
        boolean deleted = designerService.deleteDesigner(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Designer>> getDesignersByLocation(@PathVariable String location) {
        List<Designer> designers = designerService.getDesignersByLocation(location);
        return new ResponseEntity<>(designers, HttpStatus.OK);
    }
}
