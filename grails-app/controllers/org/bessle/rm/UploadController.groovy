package org.bessle.rm

import grails.converters.JSON
import org.apache.commons.io.IOUtils
import org.apache.commons.io.monitor.FileEntry
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

class UploadController {
    static allowedMethods = [uploadReview: "POST"]

    def importService

    def save() {
        String result = "unexpected error occured during uploading ${request?.getFileNames()[0]}"

        String fileLabel = params.fileLabel
        MultipartFile uploadedFile = null
        String fileName=""
        String newFileName="upload.xlsx"
        try{
            if (request instanceof MultipartHttpServletRequest){
                //Get the file's name from request
                fileName = request.getFileNames()[0]
                //Get a reference to the uploaded file.
                uploadedFile = request.getFile(fileName)
                newFileName = uploadedFile.originalFilename
                log.info "UploadController.save() File name : ${uploadedFile.originalFilename}, File size : ${uploadedFile.size}"
            }
            if (uploadedFile.empty) {
                flash.error = g.message(code:'',default:'Empty cannot be uploaded')
                return
            }
            //get uploaded file's inputStream
            InputStream inputStream = uploadedFile.inputStream
            //get the file storage location
            def fileStorageLocation = grailsApplication.config.reviewmanager.file.storage.location

            File dir = new File(fileStorageLocation)
            File file = new File(fileStorageLocation, newFileName)
            //This support both overriding and creating new file
            //If two of these fails, that means got some internal issue. May be new file creation permissions issue
            if ( ( dir.exists() ||  dir.createNewFile()) &&
                 (file.exists() || file.createNewFile()) ) {

                //to close the fileOutStream, opening it using withOutStream closure
                file.withOutputStream { fos ->
                    IOUtils.copyLarge(inputStream, fos)
                    fos.close()
                }
                flash.success = g.message(code:'', default:'File uploaded successfully')
            } else {
                throw new RuntimeException("error while creating  ${file} at ${fileStorageLocation}")
            }
        }
        catch (Exception e){
            log.info("UploadController.save() caught unexpected exception during upload", e)
            flash.error = g.message(code:'',default: 'File upload failed due to internal errors. Please try again')
            result = "failed to upload ${fileName} sucessfully"
        }

        try {
            result = importService.importExcelFile(file)
        } catch (Exception e) {
            log.info("UploadController.save() caught unexpected exception during import", e)
            flash.error = g.message(code:'',default: 'File upload failed due to import errors. Please try again')
            result = "failed to import ${fileName} sucessfully"
        }

        JSON converter = new JSON(result:result)
        converter.render(response)
        return false
    }
}
