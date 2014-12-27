'use strict';

function ReviewResource(CrudResourceFactory) {
    return CrudResourceFactory('/api/review', 'Review');
}

angular.module('reviewManagerApp.review.services', ['grails'])
    .factory('ReviewResource', ReviewResource);
