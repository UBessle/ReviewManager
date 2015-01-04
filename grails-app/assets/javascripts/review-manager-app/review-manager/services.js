'use strict';

function ReviewManagerResource(CrudResourceFactory) {
    return CrudResourceFactory('/api/reviewManager', 'ReviewManager');
}

angular.module('reviewManagerApp.reviewManager.services', ['grails'])
    .factory('ReviewManagerResource', ReviewManagerResource);
