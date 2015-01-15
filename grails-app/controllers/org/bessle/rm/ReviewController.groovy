package org.bessle.rm
import org.bessle.rm.Review

class ReviewController extends PagedRestfulController {
    static allowedMethods = [save: "POST", update: "PUT", patch: "PATCH", delete: "DELETE", upload: "POST"]

    ReviewController() {
        super(Review)
    }
}