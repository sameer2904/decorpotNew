package com.decorpot.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.decorpot.rest.model.PastWork;
import com.decorpot.services.PastWorkService;
import com.decorpot.utils.DecorpotUtils;
import com.decorpot.utils.ImageProcessorService;

@RestController
@RequestMapping(value = "/pastWork")
public class PastWorkController {

    @Autowired
    private PastWorkService pastWorkService;

    @Autowired
    private ImageProcessorService imageProcessorService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PastWork> getAllPastWork() {
        return pastWorkService.getAllPastWork();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public PastWork getPastWorkById(@PathVariable("id") Integer id) {
        return pastWorkService.getPastWorkById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadPastWork(@RequestBody PastWork pastWork) throws Exception {
        pastWorkService.uploadPastWork(pastWork);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public void uploadImages(@RequestParam("file") MultipartFile file) throws Exception {

        File imageFile = null;
        if (file != null) {
            imageFile = DecorpotUtils.multipartToFile(file);
            imageProcessorService.uploadPastWorkImage(imageFile);
        }
    }

}